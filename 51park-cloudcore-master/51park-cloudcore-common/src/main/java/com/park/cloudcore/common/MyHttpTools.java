package com.park.cloudcore.common;

import java.util.HashMap;
import java.util.Map;

import com.park.base.common.DataChangeTools;
import com.park.base.common.HttpTools;
import com.park.base.common.RSATools;
import com.park.cloudcore.properites.KeysConfig;
import com.park.cloudcore.properites.Spring;

public class MyHttpTools {
	
    // 发送POST请求
    public static String sendRequest(Object obj, String url, String publicKey) {
        String params = DataChangeTools.bean2gson(obj);
        String encrpytParams = "";
        try {
            encrpytParams = RSATools.encrpyt(params, publicKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String,String> headers = getHeaders(params);
        return HttpTools.HttpClientPost(url, encrpytParams, headers);
    }
    
    public static Map<String, String> getHeaders(String params){  
        Map<String, String> headers = new HashMap<String, String>(); 
        Map<String, String> map=DataChangeTools.json2Map(params);
        String linkparams=DataChangeTools.createLinkString(map);
        KeysConfig keysConfig = Spring.getBean("keysConfig");
        String authorization=RSATools.sign(linkparams, keysConfig.getPrivateKey());
        headers.put("Authorization",authorization);
        return headers;  
    }
    
}
