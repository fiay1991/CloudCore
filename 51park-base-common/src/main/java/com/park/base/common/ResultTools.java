package com.park.base.common;

import com.park.base.common.constants.CodeConstants;
import com.park.base.common.domain.ObjectListResponse;
import com.park.base.common.domain.ObjectResponse;
import com.park.base.common.domain.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class ResultTools {

	private static Logger logger = LoggerFactory.getLogger(ResultTools.class);
	/**
	 * 返回
	 * @return
	 */
	public static String setResponse(String code,String msg){
		
		Response response =new Response();
		response.setCode(code);
		response.setMsg(msg);
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
	public static String setObjectResponse(String code,String msg, Object data){
		
		ObjectResponse response =new ObjectResponse();
		response.setCode(code);
		response.setMsg(msg);
		response.setData(data);
		return DataChangeTools.bean2gson(response);	
	}
	/**
	 * 
	 * @param code 
	 * @param data
	 * @return
	 */
	public static String setObjectListResponse(String code,String msg,List<Object> data){
		
		ObjectListResponse response =new ObjectListResponse();
		response.setCode(code);
		response.setMsg(msg);
		response.setData(data);
		return DataChangeTools.bean2gson(response);
	}

    /**
     * 将响应结果中的data部分转换成指定类型后返回
     * @param responseResult
     * @param clazz
     * @return
     */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static ObjectResponse getObjectResponse(String responseResult, Class clazz) {
        ObjectResponse objectResponse = getObjectResponse(responseResult);
        if (objectResponse == null)
            return null;
        Object data = objectResponse.getData();
        if (data != null){
            String dataJson = DataChangeTools.bean2gson(data);
            logger.info("***data结果为: {}", dataJson);
            objectResponse.setData(DataChangeTools.gson2bean(dataJson, clazz));
        }
        return objectResponse;
    }
    /**
     * 将响应结果中的data列表部分转换成指定类型后返回
     * @param responseResult
     * @param cla
     * @return
     */
    @SuppressWarnings("rawtypes")
	public static ObjectResponse getObjectListResponse(String responseResult, Class cla) {
        ObjectResponse objectResponse = getObjectResponse(responseResult);
        if (objectResponse == null)
            return null;
        Object data = objectResponse.getData();
        if (data != null){
            String dataJson = DataChangeTools.bean2gson(data);
            logger.info("***data结果为: {}", dataJson);
            objectResponse.setData(DataChangeTools.gson2List(dataJson, cla));
        }
        return objectResponse;
    }

    /**
     * 判断响应的json结果，code码是否成功
     * @param responseBody
     * @return
     */
    public static boolean isSuccess(String responseBody){
        ObjectResponse objectResponse = getObjectResponse(responseBody);
        if (objectResponse != null && CodeConstants.SUCCESS.equals(objectResponse.getCode())){
            return true;
        }
        logger.info("***响应结果：{}",responseBody );
        return false;
    }
	/**
	 * 将响应结果转换为ObjectResponse对象
	 * @param responseBody
	 * @return
     */
	private static ObjectResponse getObjectResponse(String responseBody){
		if (ToolsUtil.isNull(responseBody))
			return null;
		try {
			ObjectResponse response = DataChangeTools.gson2bean(responseBody, ObjectResponse.class);
			if (response != null) {
				return response;
			} else {
				logger.info("***responseBody不能转换为ObjectResponse对象，{}", responseBody);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("***转换为ObjectResponse对象异常，参数:{}，error:{}", responseBody, e.getMessage());
		}
		return null;
	}
}
