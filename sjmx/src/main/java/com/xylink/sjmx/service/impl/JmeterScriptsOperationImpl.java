package com.xylink.sjmx.service.impl;

import com.xylink.sjmx.common.ResponseData;
import com.xylink.sjmx.pojo.JmxScripts;
import com.xylink.sjmx.service.IJmeterScriptsOperationService;
import com.xylink.sjmx.utils.JmeterScriptsUtils;
import com.xylink.sjmx.utils.ShellCommandUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/**
 * @NAME: JmeterScriptsOperationImpl
 * @USER: zhouyu
 * @DATE: 2020/12/18
 */
@Service
public class JmeterScriptsOperationImpl implements IJmeterScriptsOperationService {

    @Value("${jmeter.path}")
    private String jmeterPath;

    @Autowired
    ShellCommandUtils shellCommandUtils;

    @Autowired
    JmeterScriptsUtils jmeterScriptsUtils;

    @Override
    public ResponseData run(JmxScripts jmxScripts) throws IOException {

        ResponseData responseData = jmeterScriptsUtils.creatJmxScripts(jmxScripts);

        if (!responseData.isSuccessful()) {
            return responseData;
        }

        String jmeterScriptsPath = (String) responseData.getData();
        String resPath = jmeterScriptsPath.replace(".jmx", ".txt");
//        String command = "nohup sh " + jmeterPath +" -n -t " +jmeterScriptsPath +" > "
//                + resPath + " 2>&1 & ";

        Boolean res = false;
        try {
            res = shellCommandUtils.start(jmeterScriptsPath, resPath);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        if (!res) {
            return ResponseData.creatByErrorMsg("脚本运行失败");
        }

        return ResponseData.creatBySuccessMsg(true, "运行成功");

    }


    @Override
    public String stop(String jmeterScriptsPath) {
        String res = "";
        try {
            res = shellCommandUtils.stop(jmeterScriptsPath);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public Boolean isActice() {
        return null;
    }


    public void test() {
        System.out.println(jmeterPath);
    }


}
