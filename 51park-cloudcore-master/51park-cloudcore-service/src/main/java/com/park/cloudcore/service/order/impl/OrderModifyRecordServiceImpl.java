package com.park.cloudcore.service.order.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.park.base.common.DataChangeTools;
import com.park.base.common.ResultTools;
import com.park.cloudcore.common.PublicHandleTools;
import com.park.cloudcore.common.constants.MyCodeConstants;
import com.park.cloudcore.dao.order.OrderModifyRecordDao;
import com.park.cloudcore.domain.order.OrderModifyRecordDomain;
import com.park.cloudcore.service.order.OrderModifyRecordService;

/**
 * 
 * @author fangct on 20171129
 *
 */
@Repository(value="orderModifyRecordServiceImpl")
public class OrderModifyRecordServiceImpl implements OrderModifyRecordService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Resource(name="orderModifyRecordDao")
    private OrderModifyRecordDao orderModifyRecordDao;
    
    public String addOrderModifyRecord(String params) {
        try {
            OrderModifyRecordDomain orderModifyRecord = DataChangeTools.gson2bean(params, OrderModifyRecordDomain.class);
            // 校验必填参数
            String action = orderModifyRecord.getAction();
            String parkId = orderModifyRecord.getParkId();
            String orderNum = orderModifyRecord.getOrderNum();
            if(null == action || "".equals(action) || 
                    null == parkId || "".equals(parkId) || 
                    null == orderNum || "".equals(orderNum)) {
                logger.info("** 新增车牌校正记录 - 必填参数为空：action="
                        + action + "，parkId=" + parkId + "，orderNum"
                        + orderNum);
                return ResultTools.setResponse(MyCodeConstants.ERROR_400);
            }
            int result = orderModifyRecordDao.insertOrderModifyRecord(orderModifyRecord);
            boolean success = PublicHandleTools.logOperDbResult(result, "", orderModifyRecord.toString());
            // 保存失败
            if(!success) {
                return ResultTools.setResponse(MyCodeConstants.ERROR_406);
            }
            // 保存成功
            return ResultTools.setResponse(MyCodeConstants.SUCCESS);
        } catch (Exception e) {
            logger.info("** 新增车牌校正记录 - 保存出现异常：" + e.getMessage());
            e.printStackTrace();
            return ResultTools.setResponse(MyCodeConstants.ERROR);
        }
    }

    public String findOrderModifyRecords(String params) {
        try {
            Map<String, String> paramMap = DataChangeTools.json2Map(params);
            List<OrderModifyRecordDomain> orderModifyRecordList = orderModifyRecordDao.selectOrderModifyRecords(paramMap);
            return ResultTools.setObjectResponse(MyCodeConstants.SUCCESS, orderModifyRecordList);
        } catch (Exception e) {
            logger.info("** 查询车牌校正记录列表 - 查询出现异常：" + e.getMessage());
            e.printStackTrace();
            return ResultTools.setResponse(MyCodeConstants.ERROR);
        }
    }

}
