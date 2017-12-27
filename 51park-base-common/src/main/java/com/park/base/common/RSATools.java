package com.park.base.common;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class RSATools {
	public static final String SIGN_ALGORITHMS = "SHA256WithRSA";
	public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

	/** *//**
	 * RSA最大加密明文大小
	 */
	private static final int MAX_ENCRYPT_BLOCK = 245;

	/** *//**
	 * RSA最大解密密文大小
	 */
	private static final int MAX_DECRYPT_BLOCK = 256;

	/**
	 * 签名
	 * @param content
	 * @param privateKey
	 * @return
	 */
	public static String sign(String content, String privateKey){
		try{
			PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64Tools.decode2Byte(privateKey));
			KeyFactory keyf = KeyFactory.getInstance("RSA");
			PrivateKey priKey = keyf.generatePrivate(priPKCS8);
			Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
			signature.initSign(priKey);
			signature.update(content.getBytes(DEFAULT_CHARSET));
			byte[] signed = signature.sign();
			return Base64Tools.encode2String(signed);
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 验签
	 * @param content
	 * @param sign
	 * @param publicKey
	 * @return
	 */
	public static boolean verify(String content, String sign, String publicKey){
		try{
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			byte[] encodedKey = Base64Tools.decode2Byte(publicKey);
			PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));

			Signature signature = Signature.getInstance(SIGN_ALGORITHMS);

			signature.initVerify(pubKey);
			signature.update(content.getBytes(DEFAULT_CHARSET));

			return signature.verify(Base64Tools.decode2Byte(sign));
		}catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}

	public static PublicKey getPublicKey(String key)throws Exception{
		byte[] keyBytes = Base64Tools.decode2Byte(key);
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey publicKey = keyFactory.generatePublic(keySpec);
		return publicKey;
	}

	public static PrivateKey getPrivateKey(String key)throws Exception{
		byte[] keyBytes = Base64Tools.decode2Byte(key);
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		return privateKey;
	}

	public static String getKeyString(Key key)throws Exception{
		byte[] keyBytes = key.getEncoded();
		return Base64Tools.encode2String(keyBytes);
	}

	public static <T> String getSignatureContent(Map<String, T> params){
		if (params == null) {
			return null;
		}
		StringBuffer content = new StringBuffer();
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<String> keys = new ArrayList(params.keySet());
		Collections.sort(keys);
		for (int i = 0; i < keys.size(); i++){
			String key = (String)keys.get(i);
			if (params.get(key) != null){
				String value = String.valueOf(params.get(key));
				content.append((i == 0 ? "" : "&") + key + "=" + value);
			}
		}
		return content.toString();
	}

	@SuppressWarnings("unchecked")
	public static String getListSignatureContent(@SuppressWarnings("rawtypes") List<Map> mapList){
		if (mapList == null) {
			return null;
		}
		@SuppressWarnings({ "rawtypes"})
		List<String> listStr = new ArrayList();
		for (Map<String, Object> map : mapList) {
			listStr.add(getSignatureContent(map));
		}
		Collections.sort(listStr);
		return listStr.toString();
	}



	/**
	 * 分段加密
	 */
	public static final String KEY_ALGORITHM = "RSA";

	public static String encrpyt(String content, String publicKeyStr)throws Exception{
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(1, getPublicKey(publicKeyStr));
		byte[] bytes = content.getBytes(DEFAULT_CHARSET);
		int inputLen = bytes.length;
		int offSet = 0;
		byte[] cache;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int i = 0;
		// 对数据分段加密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
				cache = cipher.doFinal(bytes, offSet, MAX_ENCRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(bytes, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_ENCRYPT_BLOCK;
		}
		byte[] encryptedData = out.toByteArray();
		out.close();
		return Base64Tools.encode2String(encryptedData);
	}
	/**
	 * 分段解密
	 * @param content
	 * @param privateKeyStr
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String content, String privateKeyStr)throws Exception{
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(2, getPrivateKey(privateKeyStr));
		byte[] bytes = Base64Tools.decode2Byte(content);
		int inputLen = bytes.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段解密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
				cache = cipher.doFinal(bytes, offSet, MAX_DECRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(bytes, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_DECRYPT_BLOCK;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();
		return new String(decryptedData);
	}


}
