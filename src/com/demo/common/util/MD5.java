package com.demo.common.util;

import java.math.BigInteger;
import java.security.MessageDigest;

import sun.misc.BASE64Encoder;

import com.fasterxml.jackson.databind.deser.Deserializers.Base;

public class MD5 {

	public static String md5(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			str = buf.toString();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return str;
	}
	public static String md5_1(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            //str =  new BigInteger(1, md.digest()).toString(16);
            BASE64Encoder base64en = new BASE64Encoder();
            str  = base64en.encode(md.digest());
        } catch (Exception e) {
            e.printStackTrace();

        }
        return str;
    }
	
	
	public static void main(String[] args) {
		System.out.println(md5("31119@qq.com"+"123456"));
		System.out.println(md5_1("123"));
	}
}
