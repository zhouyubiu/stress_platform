package com.xylink.sjmx.utils;

import com.google.common.io.CharStreams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.concurrent.TimeUnit;

/**
 * @NAME: ShellCommandUtils
 * @USER: zhouyu
 * @DATE: 2020/12/18
 */

@Component
public class ShellCommandUtils {

    @Value("${shell.shell-scripts-dir-path}")
    private String shellScriptsDirPath;

    @Value("${jmeter.path}")
    private String jmeterPath;


    private Runtime runtime=Runtime.getRuntime();

    private String getStringFromScripts(Process p) throws IOException {
        InputStream inputStream = p.getInputStream();
        return CharStreams.toString(new InputStreamReader(inputStream, "UTF-8"));
    }

    private Process exec(String command){
        Process process=null;
        try {
            process=runtime.exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return process;
    }

    public Boolean start(String jmeterScriptsPath,String resPath) throws IOException, InterruptedException {

        String command = "sh"+" "+shellScriptsDirPath+"/start_shell.sh"+" "+ jmeterPath + " "+ jmeterScriptsPath+" "+resPath;

//        System.out.println(command);
//        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        Process p = exec(command);

        String res = " ";
        int count = 0;

        while(res.equals(" ")&&count<3){
            res = getStringFromScripts(p);
            System.out.println(res);
            if(res.equals(" ")){
                TimeUnit.SECONDS.sleep(1);
                count++;
            }
        }
        return count < 3;
    }


    public String stop(String jmeterScriptsPath) throws IOException, InterruptedException {

        String command = "sh"+" "+shellScriptsDirPath+"/stop_shell.sh"+" "+ jmeterScriptsPath;
        Process p = exec(command);

        String res = "null";
        int count = 0;

        while(res.equals("null")&&count<3){
            res = getStringFromScripts(p);
            System.out.println(res);
            if(res.equals("null")){
                TimeUnit.SECONDS.sleep(1);
                count++;
            }
        }
        return res;



    }


    public static void main(String[] args) throws InterruptedException, IOException {
        ShellCommandUtils shellCommandUtils=new ShellCommandUtils();
//        Process p =shellCommandUtils.exec("sh /Users/zhouyu/Desktop/study/sjmx/src/main/resources/jmxscripts/test.sh");
//        InputStream inputStream = p.getInputStream();
//        String text = CharStreams.toString(new InputStreamReader(inputStream, "UTF-8"));
//        System.out.println(text);

//        String command = "nohup sh /Users/zhouyu/Downloads/apache-jmeter-5.1.1/bin/jmeter.sh -n -t /Users/zhouyu/Desktop/study/sjmx/src/main/resources/jmxscripts/TestPlanComponent/TestPlanComponent.jmx > /Users/zhouyu/Desktop/study/sjmx/src/main/resources/jmxscripts/TestPlanComponent/TestPlanComponent.txt 2>&1 &";

//        String command = "sh /Users/zhouyu/Desktop/study/sjmx/src/main/resources/jmxscripts/t.sh";
//        String command = "jmeter";
//        String command[] ={"/bin/sh", "-c", "nohup sh /Users/zhouyu/Downloads/apache-jmeter-5.1.1/bin/jmeter.sh -n -t /Users/zhouyu/Desktop/study/sjmx/src/main/resources/jmxscripts/TestPlanComponent/TestPlanComponent.jmx > /Users/zhouyu/Desktop/study/sjmx/src/main/resources/jmxscripts/TestPlanComponent/TestPlanComponent.txt 2>&1 &"};
        String command = "nohup sh /Users/zhouyu/Downloads/apache-jmeter-5.1.1/bin/jmeter.sh -n -t /Users/zhouyu/Desktop/study/sjmx/src/main/resources/jmxscripts/TestPlanComponent/TestPlanComponent.jmx > /Users/zhouyu/Desktop/study/sjmx/src/main/resources/jmxscripts/TestPlanComponent/TestPlanComponent.txt 2>&1 & ";
        Runtime runtime=Runtime.getRuntime();
        Process pp =runtime.exec(command);



//        System.out.println(pp);
//        Process pp =shellCommandUtils.exec(command);
//        System.out.println(shellCommandUtils.getStringFromScripts(pp));

//        TimeUnit.SECONDS.sleep(2);
        InputStream inputStream = pp.getInputStream();
        String text = CharStreams.toString(new InputStreamReader(inputStream, "UTF-8"));
        System.out.println(text);



    }





}
