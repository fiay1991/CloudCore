package com.park.cloudcore.service.order.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.park.base.common.DataChangeTools;
import com.park.base.common.ResultTools;
import com.park.cloudcore.common.ValidateParamsTools;
import com.park.cloudcore.common.constants.Code;
import com.park.cloudcore.common.constants.ValidateType;
import com.park.cloudcore.dao.order.OrderDao;
import com.park.cloudcore.service.order.OrderService;

@Repository(value = "orderServiceImpl")
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;

	public String sumTrade(String params) {
		// 校验必填参数
		Map<String, String> paramMap = DataChangeTools.json2Map(params);
		// 校验参数
		String[] mustParams = { "parkId", "payTimeStart", "payTimeEnd" };
		if (!ValidateParamsTools.vaildateParams(paramMap, mustParams, ValidateType.必传)) {
			return ResultTools.setResponse(Code.ERROR_400, Code.getName(Code.ERROR_400));
		}
		Map<String, String> resultMap = orderDao.selectSumTrade(paramMap);
		if(resultMap == null || resultMap.isEmpty())
			return ResultTools.setResponse(Code.ERROR_404, Code.getName(Code.ERROR_404));
		return ResultTools.setObjectResponse(Code.SUCCESS, Code.getName(Code.SUCCESS), resultMap);
	}

}
