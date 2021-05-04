package com.xylink.sjmx.utils;

import com.xylink.sjmx.common.ResponseData;
import com.xylink.sjmx.pojo.JmxScripts;
import com.xylink.sjmx.pojo.TestPlanComponent;
import com.xylink.sjmx.pojo.ThreadGroupComponent;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.config.CSVDataSet;
import org.apache.jmeter.config.gui.ArgumentsPanel;
import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.control.gui.LoopControlPanel;
import org.apache.jmeter.control.gui.TestPlanGui;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.protocol.http.control.gui.HttpTestSampleGui;
import org.apache.jmeter.protocol.http.sampler.HTTPSamplerProxy;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.testbeans.gui.TestBeanGUI;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.threads.ThreadGroup;
import org.apache.jmeter.threads.gui.ThreadGroupGui;
import org.apache.jmeter.timers.ConstantThroughputTimer;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jmeter.visualizers.backend.BackendListener;
import org.apache.jmeter.visualizers.backend.BackendListenerGui;
import org.apache.jorphan.collections.HashTree;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * @NAME: JMXTools
 * @USER: zhouyu
 * @DATE: 2020/8/18
 */
@Data
@Log4j2
@Component
public class JmeterScriptsUtils {

    private String scriptPathHome;
    private StandardJMeterEngine jmeter = new StandardJMeterEngine();

    @Value("${jmeter.scripts-root-dir}")
    private String scriptsRootDir;


    static{
        // 设置jmeterHome路径
        // 主要是读取了几个配置文件，jmeter.properties，user.properties，system.properties。
        // 设置一下的本地的Locale环境。
        // 其实到这里，是可以仅将这3个配置文件抽离出来，即不需要整个Jmeter的home目录，仅要这3个配置文件就能运行Jmeter脚本。
        // 甚至仅在代码中写要的配置，都不需要实体的配置文件即可。
        // 当然随着功能越来越多，平台跟Jmeter的耦合也越来越多，这个Jmeter_home目录还是越来越必要了。
        String jmeterHome1 = "/Users/zhouyu/Downloads/apache-jmeter-5.1.1";
        //File jmeterHome = new File(System.getProperty("jmeter.home"));
        File jmeterHome = new File(jmeterHome1);
        // 分隔符
        String slash = System.getProperty("file.separator");

        //判断jmeterHome
        if (jmeterHome.exists()) {
            File jmeterProperties = new File(jmeterHome.getPath() + slash + "bin" + slash + "jmeter.properties");
            if (jmeterProperties.exists()) {
                // 初始化压测引擎
//                StandardJMeterEngine jmeter = new StandardJMeterEngine();
                // JMeter初始化(属性、日志级别、区域设置等)
                JMeterUtils.setJMeterHome(jmeterHome.getPath());
                JMeterUtils.loadJMeterProperties(jmeterProperties.getPath());
                // 可以注释这一行，查看额外的日志，例如DEBUG级别
//                JMeterUtils.initLogging();
//                JMeterUtils.initLocale();

            }
        }

    }

    //创建测试计划
    private TestPlan creatTestPlan(TestPlanComponent testPlanComponent) {

//        TestPlan testPlan = new TestPlan(testPlanComponent.getName());
        TestPlan testPlan = new TestPlan("6666666666666");

        testPlan.setProperty(TestElement.TEST_CLASS, TestPlan.class.getName());
        testPlan.setProperty(TestElement.GUI_CLASS, TestPlanGui.class.getName());
        testPlan.setUserDefinedVariables((Arguments) new ArgumentsPanel().createTestElement());

        return testPlan;
    }

    //创建循环控制器
    public LoopController creatLoopController() {
        LoopController loopController = new LoopController();
        loopController.setLoops(1);
        loopController.setContinueForever(true);
        loopController.setFirst(true);
        loopController.setProperty(TestElement.TEST_CLASS, LoopController.class.getName());
        loopController.setProperty(TestElement.GUI_CLASS, LoopControlPanel.class.getName());
        loopController.initialize();
        return loopController;
    }

    //创建线程组
    private ThreadGroup creatThreadGroup(ThreadGroupComponent threadGroupComponent) {
        LoopController loopController = new LoopController();
//        loopController.setLoops(1);
        loopController.setContinueForever(true);
        loopController.setEnabled(true);

        loopController.setLoops(-1);
        loopController.setProperty(TestElement.TEST_CLASS, LoopController.class.getName());
        loopController.setProperty(TestElement.GUI_CLASS, LoopControlPanel.class.getName());

        ThreadGroup threadGroup = new ThreadGroup();
//        threadGroup.setName(threadGroupComponent.getName());
        threadGroup.setName("nihao");
//        threadGroup.setNumThreads(threadGroupComponent.getThreads());
        threadGroup.setNumThreads(1);
        threadGroup.setEnabled(true);
        threadGroup.setRampUp(1);

        threadGroup.setDuration(10);
        threadGroup.setSamplerController(loopController);
        threadGroup.setProperty(TestElement.TEST_CLASS, ThreadGroup.class.getName());
        threadGroup.setProperty(TestElement.GUI_CLASS, ThreadGroupGui.class.getName());
        return threadGroup;
    }
    //创建http请求
    private HTTPSamplerProxy creatHttpReauests(){
        // 第二个 HTTP Sampler - 打开 qq.com
        HTTPSamplerProxy qqcomSampler = new HTTPSamplerProxy();
        qqcomSampler.setProtocol("https");
        qqcomSampler.setDomain("testqa.xylink.com");
        qqcomSampler.setPort(443);
        qqcomSampler.setPath("/api/rest/css/v1/client/configs?securityKey=e646c3a4939bda119cfaafd91bbef27c17689d217d1&userId=26474751&enterpriseId=ff808081552a72f501552e14d9350042&clientTimeMillis=0&sign=Yk3iNrghgZnRQpN67v9ZEp5wHpXyMdEUyOnw1QhLTLE=");
        qqcomSampler.setMethod("GET");
        qqcomSampler.setName("Open qq.com");
        qqcomSampler.setProperty(TestElement.TEST_CLASS, HTTPSamplerProxy.class.getName());
        qqcomSampler.setProperty(TestElement.GUI_CLASS, HttpTestSampleGui.class.getName());
        System.out.println("******************************************************");
        return qqcomSampler;
    }


    //创建BackendListener
    private BackendListener creatBackendListener(){
        BackendListener backendListener = new BackendListener();
        backendListener.setQueueSize("5000");
        backendListener.setName("test");
        backendListener.setEnabled(true);

        Arguments arguments=new Arguments();
        arguments.addArgument("useRegexpForSamplersList","falseasdasd");
        backendListener.setArguments(arguments);

        backendListener.setProperty(TestElement.TEST_CLASS,BackendListener.class.getName());
        backendListener.setProperty(TestElement.GUI_CLASS, BackendListenerGui.class.getName());

        return backendListener;

    }

    private ConstantThroughputTimer creatConstantThroughputTimer(){
        ConstantThroughputTimer constantThroughputTimer=new ConstantThroughputTimer();


//        PropertyDescriptor p = (PropertyDescriptor) property("throughput");
//        p.setValue(NOT_UNDEFINED, Boolean.TRUE);
//        p.setValue(DEFAULT, new Double(0.0));

        constantThroughputTimer.setEnabled(true);
        constantThroughputTimer.setName("6666");
        constantThroughputTimer.setComment("123");
        constantThroughputTimer.setProperty("calcMode",2);
        constantThroughputTimer.setProperty("throughput",60);

        constantThroughputTimer.setProperty(TestElement.TEST_CLASS,ConstantThroughputTimer.class.getName());
        constantThroughputTimer.setProperty(TestElement.GUI_CLASS, TestBeanGUI.class.getName());


        return constantThroughputTimer;

    }


    public CSVDataSet creatCSVDataSet(){

        CSVDataSet csvDataSet =new CSVDataSet();

        csvDataSet.setName("userid");


        csvDataSet.setProperty("filename","filepath");
        csvDataSet.setProperty("recycle",true);
        csvDataSet.setProperty("fileEncoding","utf-8");
        csvDataSet.setProperty("variableNames","userid");
        csvDataSet.setProperty("ignoreFirstLine",false);
        csvDataSet.setProperty("delimiter","!!!!!!!!!");
        csvDataSet.setProperty("stopThread",false);
        csvDataSet.setProperty("shareMode","shareMode.al");
        csvDataSet.setProperty("quotedData",false);


//        csvDataSet.setFilename("path");
//        csvDataSet.setDelimiter("!!!!!");
//        csvDataSet.setFileEncoding("utf-8");
//        csvDataSet.setName("userid");
//        csvDataSet.setQuotedData(false);
//        csvDataSet.setShareMode("shareMode.all");
//        csvDataSet.setStopThread(false);


        csvDataSet.setProperty(TestElement.TEST_CLASS,CSVDataSet.class.getName());
        csvDataSet.setProperty(TestElement.GUI_CLASS,TestBeanGUI.class.getName());

        return csvDataSet;
    }
    //组装jmx脚本
    public void makeJmxScriptsWithComponents(JmxScripts jmxScripts, String scriptsPath) throws IOException {

        JmeterScriptsUtils jmeterScriptsUtils = new JmeterScriptsUtils();

        TestPlan testPlan = jmeterScriptsUtils.creatTestPlan(new TestPlanComponent());
        ThreadGroup threadGroup = jmeterScriptsUtils.creatThreadGroup(new ThreadGroupComponent());
        HTTPSamplerProxy httpSamplerProxy = jmeterScriptsUtils.creatHttpReauests();
        BackendListener backendListener=creatBackendListener();
        ConstantThroughputTimer constantThroughputTimer=creatConstantThroughputTimer();
        CSVDataSet csvDataSet = creatCSVDataSet();


        HashTree testPlanTree = new HashTree();
        testPlanTree.add(testPlan);

        HashTree threadGroupHashTree = testPlanTree.add(testPlan, threadGroup);


        HashTree httpSamplerProxyTree = new HashTree();

        httpSamplerProxyTree.add(httpSamplerProxy);
        threadGroupHashTree.add(httpSamplerProxyTree);
        threadGroupHashTree.add(constantThroughputTimer);
        threadGroupHashTree.add(backendListener);
        threadGroupHashTree.add(csvDataSet);

        SaveService.saveTree(testPlanTree, new FileOutputStream(scriptsPath));


        // 将生成的测试计划保存为JMeter的.jmx文件格式
//        SaveService.saveTree(testPlanTree, new FileOutputStream(this.scriptPathHome+jmxScripts.getTestPlanComponent().getName()+".jmx"));
//        SaveService.saveTree(testPlanTree, new FileOutputStream("/Users/zhouyu/Desktop/study/sjmx/src/main/resources/jmxscripts/example.jmx"));
        //        SaveService.saveElement(constantThroughputTimerTree, new FileOutputStream("/Users/zhouyu/Desktop/study/sjmx/src/main/resources/jmxscripts/example.jmx"));
//        var o = SaveService.loadElement(new FileInputStream("/Users/zhouyu/Desktop/study/sjmx/src/main/resources/jmxscripts/example.jmx"));
//        System.out.println();
//        System.out.println(constantThroughputTimer.getCalcMode());


    }

    public ResponseData creatJmxScripts(JmxScripts jmxScripts){


        String scriptsName = jmxScripts.getTestPlanComponent().getName();
        String scriptsPath = scriptsRootDir+"/"+scriptsName;

        File file = null;
        file = new File(scriptsPath);
        if(!file.exists()){
            file.mkdirs();
        }
        scriptsPath = scriptsPath+"/"+scriptsName+".jmx";
        try {
            makeJmxScriptsWithComponents(jmxScripts,scriptsPath);

//            System.out.println(scriptsPath);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseData.creatByErrorMsg("脚本创建异常");
        }
        return ResponseData.creatBySuccessMsg(scriptsPath,"脚本创建成功");
    }



}