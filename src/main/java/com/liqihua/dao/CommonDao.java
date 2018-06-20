package com.liqihua.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author liqihua
 * @since 2018/6/20
 */
@Mapper
public interface CommonDao {

    @Select("select * from sys_user")
    public List<Map<String,Object>> test1();

}
