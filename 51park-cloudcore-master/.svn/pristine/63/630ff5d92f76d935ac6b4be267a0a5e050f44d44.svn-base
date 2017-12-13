package com.park.cloudcore.service.order.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.park.base.common.DataChangeTools;
import com.park.base.common.DateTools;
import com.park.base.common.RandomTools;
import com.park.base.common.ResultTools;
import com.park.base.common.ToolsUtil;
import com.park.cloudcore.common.PublicHandleTools;
import com.park.cloudcore.common.constants.MyCodeConstants;
import com.park.cloudcore.dao.order.OrderPayRecordDao;
import com.park.cloudcore.domain.order.OrderPayRecordDomain;
import com.park.cloudcore.service.order.OrderPayRecordService;

/**
 * 
 * @author fangct on 20171129
 *
 */
@Repository(value="orderPayRecordServiceImpl")
public class OrderPayRecordServiceImpl implements OrderPayRecordService {
    
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name="orderPayRecordDao")
    private OrderPayRecordDao orderPayRecordDao;
    
    public String addOrderPayRecord(String params) {
        try {
            OrderPayRecordDomain orderPayRecord = DataChangeTools.gson2bean(params, OrderPayRecordDomain.class);
            // 校验必填参数
            String parkId = orderPayRecord.getParkId();
            String orderNum = orderPayRecord.getOrderNum();
            String costBefore = orderPayRecord.getCostBefore();
            String costAfter = orderPayRecord.getCostAfter();
            String payStatus = orderPayRecord.getPayStatus();
            if(ToolsUtil.isNull(parkId) || ToolsUtil.isNull(orderNum) ||
                    ToolsUtil.isNull(costAfter) || ToolsUtil.isNull(costBefore) ||
                    ToolsUtil.isNull(payStatus)) {
                logger.info("** 新增交易流水 - 必填参数为空：parkId=" + parkId + ", orderNum=" + orderNum
                        + "，costBefore=" + costBefore + "，payStatus=" +
                        payStatus + "，costAfter=" + costAfter);
                return ResultTools.setResponse(MyCodeConstants.ERROR_400);
            }
            // 校验交易流水号是否为空，若空，则自动生成
            String tradeNo = orderPayRecord.getTradeNo();
            if(null == tradeNo || "".equals(tradeNo)) {
                tradeNo = RandomTools.GenerateOrderNum();
                orderPayRecord.setTradeNo(tradeNo);
            }
            int result = orderPayRecordDao.insertOrderPayRecord(orderPayRecord);
            boolean success = PublicHandleTools.logOperDbResult(result, "新增交易流水", tradeNo);
            // 保存失败
            if(!success) {
                return ResultTools.setResponse(MyCodeConstants.ERROR_406);
            }
            // 返回正常数据
            Map<String, String> resultMap = new HashMap<String, String>();
            resultMap.put("orderNum", orderNum);
            resultMap.put("tradeNo", tradeNo);
            return ResultTools.setObjectResponse(MyCodeConstants.SUCCESS, resultMap);
        } catch (Exception e) {
            logger.info("** 新增交易流水 - 保存出现异常：" + e.getMessage());
            e.printStackTrace();
            return ResultTools.setResponse(MyCodeConstants.ERROR);
        }
    }

    public String modBase(String params) {
        try {
            Map<String, String> paramMap = DataChangeTools.json2Map(params);
            // 校验必填参数
            String tradeNo = paramMap.get("tradeNo");
            String payTime = paramMap.get("payTime");
            if(ToolsUtil.isNull(tradeNo) || ToolsUtil.isNull(payTime)) {
                logger.info("** 修改交易流水基本信息 - 必填参数为空：", paramMap);
                return ResultTools.setResponse(MyCodeConstants.ERROR_400);
            }
            // 校验支付时间是否为空，如果为空，则自动生成
            if(null == payTime || "".equals(payTime)) {
                paramMap.put("payTime", DateTools.getCurrentSecond());
            }
            int result = orderPayRecordDao.updateOrderPayRecord(paramMap);
            boolean success = PublicHandleTools.logOperDbResult(result, "修改交易流水基本信息", tradeNo);
            // 保存失败
            if(!success) {
                return ResultTools.setResponse(MyCodeConstants.ERROR_404);
            }
            // 返回成功
            return ResultTools.setResponse(MyCodeConstants.SUCCESS);
        } catch (Exception e) {
            logger.info("** 修改交易流水基本信息 - 修改出现异常：" + e.getMessage());
            e.printStackTrace();
            return ResultTools.setResponse(MyCodeConstants.ERROR);
        }
    }

    public String modStatus(String params) {
        try {
            Map<String, String> paramMap = DataChangeTools.json2Map(params);
            // 校验必填参数
            String orderNum = paramMap.get("orderNum");
            String tradeNo = paramMap.get("tradeNo");
            String oldPayStatus = paramMap.get("oldPayStatus");
            String newPayStatus = paramMap.get("newPayStatus");
            if((ToolsUtil.isNull(tradeNo) && ToolsUtil.isNull(orderNum)) || ToolsUtil.isNull(newPayStatus)) {
                logger.info("** 修改交易流水 - 必填参数为空：tradeNo={}, orderNum={}, newPayStatus=" + newPayStatus, tradeNo, orderNum);
                return ResultTools.setResponse(MyCodeConstants.ERROR_400);
            }
            // 校验支付时间是否为空，如果为空，则自动生成
            String payTime = paramMap.get("payTime");
            if(null == payTime || "".equals(payTime)) {
                paramMap.put("payTime", DateTools.getCurrentSecond());
            }
            int result = orderPayRecordDao.updateOrderPayRecord(paramMap);
            boolean success = PublicHandleTools.logOperDbResult(result, "修改交易流水状态", tradeNo);
            // 保存失败
            if(!success) {
                return ResultTools.setResponse(MyCodeConstants.ERROR_404);
            }
            // 返回成功
            return ResultTools.setResponse(MyCodeConstants.SUCCESS);
        } catch (Exception e) {
            logger.info("** 修改交易流水 - 修改出现异常：" + e.getMessage());
            e.printStackTrace();
            return ResultTools.setResponse(MyCodeConstants.ERROR);
        }
    }

    public String findOrderPayRecord(String params) {
        try {
            Map<String, String> paramMap = DataChangeTools.json2Map(params);
            // 校验参数
            String id = paramMap.get("id");
            String tradeNo = paramMap.get("tradeNo");
            if(ToolsUtil.isNull(id) && ToolsUtil.isNull(tradeNo)) {
                logger.info("** 查询交易流水 - 参数全空：id=" + id +
                        "，tradeNo=" + tradeNo);
                return ResultTools.setResponse(MyCodeConstants.ERROR_400);
            }
            OrderPayRecordDomain orderPayRecord = orderPayRecordDao.selectOrderPayRecord(paramMap);
            if(null == orderPayRecord) {
                logger.info("** 查询交易流水 - 查询成功：无数据, id:{}, tradeNo:{}", id, tradeNo);
                return ResultTools.setResponse(MyCodeConstants.SUCCESS);
            }
            return ResultTools.setObjectResponse(MyCodeConstants.SUCCESS, orderPayRecord);
        } catch (Exception e) {
            logger.info("** 查询交易流水 - 查询出现异常：" + e.getMessage());
            e.printStackTrace();
            return ResultTools.setResponse(MyCodeConstants.ERROR);
        }
    }

    public String sumOrderPayRecord(String params) {
        Map<String, String> paramMap = DataChangeTools.json2Map(params);
        Map<String, String> resultMap = orderPayRecordDao.sumOrderPayRecord(paramMap);
        return ResultTools.setObjectResponse(MyCodeConstants.SUCCESS, resultMap);
    }
    
}
