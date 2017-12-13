package com.park.cloudcore.dao.order;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.park.cloudcore.domain.order.OrderPayRecordDomain;

/**
 * 此Dao用作对cp_order_pay_record表的操作
 * @author fangct on 20171129
 *
 */
@Repository(value="orderPayRecordDao")
public interface OrderPayRecordDao {
    
    /**
     * 交易流水表插入数据
     * @param orderPayRecord
     * @return
     */
    public int insertOrderPayRecord(OrderPayRecordDomain orderPayRecord);

    /**
     * 交易流水表更新数据
     * @param paramMap
     * @return
     */
    public int updateOrderPayRecord(Map<String, String> paramMap);

    /**
     * 交易流水表查询数据
     * @param paramMap
     * @return
     */
    public OrderPayRecordDomain selectOrderPayRecord(Map<String, String> paramMap);

    /**
     * 交易流水表查询数据进行统计
     * @param paramMap
     * @return
     */
    public Map<String, String> sumOrderPayRecord(Map<String, String> paramMap);
    
}
