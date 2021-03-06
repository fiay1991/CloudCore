package com.park.cloudcore.service.order.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.park.base.common.DataChangeTools;
import com.park.base.common.RandomTools;
import com.park.base.common.ResultTools;
import com.park.cloudcore.common.PublicHandleTools;
import com.park.cloudcore.common.ValidateParamsTools;
import com.park.cloudcore.common.constants.Code;
import com.park.cloudcore.common.constants.StatusConstants;
import com.park.cloudcore.common.constants.ValidateType;
import com.park.cloudcore.dao.order.OrderInfoDao;
import com.park.cloudcore.domain.order.OrderInfoDomain;
import com.park.cloudcore.service.order.OrderInfoService;

/**
 * @author fangct on 20171129
 */
@Repository(value = "orderInfoServiceImpl")
public class OrderInfoServiceImpl implements OrderInfoService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private OrderInfoDao orderInfoDao;

	public String addOrderInfo(String params) {
		try {
			Map<String, String> paramMap = DataChangeTools.json2Map(params);
			// 校验参数
			String[] mustParams = { "parkId", "enterTime", "plateNumber", "serviceStatus" };
			if (!ValidateParamsTools.vaildateParams(paramMap, mustParams, ValidateType.必传)) {
				return ResultTools.setResponse(Code.ERROR_400, Code.getName(Code.ERROR_400));
			}
			String parkId = paramMap.get("parkId");
			String plateNumber = paramMap.get("plateNumber");
			String serviceStatus = paramMap.get("serviceStatus");
			// 判断数据库订单是否存在相同车辆，存在的话就更新状态为：异常
			if (StatusConstants.SERVICE_ENTER.equals(serviceStatus)) {
				Map<String, String> selectWhere = new HashMap<String, String>();
				selectWhere.put("plateNumber", plateNumber);
				selectWhere.put("parkId", parkId);
				selectWhere.put("serviceStatus", StatusConstants.SERVICE_ENTER);
				List<OrderInfoDomain> orderInfoEnters = orderInfoDao.selectOrdersInfoByCarinfo(selectWhere);
				// 如果存在，将订单信息插入出场订单表，并删除数据
				if (null != orderInfoEnters && orderInfoEnters.size() != 0) {
					/**
					 * 修改订单状态为异常
					 */
					for (int i = 0; i < orderInfoEnters.size(); i++) {
						OrderInfoDomain orderEnter = orderInfoEnters.get(i);
						Map<String, String> updateMap = new HashMap<String, String>();
						updateMap.put("orderNum", orderEnter.getOrderNum());
						updateMap.put("serviceStatus", StatusConstants.SERVICE_EXCEPTION);
						int operRecords = orderInfoDao.updateOrderInfo(updateMap);
						PublicHandleTools.operDbResult(operRecords, "更新订单状态为异常", orderEnter.getOrderNum());
					}
				}
			}
			// 校验订单号是否为空，若为空，则自动生成
			String orderNum = paramMap.get("orderNum");
			if (null == orderNum || "".equals(orderNum)) {
				orderNum = RandomTools.GenerateOrderNum();
				paramMap.put("orderNum", orderNum);
			}
			// 插入数据库并返回结果
			int insertResult = orderInfoDao.insertOrderInfo(paramMap);
			boolean success = PublicHandleTools.operDbResult(insertResult, "新增订单", paramMap.toString());
			// 保存失败
			if (!success) {
				return ResultTools.setResponse(Code.ERROR_405, Code.getName(Code.ERROR_405));
			}
			// 保存成功，返回正常数据
			Map<String, String> resultMap = new HashMap<String, String>();
			resultMap.put("orderNum", orderNum);
			return ResultTools.setObjectResponse(Code.SUCCESS, Code.getName(Code.SUCCESS), resultMap);
		} catch (Exception e) {
			logger.info("** 新增订单 - 保存出现异常：" + e.getMessage());
			e.printStackTrace();
			return ResultTools.setResponse(Code.ERROR, Code.getName(Code.ERROR));
		}
	}

	public String modOrderInfoBase(String params) {
		try {
			Map<String, String> paramsMap = DataChangeTools.json2Map(params);
			// 校验参数
			String[] mustParams = { "orderNum" };
			if (!ValidateParamsTools.vaildateParams(paramsMap, mustParams, ValidateType.必传)) {
				return ResultTools.setResponse(Code.ERROR_400, Code.getName(Code.ERROR_400));
			}
			int result = orderInfoDao.updateOrderInfo(paramsMap);
			boolean success = PublicHandleTools.operDbResult(result, "修改订单基本信息", paramsMap.toString());
			// 修改失败
			if (!success) 
				return ResultTools.setResponse(Code.ERROR_404, Code.getName(Code.ERROR_404));
			// 返回正确数据
			Map<String, String> resultMap = new HashMap<String, String>();
			resultMap.put("orderNum", paramsMap.get("orderNum"));
			return ResultTools.setObjectResponse(Code.SUCCESS, Code.getName(Code.SUCCESS), resultMap);
		} catch (Exception e) {
			logger.info("** 修改订单基本信息 - 修改出现异常：" + e.getMessage());
			e.printStackTrace();
			return ResultTools.setResponse(Code.ERROR, Code.getName(Code.ERROR));
		}
	}

	public String modOrderInfoCost(String params) {
		try {
			Map<String, String> paramsMap = DataChangeTools.json2Map(params);
			// 校验参数
			String[] mustParams = { "orderNum", "costAfter", "costBefore" };
			if (!ValidateParamsTools.vaildateParams(paramsMap, mustParams, ValidateType.必传)) {
				return ResultTools.setResponse(Code.ERROR_400, Code.getName(Code.ERROR_400));
			}
			int result = orderInfoDao.updateOrderInfo(paramsMap);
			boolean success = PublicHandleTools.operDbResult(result, "修改订单金额", paramsMap.toString());
			// 修改失败
			if (!success) 
				return ResultTools.setResponse(Code.ERROR_404, Code.getName(Code.ERROR_404));
			// 返回正确数据
			Map<String, String> resultMap = new HashMap<String, String>();
			resultMap.put("orderNum", paramsMap.get("orderNum"));
			return ResultTools.setObjectResponse(Code.SUCCESS, Code.getName(Code.SUCCESS), resultMap);
		} catch (Exception e) {
			logger.info("** 修改订单金额 - 修改出现异常：" + e.getMessage());
			e.printStackTrace();
			return ResultTools.setResponse(Code.ERROR, Code.getName(Code.ERROR));
		}
	}

	public String findOrderInfo(String params) {
		try {
			Map<String, String> paramsMap = DataChangeTools.json2Map(params);
			// 校验参数
			String[] anyoneParams = { "id", "orderNum", "localOrderId" };
			if (!ValidateParamsTools.vaildateParams(paramsMap, anyoneParams, ValidateType.任一必传)) {
				return ResultTools.setResponse(Code.ERROR_400, Code.getName(Code.ERROR_400));
			}
			OrderInfoDomain orderInfo = orderInfoDao.selectOrderInfo(paramsMap);
			if (null == orderInfo) {
				logger.info("** 查询订单 - 查询成功：无结果, 参数：params=" + params);
				return ResultTools.setResponse(Code.ERROR_404, Code.getName(Code.ERROR_404));
			}
			return ResultTools.setObjectResponse(Code.SUCCESS, Code.getName(Code.SUCCESS), orderInfo);
		} catch (Exception e) {
			logger.info("** 查询订单 - 查询出现异常：" + e.getMessage());
			e.printStackTrace();
			return ResultTools.setResponse(Code.ERROR, Code.getName(Code.ERROR));
		}
	}

	public String findOrderInfosByCarinfo(String params) {
		try {
			Map<String, String> orderInfoMap = DataChangeTools.json2Map(params);
			// 校验参数
			String[] anyoneParams = { "plateNumber", "cardId", "ticketId", "localOrderId" };
			if (!ValidateParamsTools.vaildateParams(orderInfoMap, anyoneParams, ValidateType.任一必传)) {
				return ResultTools.setResponse(Code.ERROR_400, Code.getName(Code.ERROR_400));
			}

			//分页参数初始化
			PublicHandleTools.paging(orderInfoMap);
			List<OrderInfoDomain> orderInfos = orderInfoDao.selectOrdersInfoByCarinfo(orderInfoMap);
			if (null == orderInfos || orderInfos.size() == 0) {
				return ResultTools.setResponse(Code.ERROR_404, Code.getName(Code.ERROR_404));
			}
			return ResultTools.setObjectResponse(Code.SUCCESS, Code.getName(Code.SUCCESS), orderInfos);
		} catch (Exception e) {
			logger.info("** 查询订单 - 查询出现异常：" + e.getMessage());
			e.printStackTrace();
			return ResultTools.setResponse(Code.ERROR, Code.getName(Code.ERROR));
		}
	}

	public String findOrderInfos(String params) {
		try {
			Map<String, String> paramMap = DataChangeTools.json2Map(params);
			// 校验参数
			String[] mustParams = { "parkId" };
			if (!ValidateParamsTools.vaildateParams(paramMap, mustParams, ValidateType.必传)) {
				return ResultTools.setResponse(Code.ERROR_400, Code.getName(Code.ERROR_400));
			}
			
			//分页参数初始化
			PublicHandleTools.paging(paramMap);
			// 执行查询
			List<OrderInfoDomain> orderInfoList = orderInfoDao.selectOrderInfos(paramMap);
			if (null == orderInfoList || orderInfoList.size() == 0) {
				return ResultTools.setResponse(Code.ERROR_404, Code.getName(Code.ERROR_404));
			}
			return ResultTools.setObjectResponse(Code.SUCCESS, Code.getName(Code.SUCCESS), orderInfoList);
		} catch (Exception e) {
			logger.info("** 查询订单列表 - 查询出现异常：" + e.getMessage());
			e.printStackTrace();
			return ResultTools.setResponse(Code.ERROR, Code.getName(Code.ERROR));
		}
	}

	public String countOrderInfo(String params) {
		try {
			Map<String, String> paramMap = DataChangeTools.json2Map(params);
			// 校验参数
			String[] mustParams = { "parkId" };
			if (!ValidateParamsTools.vaildateParams(paramMap, mustParams, ValidateType.必传)) {
				return ResultTools.setResponse(Code.ERROR_400, Code.getName(Code.ERROR_400));
			}
			// 查询数据并返回结果
			int countResult = orderInfoDao.selectOrderCount(paramMap);
			Map<String, Integer> resultMap = new HashMap<String, Integer>();
			resultMap.put("orderCount", countResult);
			return ResultTools.setObjectResponse(Code.SUCCESS, Code.getName(Code.SUCCESS), resultMap);
		} catch (Exception e) {
			logger.info("** 查询订单数量 - 查询出现异常：" + e.getMessage());
			e.printStackTrace();
			return ResultTools.setResponse(Code.ERROR, Code.getName(Code.ERROR));
		}
	}

	public String fuzzyCarinfo(String params) {
		// TODO Auto-generated method stub
		return null;
	}

}
