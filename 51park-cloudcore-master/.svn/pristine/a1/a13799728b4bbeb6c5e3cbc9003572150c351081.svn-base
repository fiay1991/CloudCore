package com.park.cloudcore.dao.order;

import com.park.cloudcore.domain.order.OrderEnExRecordDomain;
import com.park.cloudcore.domain.order.OrderModifyRecordDomain;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository(value="orderEnExRecordDao")
public interface OrderEnExRecordDao {

    /**
     * 订单进出场记录表插入数据
     * @param orderEnExRecord
     * @return
     */
    public int insertOrderEnExRecord(OrderEnExRecordDomain orderEnExRecord);

    /**
     * 订单进出场记录表查询多条数据
     * @param paramMap
     * @return
     */
    public List<OrderModifyRecordDomain> selectOrderEnExRecords(Map<String, String> paramMap);

    /**
     * 订单进出场记录表更新数据
     * @param orderEnExRecord
     * @return
     */
    public int updateOrderEnExRecord(OrderEnExRecordDomain orderEnExRecord);
    
}
