package com.park.cloudcore.service.order.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.park.base.common.DataChangeTools;
import com.park.base.common.ResultTools;
import com.park.cloudcore.common.constants.MyCodeConstants;
import com.park.cloudcore.dao.order.OrderDao;
import com.park.cloudcore.service.order.OrderService;

@Repository(value="orderServiceImpl")
public class OrderServiceImpl implements OrderService {
    
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Resource(name="orderDao")
    private OrderDao orderDao;

    public String sumTrade(String params) {
        // 校验必填参数
        Map<String, String> paramMap = DataChangeTools.json2Map(params);
        String parkId = paramMap.get("parkId");
        String payTimeStart = paramMap.get("payTimeStart");
        String payTimeEnd = paramMap.get("payTimeEnd");
        if(null == parkId || "".equals(parkId) || 
                null == payTimeStart || "".equals(payTimeStart) ||
                null == payTimeEnd || "".equals(payTimeEnd)) {
            logger.info("** 查询某日交易合计 - 必填参数为空：parkId=" + parkId + 
                    "，payTimeStart=" + payTimeStart + "，payTimeEnd=" +
                    payTimeEnd);
            return ResultTools.setResponse(MyCodeConstants.ERROR_400);
        }
        Map<String, String> resultMap = orderDao.selectSumTrade(paramMap);
        return ResultTools.setObjectResponse(MyCodeConstants.SUCCESS, resultMap);
    }

}
