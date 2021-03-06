package com.park.cloudcore.common;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.park.base.common.ToolsUtil;
import com.park.cloudcore.common.constants.PageConstants;

/**
 * Created by fangct on 2017/11/27.
 */
public class PublicHandleTools {
	private static Logger logger = LoggerFactory.getLogger(PublicHandleTools.class);

	/**
	 * 记录操作数据库的结果
	 *
	 * @param records
	 * @param topic
	 * @param params
	 */
	public static boolean operDbResult(int records, String topic, String params) {
		if (0 != records) {
			return true;
		} else {
			logger.info("** {}, 影响行数为0, 参数={}", topic, params);
			return false;
		}
	}

	/**
	 * 分页处理
	 * @param page
	 * @param perPage
	 * @return
	 */
	public static void paging(Map<String, String> paramsMap) {
		String page = paramsMap.get("page");
		String perPage = paramsMap.get("perPage");
		int pageInt = ToolsUtil.parseInt(page);
		int perPageInt = ToolsUtil.parseInt(perPage);
		if (ToolsUtil.isNull(page) || pageInt <= 0)
			page = PageConstants.DEFAULT_PAGE;
		if (ToolsUtil.isNull(perPage) || perPageInt <= 0)
			perPage = PageConstants.DEFAULT_PER_PAGE;
		Integer firstResult = (pageInt - 1) * perPageInt;
		paramsMap.put("firstResult", "" + firstResult);
	}
	
}
