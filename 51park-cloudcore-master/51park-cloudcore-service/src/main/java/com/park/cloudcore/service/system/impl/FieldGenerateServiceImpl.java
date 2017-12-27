package com.park.cloudcore.service.system.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.park.base.common.DataChangeTools;
import com.park.base.common.ResultTools;
import com.park.base.common.ToolsUtil;
import com.park.cloudcore.common.ValidateParamsTools;
import com.park.cloudcore.common.constants.Code;
import com.park.cloudcore.common.constants.ValidateType;
import com.park.cloudcore.dao.system.FieldGenerateDao;
import com.park.cloudcore.domain.system.FieldGenerateDomain;
import com.park.cloudcore.service.system.FieldGenerateService;

/**
 * @author fangct on 20171225
 */
@Repository(value="fieldGenerateServiceImpl")
public class FieldGenerateServiceImpl implements FieldGenerateService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private FieldGenerateDao fieldGenerateDao;
	
	//临时车牌号最大长度
	private static int PART3_MAX_LENGTH = 6;
	
	/**
	 * 按照规则生成最新的属性值
	 * @param params
	 * @return
	 */
	public String generateNew(String params){
		try {
			Map<String, String> paramMap = DataChangeTools.json2Map(params);
			// 校验参数
			String[] mustParams = { "type" };
			if (!ValidateParamsTools.vaildateParams(paramMap, mustParams, ValidateType.必传)) {
				return ResultTools.setResponse(Code.ERROR_400, Code.getName(Code.ERROR_400));
			}
			String type = paramMap.get("type");
			FieldGenerateDomain domain = fieldGenerateDao.selectByType(type);
			
			if (domain == null) {
				logger.info("未查询到对应属性值的生成记录, type: {}", type);
				return ResultTools.setResponse(Code.ERROR_404, Code.getName(Code.ERROR_404));
			}else {
				//生成新的组成部分，并更新到数据表
				Map<String, String> parts = generateNewValue(domain);
				String part1 = parts.get("part1");
				String part2 = parts.get("part2");
				String part3 = parts.get("part3");
				String temp_plate = part1 + part2 + part3;
				domain.setPart_1(part1);
				domain.setPart_2(part2);
				domain.setPart_3(part3);
				fieldGenerateDao.updateNew(domain);
				
				Map<String, String> resultMap = new HashMap<String, String>();
				resultMap.put("newValue", temp_plate);
				return ResultTools.setObjectResponse(Code.SUCCESS, Code.getName(Code.SUCCESS), resultMap);
			}
		} catch (Exception e) {
			logger.info("** 生成新的属性值 - 出现异常：" + e.getMessage());
			e.printStackTrace();
			return ResultTools.setResponse(Code.ERROR, Code.getName(Code.ERROR));
		}
	}

	private Map<String, String> generateNewValue(FieldGenerateDomain domain){
		Map<String, String> parts = new HashMap<String, String>();
		String[] part2Arr = new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		String part_1 = domain.getPart_1();
		String part_2 = domain.getPart_2();
		String part_3 = domain.getPart_3();
		String newPart1 = part_1,newPart2 = part_2,newPart3 = part_3;
		if (ToolsUtil.isNull(part_1) || ToolsUtil.isNull(part_2) || ToolsUtil.isNull(part_3)) {
			newPart1 = "临";
			newPart2 = "A";
			newPart3 = "000001";
		}else {
			int index = getIndex(part2Arr, part_2);
			int part3 = ToolsUtil.parseInt(part_3) + 1;
			if (part_2.equals(part2Arr[part2Arr.length-1])) {
				if (part_3.length() < ("" + part3).length()) {
					newPart3 = "000001";
					newPart2 = part2Arr[0];
				}else {
					newPart3 = "" + part3;
				}
			}else {
				if (part_3.length() < ("" + part3).length()) {
					newPart3 = "000001";
					newPart2 = part2Arr[index + 1];
				}else {
					newPart3 = "" + part3;
				}
			}
		}
		parts.put("part1", newPart1);
		parts.put("part2", newPart2);
		parts.put("part3", complement6Str(newPart3));
		return parts;
	}
	
	/**
	 * 获取str在arr中的下标
	 * @param arr
	 * @param str
	 * @return
	 */
	private int getIndex(String[] arr , String str){
		for (int i = 0; i < arr.length; i++) {
			if (str.equals(arr[i])) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * 补全6位长度
	 * @param sub
	 * @return
	 */
	private String complement6Str(String sub){
		if (sub.length() == PART3_MAX_LENGTH) {
			return sub;
		}else {
			String zeroStr = "";
			for (int i = 1; i < PART3_MAX_LENGTH - sub.length(); i++) {
				zeroStr = zeroStr + "0";
			}
			return zeroStr + sub;
		}
	}
}
