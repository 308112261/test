package com.neo.service;

import com.neo.mapper.StatisticsMapper;
import com.neo.util.DateTimeUtils;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program spring-boot-mybatis-annotation
 * @description:
 * @author: ch
 * @create: 2019/07/03 15:40
 */

@Service
public class StatisticeService {

    @Value("${file.addr}")
    private String adds;

    @Autowired
    private StatisticsMapper statisticsMapper;


    @Autowired
   private MailService mailService;

    public void queryInfo() throws IOException, WriteException {
        String pwd = adds+"杭州店铺统计.xls";
        OutputStream os = new FileOutputStream(pwd);

        WritableWorkbook book = Workbook.createWorkbook(os);
        writaDate(book,query("94"),0);
        writaDate(book,query("97"),1);
        writaDate(book,query("143"),2);
        book.write();
        book.close();
        os.close();

        mailService.sendAttachmentsMail(pwd);
    }


 
    /**
    * @description: 发型师数据查询
    * @author: ch
    * @Param [store_id, admin]
    * @return java.util.List<java.lang.String>
    * @date: 2019-07-04 11:34
    **/
    public  List<String> stores(String store_id,String admin){
        List<String> list = new ArrayList<>();
        list.add(statisticsMapper.admin1(store_id,admin));
        list.add(statisticsMapper.admin2(store_id,admin));
        list.add(statisticsMapper.admin3(store_id,admin));
        list.add(statisticsMapper.admin4(store_id,admin));
        list.add(statisticsMapper.admin5(store_id,admin));
        list.add(statisticsMapper.admin6(store_id,admin));
        list.add(statisticsMapper.admin7(store_id,admin));
        list.add(statisticsMapper.admin8(store_id,admin));
        list.add(statisticsMapper.admin9(store_id,admin));
        list.add(statisticsMapper.admin10(store_id,admin));
        list.add(statisticsMapper.admin11(store_id,admin));
        list.add(statisticsMapper.admin12(store_id,admin));
        list.add(statisticsMapper.admin13(store_id,admin));
        list.add(statisticsMapper.admin14(store_id,admin));
        return list;
    }


    
    /**
    * @description: 查询店汇总
    * @author: ch
    * @Param [store_id]
    * @return java.util.List<java.lang.String>
    * @date: 2019-07-04 11:33
    **/
    public  List<String> stores(String store_id){
        List<String> list = new ArrayList<>();
        list.add(statisticsMapper.store1(store_id));
        list.add(statisticsMapper.store2(store_id));
        list.add(statisticsMapper.store3(store_id));
        list.add(statisticsMapper.store4(store_id));
        list.add(statisticsMapper.store5(store_id));
        list.add(statisticsMapper.store6(store_id));
        list.add(statisticsMapper.store7(store_id));
        list.add(statisticsMapper.store8(store_id));
        list.add(statisticsMapper.store9(store_id));
        list.add(statisticsMapper.store10(store_id));
        list.add(statisticsMapper.store11(store_id));
        list.add(statisticsMapper.store12(store_id));
        list.add(statisticsMapper.store13(store_id));
        list.add(statisticsMapper.store14(store_id));
        return list;
    }

    
    /**
    * @description: 数据组装
    * @author: ch
    * @Param [store_id]
    * @return java.util.List<java.util.List<java.lang.String>>
    * @date: 2019-07-04 11:35
    **/
    public List<List<String>> query(String store_id){
        List<Map<String, Object>> list = statisticsMapper.queryWorkPlay(store_id);
        List<List<String>> lists = new ArrayList<List<String>>();
        String store_name = "";
        List<String> ls = new ArrayList<>();
        if(!list.isEmpty()){
            String s =DateTimeUtils.yyyy_MM_dd(LocalDateTime.now());
            store_name=list.get(0).get("store_name")+s;
            ls.add(store_name);
        }
        ls.add("1用户支付成功订单数\n");
        ls.add("2用户支付成功实收订单额\n");
        ls.add("3用户支付使用优惠单数\n");
        ls.add("4型师服务订单数\n");
        ls.add("5 发型师服务实收金额\n");
        ls.add("6 发型师服务优惠单数\n");
        ls.add("7 服务前退款数\n");
        ls.add("8 服务前退款实收金额\n");
        ls.add("9 服务后退款数\n");
        ls.add("10 服务后退款实收金额\n");
        ls.add("11 服务男客数\n");
        ls.add("12 服务女客数\n");
        ls.add("13 今日产生评价数\n");
        ls.add("14 1-3星差评数");
        lists.add(ls);
        List<String> ls1 = new ArrayList<>();
        ls1.add("店汇总");
        List<String> stores = stores(store_id);
        ls1.addAll(stores);
        lists.add(ls1);

        if(!list.isEmpty()){
            for (Map<String, Object> map:list) {
                String staff_id = String.valueOf(map.get("staff_id"));
                String staff_name = String.valueOf(map.get("staff_name"));
                List<String> stringList = new ArrayList<>();
                stringList.add("今日排班发型师-"+staff_name);
                List<String> stores1 = stores(store_id, staff_id);
                stringList.addAll(stores1);
                lists.add(stringList);
            }
        }

        return lists;
    }


    /**
    * @description: excel 写入
    * @author: ch
    * @Param [book, lists, num]
    * @return void
    * @date: 2019-07-04 11:36
    **/
    public void  writaDate(WritableWorkbook book, List<List<String>> lists, int num) throws WriteException {
        String storeName="";
        if(!lists.isEmpty() && !lists.get(0).isEmpty()){
            storeName= lists.get(0).get(0);

        }
        WritableSheet sheet = book.createSheet(storeName, num);
        for (int i = 0; i < lists.size(); i++) {
            List<String> list1 = lists.get(i);
            sheet.setColumnView(i, 25);
            for (int j = 0; j < list1.size(); j++) {
                sheet.addCell(new jxl.write.Label(i, j, list1.get(j)));
            }
        }
    }



    public void queryInfoTest() throws IOException, WriteException {
//        String pwd = "/Users/xingkeduo/杭州店铺统计.xls";
        String pwd = adds+"杭州店铺统计.xls";
        OutputStream os = new FileOutputStream(pwd);

        WritableWorkbook book = Workbook.createWorkbook(os);
        writaDate(book,query("94"),0);
        writaDate(book,query("97"),1);
        writaDate(book,query("143"),2);
        book.write();
        book.close();
        os.close();

        mailService.sendAttachmentsMailTest(pwd);
    }

}
