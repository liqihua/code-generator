package com.core.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ClassUtils;

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


    /**
     * 判断str在不在strs里面，如果在，返回true
     * @param str
     * @param strs
     * @return
     */
    public static boolean in(String str,String ... strs){
        for(String s : strs){
            if(s.equals(str)){
                return true;
            }
        }
        return false;
    }


    /**
     * 获取项目路径
     * @author liqihua
     * @since 2018/6/21
     */
    public static String getWorkspace() {
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        path = path.replace("target/classes/","");
        String first = path.substring(0,1);
        if(first.equals("/")){
            path = path.substring(1,path.length());
        }
        return path;
    }


    /**
     * 获取代码路径
     * @author liqihua
     * @since 2018/6/21
     */
    public static String getCodeRoot(){
        return getWorkspace() + "src/main/java/";
    }


    /**
     * 方法名：ceil
     * 详述：向上取整
     * @param num
     * @param size
     * @return int
     */
    public static int ceil(int num, int size) {
        if (size == 0) {
            return 0;
        }
        int n1 = num/size;
        double n2 = num%size;
        if (n2 > 0) {
            n1 += 1;
        }
        return n1;
    }


}
