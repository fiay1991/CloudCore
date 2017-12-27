package com.park.cloudcore.service.park.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.park.base.common.DataChangeTools;
import com.park.base.common.ResultTools;
import com.park.cloudcore.common.ValidateParamsTools;
import com.park.cloudcore.common.constants.Code;
import com.park.cloudcore.common.constants.ValidateType;
import com.park.cloudcore.dao.park.ParkInfoDao;
import com.park.cloudcore.domain.park.ParkInfoDomain;
import com.park.cloudcore.service.park.ParkInfoService;

/**
 * @author fangct on 20171224
 */
@Repository(value = "parkInfoServiceImpl")
public class ParkInfoServiceImpl implements ParkInfoService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ParkInfoDao parkInfoDao;

	public String find(String params) {
		try {
			Map<String, String> paramMap = DataChangeTools.json2Map(params);
			// 校验参数
			String[] mustParams = { "parkId" };
			if (!ValidateParamsTools.vaildateParams(paramMap, mustParams, ValidateType.必传)) {
				return ResultTools.setResponse(Code.ERROR_400, Code.getName(Code.ERROR_400));
			}
			String parkId = paramMap.get("parkId");
			ParkInfoDomain domain = parkInfoDao.selectByParkId(parkId);
			if (domain == null) {
				logger.info("未查询到车场信息, parkId: {}", parkId);
				return ResultTools.setResponse(Code.ERROR_404, Code.getName(Code.ERROR_404));
			}else {
				return ResultTools.setObjectResponse(Code.SUCCESS, Code.getName(Code.SUCCESS), domain);
			}
		} catch (Exception e) {
			logger.info("** 查询车辆信息 - 出现异常：" + e.getMessage());
			e.printStackTrace();
			return ResultTools.setResponse(Code.ERROR, Code.getName(Code.ERROR));
		}
	}

}
