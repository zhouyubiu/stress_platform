package com.pressure.test.login.service;

import com.pressure.test.login.common.ResponseData;
import com.pressure.test.login.pojo.User;

/**
 * @NAME: IUserService
 * @USER: zhouyu
 * @DATE: 2020/12/6
 */
public interface IUserService {


    ResponseData<User> login(String username, String password);

}
