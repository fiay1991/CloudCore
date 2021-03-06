package com.park.cloudcore.dao.order;

import com.park.cloudcore.domain.order.OrderModifyRecordDomain;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 此Dao用作对cp_order_modify_record表的操作
 * @author fangct on 20171129
 *
 */
@Repository(value="orderModifyRecordDao")
public interface OrderModifyRecordDao {
    
    /**
     * 车牌校正记录表插入数据
     * @param orderModifyRecord
     * @return
     */
    public int insertOrderModifyRecord(Map<String, String> paramMap);

    /**
     * 车牌校正记录表查询数据列表
     * @param paramMap
     */
    public List<OrderModifyRecordDomain> selectOrderModifyRecords(Map<String, String> paramMap);
    
}
