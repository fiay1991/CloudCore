package com.park.cloudcore.service.order;

import org.springframework.stereotype.Repository;

/**
 * 
 * @author fangct on 20171129
 *
 */
@Repository(value="orderInfoService")
public interface OrderInfoService {
	
	/**
	 * 新增车辆订单
	 * @param params
	 * @return
	 */
	public String addOrderInfo(String params);

    /**
     * 修改车辆订单基本信息
     * @param params
     * @return
     */
    public String modOrderInfoBase(String params);

    /**
     * 修改车辆订单支付金额
     * @param params
     * @return
     */
    public String modOrderInfoCost(String params);

    /**
     * 查询车辆订单
     * @param params
     * @return
     */
    public String findOrderInfo(String params);

    /**
     * 查询车辆订单列表
     * @param params
     * @return
     */
    public String findOrderInfos(String params);

    /**
     * 查询车辆订单列表
     * @param params
     * @return
     */
    public String findOrderInfosByCarinfo(String params);

    /**
     * 查询车辆订单数量
     * @param params
     * @return
     */
    public String countOrderInfo(String params);
	
}
