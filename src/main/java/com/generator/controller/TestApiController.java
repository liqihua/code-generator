package com.generator.controller;

import com.aa.bb.cc.service.TestStudentService;
import com.core.common.basic.controller.BaseController;
import com.core.common.basic.result.BaseResult;
import com.core.common.constance.ApiConstance;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author liqihua
 * @since 2018/7/9
 */
@Api(value="api-test-controller",description="测试")
@SpringBootApplication
@RequestMapping("/api/testApiController")
public class TestApiController extends BaseController{
    @Autowired
    private TestStudentService studentService;


    @ApiOperation(value = "描述aa")
    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    @ApiResponses({@ApiResponse(code = ApiConstance.BASE_SUCCESS_CODE, message = "成功", response = String.class)})
    public ResponseEntity<BaseResult> test1(@ApiParam(value = "aa",required = true) @RequestParam(value="aa",required=false) String aa){
        return buildSuccessInfo(studentService.findList(null));
    }


}