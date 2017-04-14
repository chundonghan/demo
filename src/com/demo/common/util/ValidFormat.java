package com.demo.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidFormat
{
    public static boolean valid(String value, String type)
    {
        Pattern pattern = null;
        Matcher matcher = null;
        String regex = null;

        switch (type)
        {
            case "IDCARD":
                regex = "(\\d{15})|(\\d{17}[0-9x])";
                break;
            case "MOBILEPHONE":
                regex = "^[1][3,4,5,7,8][0-9]{9}$";
                break;
            case "PHONE":
                regex = "^([0]\\d{2,3}-)?\\d{5,8}$";
                break;
            case "BANK":
                regex = "^\\d{16,19}$";
                break;
            default:
        }
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(value);
        return matcher.matches();
    }

    static void print(Object value)
    {
        System.out.println(value);
    }

    public static void main(String[] args)
    {
        print(valid("211203199005041516", "IDCARD"));
        print(valid("18524491554", "MOBILEPHONE"));
        print(valid("76855016", "PHONE"));
        print(valid("6217850500000899520", "BANK"));
    }
}
