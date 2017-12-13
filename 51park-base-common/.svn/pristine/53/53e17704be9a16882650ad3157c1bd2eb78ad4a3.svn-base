package com.park.base.common;

import com.park.base.common.constants.CodeConstants;
import com.park.base.common.domain.ObjectListResponse;
import com.park.base.common.domain.ObjectResponse;
import com.park.base.common.domain.Response;

import java.util.List;



public class ResultTools {

	/**
	 * 返回
	 * @return
	 */
	public static String setResponse(String code){
		
		Response response =new Response();
		response.setCode(code);
		response.setMsg(CodeConstants.getName(code));
		return DataChangeTools.bean2gson(response);	
	}
	/**
	 * 返回
	 * @return
	 */
	public static String setNullMsgResponse(String code){
		
		Response response =new Response();
		response.setCode(code);
		return DataChangeTools.bean2gson(response);	
	}
	/**
	 * 
	 * @param code 
	 * @param data
	 * @return
	 */
	public static String setObjectResponse(String code,Object data){
		
		ObjectResponse response =new ObjectResponse();
		response.setCode(code);
		response.setMsg(CodeConstants.getName(code));
		response.setData(data);
		return DataChangeTools.bean2gson(response);	
	}
	/**
	 * 
	 * @param code 
	 * @param data
	 * @return
	 */
	public static String setObjectListResponse(String code,List<Object> data){
		
		ObjectListResponse response =new ObjectListResponse();
		response.setCode(code);
		response.setMsg(CodeConstants.getName(code));
		response.setData(data);
		return DataChangeTools.bean2gson(response);	
	}
}
