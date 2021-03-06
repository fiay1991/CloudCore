package com.park.cloudcore.service.discount.impl;

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
import com.park.base.common.ToolsUtil;
import com.park.cloudcore.common.PublicHandleTools;
import com.park.cloudcore.common.ValidateParamsTools;
import com.park.cloudcore.common.constants.Code;
import com.park.cloudcore.common.constants.ValidateType;
import com.park.cloudcore.dao.discount.DiscountDao;
import com.park.cloudcore.domain.discount.DiscountDomain;
import com.park.cloudcore.service.discount.DiscountService;

/**
 * @author fangct on 20171129
 */
@Repository(value = "discountServiceImpl")
public class DiscountServiceImpl implements DiscountService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DiscountDao discountDao;

	public String addDiscount(String params) {
		try {
			Map<String, String> paramsMap = DataChangeTools.json2Map(params);
			// 校验参数
			String[] mustParams = { "parkId", "orderNum", "type", "fromType", "discountTime" };
			if (!ValidateParamsTools.vaildateParams(paramsMap, mustParams, ValidateType.必传)) {
				return ResultTools.setResponse(Code.ERROR_400, Code.getName(Code.ERROR_400));
			}

			String discountNo = paramsMap.get("discountNo");
			// 如果没有传优惠编号，则生成一个
			if (ToolsUtil.isNull(discountNo)) {
				discountNo = RandomTools.GenerateDiscountNo();
				paramsMap.put("discountNo", discountNo);
			}
			// 插入数据库并返回数据
			int result = discountDao.insertDiscount(paramsMap);
			boolean success = PublicHandleTools.operDbResult(result, "新增优惠", paramsMap.toString());
			if (!success) {
				return ResultTools.setResponse(Code.ERROR_405, Code.getName(Code.ERROR_405));
			}
			Map<String, String> resultMap = new HashMap<String, String>();
			resultMap.put("discountNo", discountNo);
			return ResultTools.setObjectResponse(Code.SUCCESS, Code.getName(Code.SUCCESS), resultMap);
		} catch (Exception e) {
			logger.info("** 新增优惠 - 保存出现异常：" + e.getMessage());
			e.printStackTrace();
			return ResultTools.setResponse(Code.ERROR, Code.getName(Code.ERROR));
		}
	}

	public String modByOrdernum(String params) {
		try {
			Map<String, String> paramsMap = DataChangeTools.json2Map(params);
			// 校验参数
			String[] mustParams = { "orderNum", "newStatus" };
			if (!ValidateParamsTools.vaildateParams(paramsMap, mustParams, ValidateType.必传)) {
				return ResultTools.setResponse(Code.ERROR_400, Code.getName(Code.ERROR_400));
			}
			// 查找对应ID以备日志和返回数据
			List<Map<String, Object>> discountNos = discountDao.selectDiscountNos(paramsMap);
			if (discountNos != null && discountNos.size() > 0) {
				// 更新数据库并返回数据
				int result = discountDao.updateDiscount(paramsMap);
				boolean success = PublicHandleTools.operDbResult(result, "修改优惠", paramsMap.get("orderNum"));
				if (!success) {
					return ResultTools.setResponse(Code.ERROR_405, Code.getName(Code.ERROR_405));
				}
				return ResultTools.setObjectResponse(Code.SUCCESS, Code.getName(Code.SUCCESS), discountNos);
			} else {
				return ResultTools.setResponse(Code.ERROR_404, Code.getName(Code.ERROR_404));
			}
		} catch (Exception e) {
			logger.info("** 修改优惠 - 更新出现异常：" + e.getMessage());
			e.printStackTrace();
			return ResultTools.setResponse(Code.ERROR, Code.getName(Code.ERROR));
		}
	}

	public String modByDiscountNo(String params) {
		try {
			Map<String, String> paramsMap = DataChangeTools.json2Map(params);
			// 校验参数
			String[] mustParams = { "discountNo", "newStatus" };
			if (!ValidateParamsTools.vaildateParams(paramsMap, mustParams, ValidateType.必传)) {
				return ResultTools.setResponse(Code.ERROR_400, Code.getName(Code.ERROR_400));
			}

			int result = discountDao.updateDiscount(paramsMap);
			boolean success = PublicHandleTools.operDbResult(result, "修改优惠", paramsMap.get("orderNum"));
			if (!success)
				return ResultTools.setResponse(Code.ERROR_404, Code.getName(Code.ERROR_404));
			// 返回正确数据
			Map<String, String> resultMap = new HashMap<String, String>();
			resultMap.put("discountNo", paramsMap.get("discountNo"));
			return ResultTools.setObjectResponse(Code.SUCCESS, Code.getName(Code.SUCCESS), resultMap);
		} catch (Exception e) {
			logger.info("** 修改优惠 - 更新出现异常：" + e.getMessage());
			e.printStackTrace();
			return ResultTools.setResponse(Code.ERROR, Code.getName(Code.ERROR));
		}
	}

	public String findDiscounts(String params) {
		try {
			Map<String, String> paramsMap = DataChangeTools.json2Map(params);
			// 校验参数
			String[] mustParams = { "parkId", "orderNum" };
			if (!ValidateParamsTools.vaildateParams(paramsMap, mustParams, ValidateType.任一必传)) {
				return ResultTools.setResponse(Code.ERROR_400, Code.getName(Code.ERROR_400));
			}

			// 分页参数初始化
			PublicHandleTools.paging(paramsMap);
			List<DiscountDomain> discountList = discountDao.selectDiscounts(paramsMap);
			if (discountList == null || discountList.size() == 0)
				return ResultTools.setResponse(Code.ERROR_404, Code.getName(Code.ERROR_404));
			return ResultTools.setObjectResponse(Code.SUCCESS, Code.getName(Code.SUCCESS), discountList);
		} catch (Exception e) {
			logger.info("** 查询优惠列表 - 查询出现异常：" + e.getMessage());
			e.printStackTrace();
			return ResultTools.setResponse(Code.ERROR, Code.getName(Code.ERROR));
		}
	}

	public String findDiscount(String params) {
		try {
			Map<String, String> paramsMap = DataChangeTools.json2Map(params);
			// 校验参数
			String[] mustParams1 = { "id" };
			String[] mustParams2 = { "parkId","discountNo" };
			if (!ValidateParamsTools.vaildateParams(paramsMap, mustParams1, ValidateType.必传)
					&& !ValidateParamsTools.vaildateParams(paramsMap, mustParams2, ValidateType.必传)) {
				return ResultTools.setResponse(Code.ERROR_400, Code.getName(Code.ERROR_400));
			}

			DiscountDomain discount = discountDao.selectDiscount(paramsMap);
			if (discount == null)
				return ResultTools.setResponse(Code.ERROR_404, Code.getName(Code.ERROR_404));
			return ResultTools.setObjectResponse(Code.SUCCESS, Code.getName(Code.SUCCESS), discount);
		} catch (Exception e) {
			logger.info("** 查询优惠 - 查询出现异常：" + e.getMessage());
			e.printStackTrace();
			return ResultTools.setResponse(Code.ERROR, Code.getName(Code.ERROR));
		}
	}
}
