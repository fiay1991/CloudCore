package com.park.cloudcore.service.order;

import org.springframework.stereotype.Repository;

/**
 * 
 * @author fangct on 20171129
 *
 */
@Repository(value="orderModifyRecordService")
public interface OrderModifyRecordService {
    
    /**
     * 新增车牌校正记录
     * @param params
     * @return
     */
    public String addOrderModifyRecord(String params);

    /**
     * 查询车牌校正记录列表
     * @param params
     * @return
     */
    public String findOrderModifyRecords(String params);
    
}
