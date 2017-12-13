package com.park.cloudcore.service.payment.impl;

import java.math.BigDecimal;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.park.base.common.DataChangeTools;
import com.park.base.common.ResultTools;
import com.park.cloudcore.common.MyHttpTools;
import com.park.cloudcore.common.constants.MyCodeConstants;
import com.park.cloudcore.dao.order.OrderInfoDao;
import com.park.cloudcore.domain.response.ObjectResponse;
import com.park.cloudcore.properites.KeysConfig;
import com.park.cloudcore.properites.Spring;
import com.park.cloudcore.properites.URLConfig;
import com.park.cloudcore.service.payment.PaymentService;

/**
 * 
 * @author fangct on 20171129
 *
 */
@Repository(value="paymentServiceImpl")
public class PaymentServiceImpl implements PaymentService {
    
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Resource(name="orderInfoDao")
    private OrderInfoDao orderInfoDao;
    
    @Autowired
    private URLConfig uRLConfig;

    public String query(String params) {
        try {
            // 验证必填参数
            Map<String, String> paramMap = DataChangeTools.json2Map(params);
            String parkId = paramMap.get("parkId");
            String plateNumber = paramMap.get("plateNumber");
            String orderNum = paramMap.get("orderNum");
            if(null == parkId || "".equals(parkId) || 
                    (null == plateNumber || "".equals(plateNumber) && 
                    null == orderNum || "".equals(orderNum))) {
                logger.info("** 缴费查询 - 必填参数为空：parkId=" + parkId + "，plateNumber=" 
                        + plateNumber + "，orderNum=" + orderNum);
                return ResultTools.setResponse(MyCodeConstants.ERROR_400);
            }
            // 放入参数，获取url，发送请求
//            // 查询订单
//            String enterOrExit = orderInfoDao.selectOrderEnter4Seek(paramMap);
//            String serviceStatus = "1";
//            if(null == enterOrExit || "".equals(enterOrExit)) {
//                enterOrExit = orderExitDao.selectOrderExit4Seek(paramMap);
//                serviceStatus = "2";
//            }
            String url = uRLConfig.getUrl4PayQuery();
            KeysConfig keysConfig = Spring.getBean("keysConfig");
            String result = MyHttpTools.sendRequest(paramMap, url, keysConfig.getPublicKey());
            logger.info("** 缴费查询，返回结果：" + result);
            if(null == result || "".equals(result)) {
                logger.info("** 缴费查询 - 返回全空。");
                return ResultTools.setResponse(MyCodeConstants.ERROR_503);
            }
            ObjectResponse response = DataChangeTools.gson2bean(result, ObjectResponse.class);
            if(response == null || !response.getCode().equals("200")) {
                logger.info("** 缴费查询 - 返回非成功值：code=" + response.getCode() + 
                        "，msg=" + response.getMsg());
                return ResultTools.setResponse(MyCodeConstants.ERROR_504);
            }
            if(null == response.getData() || response.getData().equals("")) {
                logger.info("** 缴费查询 - 查询成功，无数据。");
                return ResultTools.setResponse(MyCodeConstants.ERROR_505);
            }
            String resultStr = DataChangeTools.bean2gson(response.getData());
            // 整理参数并返回结果
            Map<String, String> resultMap = DataChangeTools.json2Map(resultStr);
            BigDecimal payedPrice = new BigDecimal(resultMap.get("payed_price"));
            BigDecimal unpayPrice = new BigDecimal(resultMap.get("unpay_price"));
            String status = "";
            int unpay = unpayPrice.compareTo(new BigDecimal("0"));
            int payed = payedPrice.compareTo(new BigDecimal("0"));
            if(unpay <= 0) {
                status = "1"; // 已缴费
            }else if(payed <= 0) {
                status = "2"; // 未缴费
            }else {
                status = "3"; // 需补缴
            }
            resultMap.put("status", status);
//            resultMap.put("service_status", serviceStatus);
            logger.info("** 缴费查询 - 缴费查询成功：" + resultMap);
            return ResultTools.setObjectResponse(MyCodeConstants.SUCCESS, resultMap);
        } catch (Exception e) {
            logger.info("** 缴费查询 - 缴费查询出现异常：" + e.getMessage());
            e.printStackTrace();
            return ResultTools.setResponse(MyCodeConstants.ERROR);
        }
    }
    
}
