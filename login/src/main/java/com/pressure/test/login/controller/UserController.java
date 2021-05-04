package com.pressure.test.login.controller;
import com.pressure.test.login.common.CommonValue;
import com.pressure.test.login.common.RedisPool;
import com.pressure.test.login.common.ResponseData;
import com.pressure.test.login.common.ZKCli;
import com.pressure.test.login.pojo.User;
import com.pressure.test.login.service.impl.UserServiceImpl;
import com.pressure.test.login.utils.JsonUtils;
import com.pressure.test.login.utils.RedisPoolUtil;
import com.pressure.test.login.utils.ZKCliUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@RequestMapping("/pressure/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;


    //用户登录
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseData<User> userLogin(User user, HttpSession session){
        if(user.getName()==null||user.getPassword()==null){
            return ResponseData.creatByErrorMsg("输入不能为null");
        }
        ResponseData<User>responseData=userService.login(user.getName(),user.getPassword());
        if(responseData.isSuccessful()){
            RedisPoolUtil.setEx(session.getId(), JsonUtils.obj2json(responseData.getData()),30*60);
        }
        return responseData;

    }


    //用户退出
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public ResponseData<String> userLogout(HttpSession session){
        RedisPoolUtil.del(session.getId());
        return ResponseData.creatBySuccessMsg("退出成功");
    }



    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String test() throws InterruptedException {
        ZKCliUtils zkCliUtils=new ZKCliUtils();
        InterProcessLock lock = zkCliUtils.lock("/opopopopop");
        System.out.println("hello**********************************");
//        TimeUnit.SECONDS.sleep(10);
//        zkCliUtils.release(lock);


        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject("http://localhost:8089/jmx/presure/test", String.class,"lsc");
        System.out.println(result);
        return "ok";
    }

}
