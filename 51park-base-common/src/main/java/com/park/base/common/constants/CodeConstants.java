package com.park.base.common.constants;

import java.util.HashMap;
import java.util.Map;


/**
 * @author Wupj
 */
public class CodeConstants {
	/**
	 * 成功
	 */
	public final static String SUCCESS = "200";
	/**
	 * 服务器异常
	 */
	public final static String ERROR = "500";
	/**
	 * 缺失参数或参数格式不正确
	 */
	public final static String ERROR_400 = "400";
	/**
	 * 签名验证失败
	 */
	public final static String ERROR_401= "401";
	/**
	 * 请求失败,参数格式正确但业务错误
	 */
	public final static String ERROR_402= "402";
	/**
	 * 请求资源不存在
	 */
	public final static String ERROR_404 = "404";
	/**
	 * 服务器异常,解析错误
	 */
	public final static String ERROR_502 = "502";

	public static Map<String, String> map = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;

		{
			put(SUCCESS, "成功");
			put(ERROR, "服务器异常");
			put(ERROR_400, "缺失参数或参数格式不正确");
			put(ERROR_401, "签名验证失败");
			put(ERROR_402, "请求失败,参数格式正确但业务错误");
			put(ERROR_404, "请求资源不存在");
			put(ERROR_502, "服务器异常,解析错误");
		}
	};


	public static String getName(String code) {
		for (Map.Entry<String, String> entry : map.entrySet()) {
			if (code.equals(entry.getKey())) {
				return entry.getValue();
			}
		}
		return null;
	}

	public static String getCode(String name) {
		for (Map.Entry<String, String> entry : map.entrySet()) {
			if (name.equals(entry.getValue())) {
				return entry.getKey();
			}
		}
		return "";
	}

}
