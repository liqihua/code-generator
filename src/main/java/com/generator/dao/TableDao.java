package com.generator.dao;

import com.generator.common.basic.TableColumn;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author liqihua
 * @since 2018/6/21
 */
@Mapper
public interface TableDao {

    @Select("SELECT count(1) FROM information_schema.`TABLES` WHERE TABLE_SCHEMA=(SELECT DATABASE()) AND TABLE_NAME=#{tableName} ")
    public int findTable(@Param("tableName") String tableName);


    @Select("SELECT TABLE_NAME FROM information_schema.`TABLES` WHERE TABLE_SCHEMA=(SELECT DATABASE()) ")
    public List<String> findTableList();

    @Select("SELECT COLUMN_NAME AS columnName,DATA_TYPE AS dataType,COLUMN_TYPE AS columnType,IS_NULLABLE AS isNullable,COLUMN_COMMENT AS columnComment,ORDINAL_POSITION AS rank FROM information_schema.`COLUMNS` WHERE TABLE_SCHEMA=(SELECT DATABASE()) AND TABLE_NAME = #{tableName} ORDER BY rank ASC")
    public List<TableColumn> findColumnList(@Param("tableName") String tableName);

}
