package com.liqihua.controller;

import com.liqihua.dao.CommonDao;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author liqihua
 * @since 2018/6/20
 */
@RestController
@RequestMapping("/genteratorController")
public class GeneratorController {
    @Autowired
    private CommonDao commonDao;

    @RequestMapping("/test1")
    public String test1(){
        List<Map<String,Object>> list = commonDao.test1();
        if(list != null) {
            System.out.println(JSONArray.fromObject(list).toString());
        }else{
            System.out.println("list is null");
        }
        return "--test1--";
    }



}
