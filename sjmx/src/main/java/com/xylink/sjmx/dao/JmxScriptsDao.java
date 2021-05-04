package com.xylink.sjmx.dao;

import com.xylink.sjmx.common.ResponseData;
import com.xylink.sjmx.pojo.JmxScripts;
import com.xylink.sjmx.utils.JmeterScriptsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @NAME: JmxScriptsDao
 * @USER: zhouyu
 * @DATE: 2020/10/10
 */

@Component
public class JmxScriptsDao {

    @Autowired()
    private JmeterScriptsUtils jmeterScriptsUtils;


    public ResponseData creatJmxScripts(JmxScripts jmxs) throws IOException {
        return jmeterScriptsUtils.creatJmxScripts(jmxs);
    }



}
