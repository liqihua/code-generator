package com.generator.service;

import com.generator.common.basic.TableColumn;
import com.generator.dao.TableDao;
import com.core.common.utils.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liqihua
 * @since 2018/6/21
 */
@Service
public class TableService {
    @Autowired
    private TableDao tableDao;


    /**
     * 查询当前数据库所有表的名称
     * @author liqihua
     * @since 2018/6/21
     */
    public List<String> findTableList(){
        return tableDao.findTableList();
    }


    /**
     * 根据数据库表名查询这个表的所有字段
     * @param tableName
     * @return
     */
    public List<TableColumn> findColumnList(String tableName){
        return tableDao.findColumnList(tableName);
    }



    /**
     * 将SysColumn相应的字段名、类型等属性向Java转换
     * @author liqihua
     * @since 2018/6/21
     */
    public TableColumn convert(TableColumn sysColumn){
        if(sysColumn != null && Tool.isNotBlank(sysColumn.getColumnName(),sysColumn.getColumnType())){
            sysColumn.setAttrName(toJavaAttr(sysColumn.getColumnName()));
            sysColumn.setGetMethod(toGetMethod(sysColumn.getColumnName()));
            sysColumn.setSetMethod(toSetMethod(sysColumn.getColumnName()));
            sysColumn.setJavaType(toJavaType(sysColumn.getDataType()));
        }
        return sysColumn;
    }



    /**
     * 根据表字段生成get方法
     * @param columnName
     * @return
     */
    public String toGetMethod(String columnName){
        if(Tool.isBlank(columnName)){
            return null;
        }
        String attrName = toJavaAttr(columnName);
        return "get"+attrName.substring(0, 1).toUpperCase()+attrName.substring(1);
    }

    /**
     * 根据表字段生成set方法
     * @param columnName
     * @return
     */
    public String toSetMethod(String columnName){
        if(Tool.isBlank(columnName)){
            return null;
        }
        String attrName = toJavaAttr(columnName);
        return "set"+attrName.substring(0, 1).toUpperCase()+attrName.substring(1);
    }

    /**
     * 将数据库表名转化成Java类名
     * @param tableName
     * @return
     */
    public String toClassName(String tableName){
        if(Tool.isBlank(tableName)){
            return null;
        }
        String className = null;
        StringBuilder sb = new StringBuilder();
        String[] strArr = tableName.split("_");
        for(int i=0;i<strArr.length;i++){
            if(strArr[i].length()<2){
                sb.append(strArr[i].toUpperCase());
            }else{
                sb.append(strArr[i].substring(0,1).toUpperCase()+strArr[i].substring(1));
            }
        }
        className = sb.toString();
        return className;
    }

    /**
     * 将数据库表名转化成Java类名，去掉表前缀
     * @param tableName
     * @return
     */
    public String toShortName(String tableName){
        if(Tool.isBlank(tableName)){
            return null;
        }
        StringBuilder sb = new StringBuilder();
        String[] strArr = tableName.split("_");
        if(strArr.length < 2){
            return strArr[0].substring(0,1).toUpperCase()+strArr[0].substring(1);
        }else{
            for(int i=1;i<strArr.length;i++){
                if(strArr[i].length()<2){
                    sb.append(strArr[i].toUpperCase());
                }else{
                    sb.append(strArr[i].substring(0,1).toUpperCase()+strArr[i].substring(1));
                }
            }
        }
        return sb.toString();
    }

    /**
     * 首字母小写
     * @param str
     * @return String
     */
    public String firstLower(String str){
        if(Tool.isBlank(str) || str.length()<1){
            return null;
        }
        return str.substring(0,1).toLowerCase()+str.substring(1);
    }


    /**
     * 将数据库字段名转化成Java属性名
     * @param columnName
     * @return
     */
    public String toJavaAttr(String columnName){
        if(Tool.isBlank(columnName)){
            return null;
        }
        String attrName = null;
        StringBuilder sb = null;
        String[] strArr = columnName.split("_");
        if(strArr.length < 2){
            attrName = columnName;
        }else{
            sb = new StringBuilder();
            for(int i=0;i<strArr.length;i++){
                if(i == 0){
                    sb.append(strArr[i]);
                }else{
                    if(strArr[i].length()<2){
                        sb.append(strArr[i].toUpperCase());
                    }else{
                        sb.append(strArr[i].substring(0,1).toUpperCase()+strArr[i].substring(1));
                    }
                }
            }
            attrName = sb.toString();
        }
        return attrName;
    }

    /**
     * 将数据类型转换成java类型
     * @param dataType
     * @return
     */
    public String toJavaType(String dataType){
        if(Tool.isBlank(dataType)){
            return null;
        }
        dataType = dataType.toLowerCase();
        if(Tool.in(dataType, "int","tinyint","smallint","mediumint","integer")){
            return "Integer";
        }else if(Tool.in(dataType,"bigint")){
            return "Long";
        }else if(Tool.in(dataType, "real","double","float")){
            return "Double";
        }else if(Tool.in(dataType, "numeric","decimal")){
            return "BigDecimal";
        }else if(Tool.in(dataType, "varchar","char","text","tinytext","mediumtext","longtext","enum")){
            return "String";
        }else if(Tool.in(dataType, "datetime","date","time","timestamp")){
            return "Date";
        }else if(Tool.in(dataType, "varbinary","binary")){
            return "byte[]";
        }else if(Tool.in(dataType,"bit")){
            return "Boolean";
        }
        return null;
    }

    /**
     * 根据表名生成权限符号
     * @param tableName 表名
     * @param permType 权限类型：view、edit、delte等
     * @return
     */
    public String toPermStr(String tableName,String permType){
        if(Tool.isNotBlank(tableName,permType)){
            String[] strArr = tableName.split("_");
            if(strArr != null && strArr.length > 0){
                if(strArr.length < 2){
                    return strArr[0]+":"+strArr[0]+":"+permType;
                }else if(strArr.length < 3){
                    return strArr[0]+":"+strArr[1]+":"+strArr[1]+":"+permType;
                }else if(strArr.length >2){
                    StringBuilder sb = new StringBuilder();
                    for(int i=0; i<strArr.length; i++){
                        if(i < strArr.length -1){
                            sb.append(strArr[i]).append(":");
                        }else{
                            sb.append(strArr[i]).append(":").append(strArr[i]).append(":").append(permType);
                        }
                    }
                    return sb.toString();
                }
            }
        }
        return "";
    }


}
