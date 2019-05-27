package com.dwtedx.income.utility;

import java.security.MessageDigest;
import java.util.Random;

public class MD5Util {

	/***
	 * MD5加码 生成32位md5码
	 */
	public static String stringToMD5(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();

	}

	/**
	 * 生成随机数字和字母
	 * 
	 * @param length
	 * @return
	 */
	public static String getStringRandom(int length) {
		String val = "";
		Random random = new Random();
		// 参数length，表示生成几位随机数
		for (int i = 0; i < length; i++) {

			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
			// 输出字母还是数字
			if ("char".equalsIgnoreCase(charOrNum)) {
				// 输出是大写字母还是小写字母
				int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
				val += (char) (random.nextInt(26) + temp);
			} else if ("num".equalsIgnoreCase(charOrNum)) {
				val += String.valueOf(random.nextInt(10));
			}
		}
		return val;
	}

	public static void main(String[] args) {
		String password = "{\"name\":\"爱夏\",\"head\":\"http:q.qlogo.cnqqapp1105404596611C883794AB5533F493AC0DC109F257100\",\"sex\":\"男\",\"qqopenid\":\"611C883794AB5533F493AC0DC109F257\",\"othertype\":2};jFX024sn0gk08m8J630PJq7D787sWNnIQYLdwtedx199117??";
		System.out.println(stringToMD5(password));

		System.out.println("e10adc3949ba59abbe56e057f20f883e".equals(stringToMD5(password)));
	}
}
