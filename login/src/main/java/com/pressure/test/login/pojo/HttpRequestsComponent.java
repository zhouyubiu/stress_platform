package com.pressure.test.login.pojo;

import lombok.Data;

import java.util.HashMap;

/**
 * @NAME: HttpRequests
 * @USER: zhouyu
 * @DATE: 2020/8/2
 */

@Data
public class HttpRequestsComponent {

    private String name;
    private String ip;
    private String protocol;
    private String portNumber;
    private String method;
    private String path;
    private HashMap<String,String>body;

}
