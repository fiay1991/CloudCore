package com.park.cloudcore.dao.order;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.park.cloudcore.domain.order.OrderEnExRecordDomain;

@Repository(value="orderEnExRecordDao")
public interface OrderEnExRecordDao {

    /**
     * 订单进出场记录表插入数据
     * @param orderEnExRecord
     * @return
     */
    public int insertOrderEnExRecord(Map<String, String> paramMap);

    /**
     * 订单进出场记录表查询多条数据
     * @param paramMap
     * @return
     */
    public List<OrderEnExRecordDomain> selectOrderEnExRecords(Map<String, String> paramMap);

    /**
     * 订单进出场记录表更新数据
     * @param orderEnExRecord
     * @return
     */
    public int updateOrderEnExRecord(Map<String, String> paramMap);
    
}
