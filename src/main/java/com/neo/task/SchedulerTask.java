package com.neo.task;

import com.neo.service.StatisticeService;
import jxl.write.WriteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @program spring-boot-mybatis-annotation
 * @description: 定时任务
 * @author: ch
 * @create: 2019/07/04 16:55
 */
@Component
public class SchedulerTask {

    @Autowired
    StatisticeService statisticeService;
    
    /**
    * @description: 每天晚上十点执行
    * @author: ch
    * @Param []
    * @return void
    * @date: 2019-07-04 16:59
    **/
    @Scheduled(cron="0 0 23 * * ?")
    public void task() throws IOException, WriteException {
        statisticeService.queryInfo();
    }
}
