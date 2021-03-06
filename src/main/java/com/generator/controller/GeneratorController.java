package com.generator.controller;

import com.core.basic.controller.BaseController;
import com.core.basic.result.BaseResult;
import com.generator.service.GeneratorService;
import com.generator.service.TableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liqihua
 * @since 2018/6/20
 */
@Api(value="genteratorController",description="代码生成")
@RestController
@RequestMapping("/genteratorController")
public class GeneratorController extends BaseController{
    @Autowired
    private GeneratorService generatorService;
    @Autowired
    private TableService tableService;


    @ApiOperation(value = "makeCode")
    @RequestMapping(value = "/makeCode",method = RequestMethod.GET)
    public ResponseEntity<BaseResult> makeCode(@ApiParam(value = "表名",required = true) @RequestParam(value="tableName",required=true) String tableName,
                                               @ApiParam(value = "包名，如：com.liqihua.tally",required = true) @RequestParam(value="packageName",required=true) String packageName,
                                               @ApiParam(value = "父类包名，如：com.liqihua.core",required = false) @RequestParam(value="corePackage",required=false) String corePackage){
        if(packageName.contains("core") || packageName.contains("generator")){
            return buildFailedInfo("packageName不能含有core、generator");
        }
        if(!tableService.tableExist(tableName)){
            return buildFailedInfo("表："+tableName+" 不存在");
        }
        generatorService.makeAll(tableName,packageName,corePackage);
        return buildSuccessInfo("--makeCode--");
    }

}
