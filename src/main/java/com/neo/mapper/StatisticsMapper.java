package com.neo.mapper;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface StatisticsMapper {
	





	/**
	 * 获取店铺排班表
	 * @param store_id
	 * @return
	 */
	@Select("SELECT staff_id,staff_name,date,store_id,store_name FROM bos_work_plan.t_staff_attendance WHERE `enable` = TRUE and store_id = #{store_id} and date(date) = curdate() ")
	List<Map<String, Object>>  queryWorkPlay(String store_id);


	/**
	 * 1用户支付成功订单数 店总汇
	 * @return
	 */
	@Select(" SELECT count(*)     FROM t_order WHERE pay_success=1 and date(pay_time) = curdate()  and  store_id=#{store_id}")
	String  store1(String store_id);

	/**
	 * 1用户支付成功订单数 排班发型师
	 * @return
	 */
	@Select(" SELECT count(*)  FROM t_order WHERE pay_success=1 and date(pay_time) = curdate()  and  store_id=#{store_id} and appointment_admin_id=#{appointment_admin_id}")
	String  admin1(String store_id,String appointment_admin_id);


	/**
	 * 2用户支付成功实收订单额
	 * @param store_id
	 * @return
	 */
	@Select("SELECT sum(pay_price)  FROM t_order WHERE pay_success=1 and date(pay_time) = curdate()  and  store_id=#{store_id}")
	String store2(String store_id);

	/**
	 * 2用户支付成功实收订单额
	 * @param store_id
	 * @return
	 */
	@Select("SELECT sum(pay_price)  FROM t_order WHERE pay_success=1 and date(pay_time) = curdate()   and  store_id=#{store_id} and appointment_admin_id=#{appointment_admin_id}")
	String admin2(String store_id,String appointment_admin_id);


	/**
	 * 3用户支付使用优惠单数
	 * @param store_id
	 * @return
	 */
	@Select("SELECT count(*) as 店汇总  FROM t_order WHERE pay_success=1 and date(pay_time) = curdate()  and  store_id=#{store_id}  AND (marketing_id !=\"\" OR activity_id !=\"\")")
	String store3(String store_id);



	/**
	 * 3用户支付使用优惠单数
	 * @param store_id
	 * @return
	 */
	@Select("SELECT count(*)  FROM t_order WHERE pay_success=1 and date(pay_time) = curdate()   and  store_id=#{store_id} and appointment_admin_id=#{appointment_admin_id}  AND (marketing_id !=\"\" OR activity_id !=\"\")")
	String admin3(String store_id,String appointment_admin_id);


	/**
	 * 发4型师服务订单数*
	 * @param store_id
	 * @return
	 */
	@Select("SELECT count(*) as 店汇总  FROM t_order WHERE pay_success=1 and date(serve_start_time) = curdate()  and  store_id=#{store_id} and admin_id is NOT NULL AND admin_id !=\"\"")
	String store4(String store_id);


	/**
	 * 发4型师服务订单数*
	 * @param store_id
	 * @return
	 */
	@Select("SELECT count(*)  FROM t_order WHERE pay_success=1 and date(serve_start_time) = curdate()   and  store_id=#{store_id} and admin_id=#{admin_id}")
	String admin4(String store_id,String admin_id);


	/**
	 * 5 发型师服务实收金额
	 * @param store_id
	 * @return
	 */
	@Select("SELECT sum(pay_price)   FROM t_order WHERE pay_success=1 and date(serve_start_time) = curdate()  and  store_id=#{store_id}")
	String store5(String store_id);


	/**
	 * 5 发型师服务实收金额
	 * @param store_id
	 * @return
	 */
	@Select("SELECT sum(pay_price) FROM t_order WHERE pay_success=1 and date(serve_start_time) = curdate()   and  store_id=#{store_id} and admin_id=#{admin_id}")
	String admin5(String store_id,String admin_id);



	/**
	 * 6 发型师服务优惠单数
	 * @param store_id
	 * @return
	 */
	@Select("SELECT count(*)  FROM t_order WHERE pay_success=1 and date(serve_start_time) = curdate()  and  store_id=#{store_id}  AND (marketing_id !=\"\" OR activity_id !=\"\")")
	String store6(String store_id);

	/**
	 * 6 发型师服务优惠单数
	 * @param store_id
	 * @return
	 */
	@Select("SELECT count(*)  FROM t_order WHERE pay_success=1 and date(serve_start_time) = curdate()   and  store_id=#{store_id} and admin_id=#{admin_id}  AND (marketing_id !=\"\" OR activity_id !=\"\")")
	String admin6(String store_id,String admin_id);


	/**
	 * 7 服务前退款数
	 * @param store_id
	 * @return
	 */
	@Select("SELECT  COUNT(*) as 店汇总 FROM t_order WHERE pay_success=1 AND store_id=#{store_id} AND service_down=0 AND order_no IN (SELECT order_no FROM t_order_refund WHERE date(refund_time) = curdate())")
	String store7(String store_id);


	/**
	 * 7 服务前退款数
	 * @param store_id
	 * @return
	 */
	@Select("SELECT count(*) FROM t_order WHERE pay_success=1 and service_down=0  and  store_id=#{store_id} and appointment_admin_id=#{appointment_admin_id} AND order_no IN (SELECT order_no FROM t_order_refund WHERE date(refund_time) = curdate())")
	String admin7(String store_id,String appointment_admin_id);


	/**
	 * 8 服务前退款实收金额
	 * @param store_id
	 * @return
	 */
	@Select("SELECT  sum(pay_price)  FROM t_order WHERE pay_success=1 AND store_id=#{store_id} AND service_down=0 AND order_no IN (SELECT order_no FROM t_order_refund WHERE date(refund_time) = curdate())")
	String store8(String store_id);


	/**
	 * 8 服务前退款实收金额
	 * @param store_id
	 * @return
	 */
	@Select("SELECT sum(pay_price) FROM t_order WHERE pay_success=1 and  service_down=0  and  store_id=#{store_id} and appointment_admin_id=#{appointment_admin_id}  AND order_no IN (SELECT order_no FROM t_order_refund WHERE date(refund_time) = curdate())")
	String admin8(String store_id,String appointment_admin_id);


	/**
	 * 9 服务后退款数
	 * @param store_id
	 * @return
	 */
	@Select("SELECT  COUNT(*)  FROM t_order WHERE pay_success=1 AND store_id=#{store_id} AND service_down=1 AND order_no IN (SELECT order_no FROM t_order_refund WHERE date(refund_time) = curdate())")
	String store9(String store_id);


	/**
	 * 9 服务后退款数
	 * @param store_id
	 * @return
	 */
	@Select("SELECT count(*) FROM t_order WHERE pay_success=1 and service_down=1  and  store_id=#{store_id} and admin_id=#{admin_id} AND order_no IN (SELECT order_no FROM t_order_refund WHERE date(refund_time) = curdate())")
	String admin9(String store_id,String admin_id);


	/**
	 * 10 服务后退款实收金额
	 * @param store_id
	 * @return
	 */
	@Select("SELECT  sum(pay_price)  FROM t_order WHERE pay_success=1 AND store_id=#{store_id} AND service_down=1 AND order_no IN (SELECT order_no FROM t_order_refund WHERE date(refund_time) = curdate())")
	String store10(String store_id);

	/**
	 * 10 服务后退款实收金额
	 * @param store_id
	 * @return
	 */
	@Select("SELECT sum(pay_price)  FROM t_order WHERE pay_success=1 and  service_down=1  and  store_id=#{store_id} and admin_id=#{admin_id}  AND order_no IN (SELECT order_no FROM t_order_refund WHERE date(refund_time) = curdate())")
	String admin10(String store_id,String admin_id);


	/**
	 * 11 服务男客数
	 * @param store_id
	 * @return
	 */
	@Select("SELECT COUNT(*)  FROM bos_user.t_consumer_private id  WHERE sex=1 AND id IN (SELECT user_id FROM t_order WHERE pay_success=1 and date(serve_start_time) = curdate() and  store_id=#{store_id} )")
	String store11(String store_id);


	/**
	 * 11 服务男客数
	 * @param store_id
	 * @return
	 */
	@Select("SELECT COUNT(*)  FROM bos_user.t_consumer_private id  WHERE sex=1 AND id IN (SELECT user_id FROM t_order WHERE pay_success=1 and date(serve_start_time) = curdate()   and  store_id=#{store_id} and admin_id=#{admin_id})")
	String admin11(String store_id,String admin_id);



	/**
	 * 12 服务女客数
	 * @param store_id
	 * @return
	 */
	@Select("SELECT COUNT(*)  FROM bos_user.t_consumer_private id  WHERE sex=0 AND id IN (SELECT user_id FROM t_order WHERE pay_success=1 and date(serve_start_time) = curdate()  and  store_id=#{store_id})")
	String store12(String store_id);


	/**
	 * 12 服务女客数
	 * @param store_id
	 * @return
	 */
	@Select("SELECT COUNT(*)  FROM bos_user.t_consumer_private id  WHERE sex=0 AND id IN (SELECT user_id FROM t_order WHERE pay_success=1 and date(serve_start_time) = curdate()   and  store_id=#{store_id} and admin_id=#{admin_id})")
	String admin12(String store_id,String admin_id);


	/**
	 * 13 今日产生评价数
	 * @param store_id
	 * @return
	 */
	@Select("SELECT COUNT(*) FROM bos_comments.t_comments WHERE date(created_time) = curdate() AND store_id=#{store_id} ")
	String store13(String store_id);

	/**
	 * 13 今日产生评价数
	 * @param store_id
	 * @param admin_id
	 * @return
	 */
	@Select("SELECT COUNT(*) FROM bos_comments.t_comments WHERE date(created_time) = curdate() AND store_id=#{store_id} AND admin_id=#{admin_id}")
	String admin13(String store_id,String admin_id);


	/**
	 *
	 * 14 1-3星差评数
	 * @param store_id
	 * @return
	 */
	@Select("SELECT COUNT(*)  FROM bos_comments.t_comments WHERE date(created_time) = curdate() AND store_id=#{store_id} and comment_star IN (1,2,3)")
	String store14(String store_id);


	/**
	 *
	 * 14 1-3星差评数
	 * @param store_id
	 * @param admin_id
	 * @return
	 */
	@Select("SELECT COUNT(*) FROM bos_comments.t_comments WHERE date(created_time) = curdate() AND store_id=#{store_id} AND admin_id=#{admin_id} and comment_star IN (1,2,3)")
	String admin14(String store_id,String admin_id);
}