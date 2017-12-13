package com.park.cloudcore.common;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.park.base.common.DataChangeTools;
import com.park.base.common.RSATools;
import com.park.base.common.SignTools;

public class MySignTools {
	
public  static  Logger logger =LoggerFactory.getLogger(SignTools.class);
	
	/**
	 * RSA验签
	 * @param params 参数JSON串(已解密)
	 * @param sign 接收到的签名
	 * @param myPrivateKey 我方私钥
	 * @param publicKey 对方公钥
	 * @return 验签结果
	 * @throws Exception 
	 */
	public static boolean RSAVerify(String params, String sign, String myPrivateKey, String publicKey) throws Exception {
	    String signatureParams;
        try {
            Map<String, String> paramMap = DataChangeTools.json2Map(params);
            signatureParams = DataChangeTools.createLinkString(paramMap);
            return RSATools.verify(signatureParams, sign, publicKey);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
	}
	
	// RSA签名
	public Map<String, String> getHeaders(String params, String privateKey){  
        Map<String, String> headers = new HashMap<String, String>(); 
        Map<String, String> map=DataChangeTools.json2Map(params);
        String linkparams=DataChangeTools.createLinkString(map);
        String authorization=RSATools.sign(linkparams, privateKey);
        headers.put("Authorization",authorization);
        return headers;  
    }
}
