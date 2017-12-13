package com.park.cloudcore.service.order.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.park.base.common.DataChangeTools;
import com.park.base.common.RandomTools;
import com.park.base.common.ResultTools;
import com.park.base.common.ToolsUtil;
import com.park.cloudcore.common.PublicHandleTools;
import com.park.cloudcore.common.constants.MyCodeConstants;
import com.park.cloudcore.common.constants.StatusConstants;
import com.park.cloudcore.dao.order.OrderInfoDao;
import com.park.cloudcore.domain.order.OrderInfoDomain;
import com.park.cloudcore.service.order.OrderInfoService;

/**
 * @author fangct on 20171129
 */
@Repository(value = "orderInfoServiceImpl")
public class OrderInfoServiceImpl implements OrderInfoService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name = "orderInfoDao")
    private OrderInfoDao orderInfoDao;

    public String addOrderInfo(String params) {
        try {
            OrderInfoDomain orderInfo = DataChangeTools.gson2bean(params, OrderInfoDomain.class);
            // 校验必填参数
            String parkId = orderInfo.getParkId();
            String enterTime = orderInfo.getEnterTime();
            String plateNumber = orderInfo.getPlateNumber();
            String serviceStatus = orderInfo.getServiceStatus();
            if (ToolsUtil.isNull(parkId) ||ToolsUtil.isNull(enterTime) ||
            		ToolsUtil.isNull(plateNumber) || ToolsUtil.isNull(serviceStatus)) {
                logger.info("** 新增订单 - 必填参数为空：parkId=" + parkId + "，enterTime=" + enterTime
                		+ "，plateNumber=" + plateNumber + "，serviceStatus=" + serviceStatus);
                return ResultTools.setResponse(MyCodeConstants.ERROR_400);
            }
            // 判断数据库订单是否存在相同车辆，存在的话就更新状态为：异常
            if (StatusConstants.SERVICE_ENTER.equals(serviceStatus)) {
                OrderInfoDomain selectWhere = new OrderInfoDomain();
                selectWhere.setPlateNumber(orderInfo.getPlateNumber());
                selectWhere.setParkId(orderInfo.getParkId());
                selectWhere.setServiceStatus(StatusConstants.SERVICE_ENTER);
                List<OrderInfoDomain> orderInfoEnters = orderInfoDao.selectOrdersInfoByCarinfo(selectWhere);
                // 如果存在，将订单信息插入出场订单表，并删除数据
                if (null != orderInfoEnters && orderInfoEnters.size() != 0) {
                    /**
                     * 修改订单状态为取消
                     */
                    for (int i = 0; i < orderInfoEnters.size(); i++) {
                        OrderInfoDomain orderEnter = orderInfoEnters.get(i);
                        orderEnter.setServiceStatus("3");
                        int operRecords = orderInfoDao.updateOrderInfo(orderEnter);
                        PublicHandleTools.logOperDbResult(operRecords, "更新订单状态为异常", orderEnter.getOrderNum());
                    }
                }
            }
            // 校验订单号是否为空，若为空，则自动生成
            String orderNum = orderInfo.getOrderNum();
            if (null == orderNum || "".equals(orderNum)) {
                orderNum = RandomTools.GenerateOrderNum();
                orderInfo.setOrderNum(orderNum);
            }
            // 插入数据库并返回结果
            int insertResult = orderInfoDao.insertOrderInfo(orderInfo);
            boolean success = PublicHandleTools.logOperDbResult(insertResult, "新增订单", orderInfo.toString());
            // 保存失败
            if (!success) {
                return ResultTools.setResponse(MyCodeConstants.ERROR_406);
            }
            // 保存成功，返回正常数据
            Map<String, String> resultMap = new HashMap<String, String>();
            resultMap.put("orderNum", orderNum);
            return ResultTools.setObjectResponse(MyCodeConstants.SUCCESS, resultMap);
        } catch (Exception e) {
            logger.info("** 新增订单 - 保存出现异常：" + e.getMessage());
            e.printStackTrace();
            return ResultTools.setResponse(MyCodeConstants.ERROR);
        }
    }

    public String modOrderInfoBase(String params) {
        try {
            OrderInfoDomain orderInfo = DataChangeTools.gson2bean(params, OrderInfoDomain.class);
            // 校验必填参数
            String orderNum = orderInfo.getOrderNum();
            if (null == orderNum || "".equals(orderNum)) {
                logger.info("** 修改订单基本信息 - 必填参数为空：orderNum=" + orderNum);
                return ResultTools.setResponse(MyCodeConstants.ERROR_400);
            }
            int result = orderInfoDao.updateOrderInfo(orderInfo);
            boolean success = PublicHandleTools.logOperDbResult(result, "修改订单基本信息", orderInfo.toString());
            // 修改失败
            if (!success) {
                return ResultTools.setResponse(MyCodeConstants.ERROR_404);
            }
            // 返回正确数据
            Map<String, String> resultMap = new HashMap<String, String>();
            resultMap.put("orderNum", orderNum);
            return ResultTools.setObjectResponse(MyCodeConstants.SUCCESS, resultMap);
        } catch (Exception e) {
            logger.info("** 修改订单基本信息 - 修改出现异常：" + e.getMessage());
            e.printStackTrace();
            return ResultTools.setResponse(MyCodeConstants.ERROR);
        }
    }

    public String modOrderInfoCost(String params) {
        try {
            OrderInfoDomain orderInfo = DataChangeTools.gson2bean(params, OrderInfoDomain.class);
            // 校验必选参数
            String orderNum = orderInfo.getOrderNum();
            String costBefore = orderInfo.getCostBefore();
            String costAfter = orderInfo.getCostAfter();
            String prepayAmount = orderInfo.getPrepayAmount();
            String prepayTime = orderInfo.getPrepayTime();
            if (ToolsUtil.isNull(orderNum) || ToolsUtil.isNull(prepayTime) ||
                    ToolsUtil.isNull(costAfter) || ToolsUtil.isNull(costBefore) ||
                    ToolsUtil.isNull(prepayAmount)) {
                logger.info("** 修改订单金额 - 必选参数为空：{}", orderInfo.toString());
                return ResultTools.setResponse(MyCodeConstants.ERROR_400);
            }
            int result = orderInfoDao.updateOrderInfo(orderInfo);
            boolean success = PublicHandleTools.logOperDbResult(result, "修改订单金额", orderInfo.toString());
            // 修改失败
            if (!success) {
                return ResultTools.setResponse(MyCodeConstants.ERROR_404);
            }
            // 返回正确数据
            Map<String, String> resultMap = new HashMap<String, String>();
            resultMap.put("orderNum", orderNum);
            return ResultTools.setObjectResponse(MyCodeConstants.SUCCESS, resultMap);
        } catch (Exception e) {
            logger.info("** 修改订单金额 - 修改出现异常：" + e.getMessage());
            e.printStackTrace();
            return ResultTools.setResponse(MyCodeConstants.ERROR);
        }
    }

    public String findOrderInfo(String params) {
        try {
            OrderInfoDomain orderInfoDomain = DataChangeTools.gson2bean(params, OrderInfoDomain.class);
            String id = orderInfoDomain.getId();
            String orderNum = orderInfoDomain.getOrderNum();
            String localOrderId = orderInfoDomain.getLocalOrderId();
            // 校验必填参数
            // 如果id不为空，则置空所有其它参数
            if (ToolsUtil.isNull(id) && ToolsUtil.isNull(orderNum) && ToolsUtil.isNull(localOrderId)) {
                logger.info("** 查询订单 - 必填参数为空：id=" + id + "orderNum=" + orderNum
                        + "，localOrderId=" + localOrderId);
                return ResultTools.setResponse(MyCodeConstants.ERROR_400);
            }
            OrderInfoDomain orderInfo = orderInfoDao.selectOrderInfo(orderInfoDomain);
            if (null == orderInfo) {
                logger.info("** 查询订单 - 查询成功：无结果, 参数：id=" + id + "orderNum=" + orderNum
                        + "，localOrderId=" + localOrderId);
                return ResultTools.setResponse(MyCodeConstants.SUCCESS);
            }
            return ResultTools.setObjectResponse(MyCodeConstants.SUCCESS, orderInfo);
        } catch (Exception e) {
            logger.info("** 查询订单 - 查询出现异常：" + e.getMessage());
            e.printStackTrace();
            return ResultTools.setResponse(MyCodeConstants.ERROR);
        }
    }

    public String findOrderInfosByCarinfo(String params) {
        try {
            OrderInfoDomain orderInfoDomain = DataChangeTools.gson2bean(params, OrderInfoDomain.class);
            // 校验必填参数
            String parkId = orderInfoDomain.getParkId();
            if (ToolsUtil.isNull(parkId)) {
                logger.info("** 查询订单 - 必填参数为空：parkId=" + parkId);
                return ResultTools.setResponse(MyCodeConstants.ERROR_400);
            }
            String plateNumber = orderInfoDomain.getPlateNumber();
            String cardId = orderInfoDomain.getCardId();
            String ticketId = orderInfoDomain.getTicketId();
            if (ToolsUtil.isNull(plateNumber) && ToolsUtil.isNull(cardId) &&
                    ToolsUtil.isNull(ticketId)) {
                logger.info("** 查询订单 - 必选参数为空：plateNumber=" + plateNumber
                        + "，cardId=" + cardId + "，ticketId=" + ticketId);
                return ResultTools.setResponse(MyCodeConstants.ERROR_400);
            }
            List<OrderInfoDomain> orderInfos = orderInfoDao.selectOrdersInfoByCarinfo(orderInfoDomain);
            if (null == orderInfos) {
                logger.info("** 查询订单 - 查询成功：无结果, plateNumber: " + plateNumber);
                return ResultTools.setResponse(MyCodeConstants.SUCCESS);
            }
            return ResultTools.setObjectResponse(MyCodeConstants.SUCCESS, orderInfos);
        } catch (Exception e) {
            logger.info("** 查询订单 - 查询出现异常：" + e.getMessage());
            e.printStackTrace();
            return ResultTools.setResponse(MyCodeConstants.ERROR);
        }
    }

    public String findOrderInfos(String params) {
        try {
            Map<String, String> paramMap = DataChangeTools.json2Map(params);
            // 校验必填数据
            String parkId = paramMap.get("parkId");
            if (null == parkId || "".equals(parkId)) {
                logger.info("** 查询订单列表 - 必填参数为空：parkId=" + parkId);
                return ResultTools.setResponse(MyCodeConstants.ERROR_400);
            }
            // 执行查询
            List<OrderInfoDomain> orderInfoList = orderInfoDao.selectOrderInfos(paramMap);
            return ResultTools.setObjectResponse(MyCodeConstants.SUCCESS, orderInfoList);
        } catch (Exception e) {
            logger.info("** 查询订单列表 - 查询出现异常：" + e.getMessage());
            e.printStackTrace();
            return ResultTools.setResponse(MyCodeConstants.ERROR);
        }
    }

    public String countOrderInfo(String params) {
        try {
            Map<String, String> paramMap = DataChangeTools.json2Map(params);
            // 校验必填参数
            String parkId = paramMap.get("parkId");
            if (null == parkId || "".equals(parkId)) {
                logger.info("** 查询订单数量 - 必填参数为空：" + paramMap);
                return ResultTools.setResponse(MyCodeConstants.ERROR_400);
            }
            // 查询数据并返回结果
            int countResult = orderInfoDao.selectOrderCount(paramMap);
            Map<String, Integer> resultMap = new HashMap<String, Integer>();
            resultMap.put("orderCount", countResult);
            return ResultTools.setObjectResponse(MyCodeConstants.SUCCESS, resultMap);
        } catch (Exception e) {
            logger.info("** 查询订单数量 - 查询出现异常：" + e.getMessage());
            e.printStackTrace();
            return ResultTools.setResponse(MyCodeConstants.ERROR);
        }
    }

}
