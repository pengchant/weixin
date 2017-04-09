package com.handkoo.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 签名校验
 * 
 * @author cp
 *
 */
public class SignUtil {

	private static final Logger logger = LoggerFactory.getLogger(SignUtil.class);

	/**
	 * 校验签名
	 * 
	 * @param signature
	 *            微信加密签名
	 * @param timestamp
	 *            时间戳
	 * @param nonce
	 *            随机数
	 * @return
	 */
	public static boolean checkSignature(String signature, String timestamp, String nonce) {
		String[] paramArr = new String[] { CommonUtil.tokenstr, timestamp, nonce };
		Arrays.sort(paramArr);
		String content = paramArr[0].concat(paramArr[1]).concat(paramArr[2]);
		String ciphertext = null;
		try {
			MessageDigest mDigest = MessageDigest.getInstance("SHA-1");
			byte[] digest = mDigest.digest(content.toString().getBytes());
			ciphertext = byteToStr(digest);
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.toString());
		}
		return ciphertext != null ? ciphertext.equals(signature.toUpperCase()) : false;
	}

	/**
	 * 将字节数组转化为十六进制字符串
	 * 
	 * @param byteArray
	 *            字节数组
	 * @return
	 */
	private static String byteToStr(byte[] byteArray) {
		String strDigest = "";
		for (int i = 0; i < byteArray.length; i++) {
			strDigest += byteToHexStr(byteArray[i]);
		}
		return strDigest;
	}

	/**
	 * 将字节转化为十六进制字符串
	 * 
	 * @param mByte
	 *            字节
	 * @return
	 */
	private static String byteToHexStr(byte mByte) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char[] tempArr = new char[2];
		tempArr[0] = Digit[mByte >>> 4 & 0x0F];
		tempArr[1] = Digit[mByte & 0x0F];
		String s = new String(tempArr);
		return s;
	}
}
