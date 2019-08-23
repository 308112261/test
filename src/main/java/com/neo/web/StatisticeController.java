package com.neo.web;

import com.neo.mapper.StatisticsMapper;
import com.neo.service.StatisticeService;
import jxl.write.WriteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class StatisticeController {
	


	@Autowired
	StatisticeService statisticeService;


	/**
	* @description: 发送否拉萨大家发来的撒开理发店第一个
	* @author: ch
	* @Param []
	* @return void
	* @date: 2019-07-04 17:08
	**/
	@RequestMapping("/query")
	public void   queryWorkPlay() throws IOException, WriteException {
		statisticeService.queryInfo();
	}



	/**
	* @description: 测试使用
	* @author: ch
	* @Param []
	* @return void
	* @date: 2019-07-04 17:08
	**/
	@RequestMapping("/test")
	public void   queryWorkPlayTest() throws IOException, WriteException {
		statisticeService.queryInfoTest();
	}


    
}