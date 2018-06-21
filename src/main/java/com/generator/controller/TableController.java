package com.generator.controller;

import com.generator.common.basic.TableColumn;
import com.generator.dao.TableDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author liqihua
 * @since 2018/6/20
 */
@Api(value="tableController",description="数据表")
@RestController
@RequestMapping("/tableController")
public class TableController {
    @Autowired
    private TableDao tableDao;

    @ApiOperation(value = "findTableList")
    @RequestMapping(value = "/findTableList",method = RequestMethod.GET)
    public String findTableList(){
        List<String> list = tableDao.findTableList();
        if(list != null) {
            return JSONArray.fromObject(list).toString();
        }else{
            return "list is null";
        }
    }



    @ApiOperation(value = "findColumnList")
    @RequestMapping(value = "/findColumnList",method = RequestMethod.GET)
    public String findColumnList(@ApiParam(value = "tableName",required = true) @RequestParam(value="tableName",required=true) String tableName){
        List<TableColumn> list = tableDao.findColumnList(tableName);
        if(list != null) {
            return JSONArray.fromObject(list).toString();
        }else{
            return "list is null";
        }
    }





}
