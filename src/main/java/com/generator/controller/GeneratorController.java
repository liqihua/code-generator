package com.generator.controller;

import com.generator.service.GeneratorService;
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
@Api(value="genteratorController",description="代码生成")
@RestController
@RequestMapping("/genteratorController")
public class GeneratorController {
    @Autowired
    private GeneratorService generatorService;


    @ApiOperation(value = "makeCode")
    @RequestMapping(value = "/makeCode",method = RequestMethod.GET)
    public String makeCode(@ApiParam(value = "tableName",required = true) @RequestParam(value="tableName",required=true) String tableName){
        generatorService.makeAll(tableName);
        return "--makeCode--";
    }



}
