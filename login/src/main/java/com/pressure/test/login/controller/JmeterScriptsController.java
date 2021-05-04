package com.pressure.test.login.controller;

import com.pressure.test.login.common.ResponseData;
import com.pressure.test.login.utils.ZKCliUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @NAME: JmeterScriptsHandle
 * @USER: zhouyu
 * @DATE: 2020/12/14
 */

@RestController
@Slf4j
@RequestMapping(value = "/scripts")
public class JmeterScriptsController {

    private static String updatecsripts;

    //脚本参数下发
    @RequestMapping(value = "/distributed/jmx/",method= RequestMethod.GET)
    public ResponseData<String> distributeScripts(){
//        ZKCliUtils zkCliUtils=new ZKCliUtils();
//        InterProcessLock lock = zkCliUtils.lock("/123321");
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject("http://localhost:8089/jmx/presure/test", String.class,"lsc");
        System.out.println(result);
        return null;
    }



}
