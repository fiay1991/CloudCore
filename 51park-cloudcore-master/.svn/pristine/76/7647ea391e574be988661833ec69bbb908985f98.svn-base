package com.park.cloudcore.service.order.impl;

import java.util.HashMap;
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
import com.park.cloudcore.dao.order.OrderEnExRecordDao;
import com.park.cloudcore.domain.order.OrderEnExRecordDomain;
import com.park.cloudcore.domain.order.OrderModifyRecordDomain;
import com.park.cloudcore.service.order.OrderEnExRecordService;

/**
 * 
 * @author fangct on 20171129
 *
 */
@Repository(value="orderEnExRecordServiceImpl")
public class OrderEnExRecordServiceImpl implements OrderEnExRecordService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Resource(name="orderEnExRecordDao")
    private OrderEnExRecordDao orderEnExRecordDao;
    
    public String addOrderEnExRecord(String params) {
        try {
            OrderEnExRecordDomain orderEnExRecord = DataChangeTools.gson2bean(params, OrderEnExRecordDomain.class);
            // 校验必填参数
            String recordType = orderEnExRecord.getRecordType();
            String orderNum = orderEnExRecord.getOrderNum();
            String parkId = orderEnExRecord.getParkId();
            String deviceType = orderEnExRecord.getDeviceType();
            if(null == recordType || "".equals(recordType) || 
                    null == orderNum || "".equals(orderNum) || 
                    null == parkId || "".equals(parkId) || 
                    null == deviceType || "".equals(deviceType)) {
                logger.info("** 新增进出场记录 - 必填参数为空：recordType=" + 
                    recordType + "，orderNum=" + orderNum + "，parkId="
                    + parkId + "，deviceType=" + deviceType);
                return ResultTools.setResponse(MyCodeConstants.ERROR_400);
            }
            int result = orderEnExRecordDao.insertOrderEnExRecord(orderEnExRecord);
            boolean success = PublicHandleTools.logOperDbResult(result, "新增进出场记录", orderEnExRecord.getOrderNum());
            // 保存失败
            if(!success) {
                return ResultTools.setResponse(MyCodeConstants.ERROR_406);
            }
            return ResultTools.setResponse(MyCodeConstants.SUCCESS);
        } catch (Exception e) {
            logger.info("** 新增进出场记录 - 保存出现异常：" + e.getMessage());
            e.printStackTrace();
            return ResultTools.setResponse(MyCodeConstants.ERROR);
        }
    }

    public String findOrderEnExRecords(String params) {
        try {
            Map<String, String> paramMap = DataChangeTools.json2Map(params);
            List<OrderModifyRecordDomain> orderEnExRecordList = orderEnExRecordDao.selectOrderEnExRecords(paramMap);
            return ResultTools.setObjectResponse(MyCodeConstants.SUCCESS, orderEnExRecordList);
        } catch (Exception e) {
            logger.info("** 查询进出场记录列表 - 查询出现异常：" + e.getMessage());
            e.printStackTrace();
            return ResultTools.setResponse(MyCodeConstants.ERROR);
        }
    }

    public String modOrderEnExRecord(String params) {
        try {
            OrderEnExRecordDomain orderEnExRecord = DataChangeTools.gson2bean(params, OrderEnExRecordDomain.class);
            // 校验必填参数
            String orderNum = orderEnExRecord.getOrderNum();
            String parkId = orderEnExRecord.getParkId();
            String plateNumber = orderEnExRecord.getPlateNumber();
            if(null == orderNum || "".equals(orderNum) || 
                    null == parkId || "".equals(parkId) || 
                    null == plateNumber || "".equals(plateNumber)) {
                logger.info("** 修改进出场记录 - 必填参数为空：orderNum=" +
                    orderNum + "，parkId=" + parkId + "，plateNumber"
                    + plateNumber);
                return ResultTools.setResponse(MyCodeConstants.ERROR_400);
            }
            int result = orderEnExRecordDao.updateOrderEnExRecord(orderEnExRecord);
            boolean success = PublicHandleTools.logOperDbResult(result, "修改进出场记录", orderEnExRecord.getOrderNum());
            // 修改失败
            if(!success) {
                return ResultTools.setResponse(MyCodeConstants.ERROR_404);
            }
            // 修改成功，返回正常数据
            Map<String, String> resultMap = new HashMap<String, String>();
            resultMap.put("orderNum", orderNum);
            return ResultTools.setObjectResponse(MyCodeConstants.SUCCESS, resultMap);
        } catch (Exception e) {
            logger.info("** 修改进出场记录 - 修改出现异常：" + e.getMessage());
            e.printStackTrace();
            return ResultTools.setResponse(MyCodeConstants.ERROR);
        }
    }

}
