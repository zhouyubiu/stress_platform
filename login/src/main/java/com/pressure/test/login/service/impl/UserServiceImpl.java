package com.pressure.test.login.service.impl;

import com.pressure.test.login.common.ResponseData;
import com.pressure.test.login.dao.UserMapper;
import com.pressure.test.login.pojo.User;
import com.pressure.test.login.service.IUserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 * @NAME: userServiceImpl
 * @USER: zhouyu
 * @DATE: 2020/12/6
 */

@Service
@Log4j2
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public ResponseData<User> login(String username, String password) {

        User userinfo=userMapper.selectByUserName(username);
        password = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        if(userinfo==null||!(userinfo.getPassword().equals(password))){
            return ResponseData.creatByErrorMsg("error");
        }else{
            userinfo.setPassword(null);
            return ResponseData.creatBySuccessMsg(userinfo,"success");
        }

    }


}
