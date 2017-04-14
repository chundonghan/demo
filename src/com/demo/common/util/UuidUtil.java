
package com.demo.common.util;

import java.util.UUID;
/**
 * 
 * @see uuid
 * @time 2016年9月30日 上午11:23:57
 * @author han
 *
 */
public class UuidUtil {
	
	public static String get32UUID() {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		return uuid;
	}
	
	public static void main(String[] args) {
		System.out.println(get32UUID());
	}
}

