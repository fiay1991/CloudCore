package com.park.cloudcore.service.order;

import org.springframework.stereotype.Repository;

/**
 * 
 * @author fangct on 20171129
 *
 */
@Repository(value="orderPayRecordService")
public interface OrderPayRecordService {
    
    /**
     * 新增交易流水
     * @param params
     * @return
     */
    public String addOrderPayRecord(String params);

    /**
     * 修改交易流水基本信息
     * @param params
     * @return
     */
    public String modBase(String params);

    /**
     * 修改交易流水状态
     * @param params
     * @return
     */
    public String modStatus(String params);

    /**
     * 查询交易流水详情
     * @param params
     * @return
     */
    public String findOrderPayRecord(String params);

    /**
     * 统计实收笔数、实收金额、优惠笔数、优惠金额
     * @param params
     * @return
     */
    public String sumOrderPayRecord(String params);
    
}
