package com.neo.service;

import com.neo.util.DateTimeUtils;
import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;

/**
 * @program spring-boot-mybatis-annotation
 * @description: 邮件发送
 * @author: ch
 * @create: 2019/07/04 14:27
 */
@Service
public class MailService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JavaMailSender mailSender;

    @Value("${mail.fromMail.addr}")
    private String from;


    @Value("${mail.toMail.addr}")
    private String to;


    @Value("${mail.toMail.addr.test}")
    private String totest;



    public void sendAttachmentsMailTest(String filePath) {
        String s = DateTimeUtils.yyyy_MM_dd(LocalDateTime.now());
        sendAttachmentsMail(totest, "主题：杭州店铺数据统计"+s, "各位老板：有附件，请查收！", filePath);
    }


    public void sendAttachmentsMail(String filePath) {
        String s = DateTimeUtils.yyyy_MM_dd(LocalDateTime.now());
       sendAttachmentsMail(to, "主题：杭州店铺数据统计"+s, "各位老板：有附件，请查收！", filePath);
    }

    public void sendAttachmentsMail(String to, String subject, String content, String filePath){
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to.split(","));

            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource file = new FileSystemResource(new File(filePath));

            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
//            logger.info("fileName "+fileName);
//            helper.addAttachment(fileName, file);
            helper.addAttachment(MimeUtility.encodeWord(fileName), file);


            mailSender.send(message);
            logger.info("邮件已经发送。");
        } catch (MessagingException e) {
            logger.error("邮件时发生异常！", e);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
