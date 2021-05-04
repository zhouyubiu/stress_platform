package com.xylink.sjmx;

import com.xylink.sjmx.common.CommonValue;

import com.xylink.sjmx.dao.JmxScriptsDao;
import com.xylink.sjmx.pojo.HttpRequestsComponent;
import com.xylink.sjmx.pojo.JmxScripts;
import com.xylink.sjmx.pojo.TestPlanComponent;
import com.xylink.sjmx.pojo.ThreadGroupComponent;
import com.xylink.sjmx.service.impl.JmeterScriptsOperationImpl;
import com.xylink.sjmx.utils.JmeterScriptsUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.HashMap;


@SpringBootTest
class SjmxApplicationTests {


    @Autowired
    private JmeterScriptsUtils jmeterScriptsUtils;



    @Autowired
    private JmeterScriptsOperationImpl jmeterScriptsOperation;

    @Autowired
    private JmxScriptsDao jmxScriptsDao;

    @Test
    void contextLoads() throws IOException {
        jmeterScriptsOperation.test();
    }

    @Test
    void contextLoads2() throws IOException {
        JmxScripts jmxScripts = new JmxScripts();

        TestPlanComponent testPlanComponent = new TestPlanComponent();
        testPlanComponent.setName("TestPlanComponent");

        ThreadGroupComponent threadGroupComponent =new ThreadGroupComponent();
        threadGroupComponent.setName("ThreadGroupComponent");
        threadGroupComponent.setLoopCounts(1);
        threadGroupComponent.setThreads(1);

        HttpRequestsComponent httpRequestsComponent = new HttpRequestsComponent();
        httpRequestsComponent.setName("HttpRequestsComponent");
        httpRequestsComponent.setIp("1.1.1.1");
        httpRequestsComponent.setMethod("get");
        httpRequestsComponent.setPortNumber("443");
        httpRequestsComponent.setPath("1/1/2/3");
        httpRequestsComponent.setProtocol("popo");
        HashMap<String,String> hashMap = new HashMap<String,String>();
        httpRequestsComponent.setBody(hashMap);

        jmxScripts.setTestPlanComponent(testPlanComponent);
        jmxScripts.setHttpRequestsComponentList(httpRequestsComponent);
        jmxScripts.setThreadGroupComponent(threadGroupComponent);

        jmxScriptsDao.creatJmxScripts(jmxScripts);
//        jmeterScriptsOperation.run(jmxScripts);
//
//        String jmxPath = "/Users/zhouyu/Desktop/study/sjmx/src/main/resources/jmxscripts/TestPlanComponent/TestPlanComponent.jmx";
//        jmeterScriptsOperation.stop(jmxPath);







    }


}
