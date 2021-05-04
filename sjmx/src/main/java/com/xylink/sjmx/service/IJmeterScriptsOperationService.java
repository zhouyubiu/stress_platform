package com.xylink.sjmx.service;

import com.xylink.sjmx.common.ResponseData;
import com.xylink.sjmx.pojo.JmxScripts;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @NAME: JmeterScriptsOperationService
 * @USER: zhouyu
 * @DATE: 2020/12/18
 */
public interface IJmeterScriptsOperationService {

    ResponseData run(JmxScripts jmxScripts) throws IOException;
    String stop(String jmeterScriptsPath) throws IOException, InterruptedException;
    Boolean isActice();
}
