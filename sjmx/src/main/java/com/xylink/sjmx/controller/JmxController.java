package com.xylink.sjmx.controller;

import com.xylink.sjmx.common.ResponseData;
import com.xylink.sjmx.dao.JmxScriptsDao;
import com.xylink.sjmx.pojo.JmxScripts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.Request;

import java.io.IOException;

/**
 * @NAME: JmxController
 * @USER: zhouyu
 * @DATE: 2020/8/2
 */

@RestController
@RequestMapping("/jmx/presure")
public class JmxController {

    @Autowired
    private JmxScriptsDao jmxScriptsDao;



    @RequestMapping(path = "/updata",method = RequestMethod.POST)
    public ResponseData updataJmxInfo(@RequestParam(value = "jmxScriptsData")JmxScripts jmxScriptsData)  throws IOException {
        ResponseData res = jmxScriptsDao.creatJmxScripts(jmxScriptsData);
        if(res.isSuccessful()){
            return res;
        }
        return ResponseData.creatByErrorMsg("运行失败");
    }



    @RequestMapping(path = "/start",method = RequestMethod.GET)
    public ResponseData<String> start(){
        return null;
    }

    @RequestMapping(path = "/stop",method= RequestMethod.GET)
    public ResponseData<String> stop(){
        return null;
    }

    @RequestMapping(path = "delete",method=RequestMethod.DELETE)
    public ResponseData<String> delete(){
        return null;
    }



    @RequestMapping(path = "test",method = RequestMethod.GET)
    public String test(){
        System.out.println("test_ok");
//        user u=new user();
//        u.test();
        return "test_ok";
    }
}
