package com.liqihua.common.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author liqihua
 * @since 2018/6/20
 */
public class Tool {


    /**
     * 判断String参数是否为空的方法，参数数量可变，如果其中有一个参数是空，就返回true
     * @param strs
     * @return
     */
    public static boolean isBlank(String ... strs){
        for(String s : strs){
            if(StringUtils.isBlank(s)){
                return true;
            }
        }
        return false;
    }

    /**
     * 判断多个参数是否为空
     * @param strs
     * @return
     */
    public static boolean isNotBlank(String ... strs){
        return !isBlank(strs);
    }




}
