package me.bjxg.demo.mock.utils;

/**
 * Created by chengcy on 2017/3/9.
 */
public class Validator {
    /**
     * 校验字符串是否为null , 空串，纯空格
     * @param str
     * @return
     */
    public static boolean isBlank(String str){
        return isEmpty(str)||str.trim().length()==0;
    }

    /**
     * 校验字符串是否为null , 空串
     * @param str
     * @return
     */
    public static boolean isEmpty(String str){
        return str==null||str.length()==0;
    }
}
