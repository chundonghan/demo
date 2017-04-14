package com.demo.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: MatcherTest
 * 模块名称：
 * @Description: 内容摘要：
 * @author handongchun
 * @date 2017年3月22日
 * @version V1.0
 * history:
 * 1、2017年3月22日 handongchun 创建文件
 */
public class MatcherTest
{
    public static String date2String(Date currentDate, String formate)
    {
        String result = null;
        SimpleDateFormat formatdater = new SimpleDateFormat(formate);
        result = formatdater.format(currentDate);
        return result;
    }

    public static void main(String[] args)
    {   
            String str = "2006-02-10";
            String regex ="^20(\\d{2})-(\\d{2})$";
            java.util.regex.Pattern pattern =java.util.regex.Pattern.compile(regex);
            java.util.regex.Matcher matcher = pattern.matcher(str);
           System.out.println(matcher.matches());
           System.out.println(date2String(new java.util.Date(),"YYYY-MM"));
           String dept_info = "DPHYWYBXXXX001";
           String dept_info_regex ="^DPHYW([A-Z]+)BXXXX(\\d{3,})$";
           java.util.regex.Pattern dept_info_pattern =java.util.regex.Pattern.compile(dept_info_regex);
           java.util.regex.Matcher dept_info_matcher = dept_info_pattern.matcher(dept_info);
           System.out.println(dept_info_matcher.matches());
           
    }
}
