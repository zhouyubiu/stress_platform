package com.pressure.test.login.common;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.stereotype.Component;

/**
 * @NAME: ZKUtils
 * @USER: zhouyu
 * @DATE: 2020/12/13
 */

@Component
public class ZKCli {

    private static final String ZK_ADDRESS="172.25.49.121:2181,172.25.50.171:2181,172.25.50.162:2181,172.25.50.126:2181";

    private static final int BASE_SLEEP_TIME_MS=2000;

    private static final int MAX_RETRIES=3;

    private static final int SESSION_TIME_OUT_MS=6000;


    public static CuratorFramework getZKCli(){
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(BASE_SLEEP_TIME_MS, MAX_RETRIES);
        return CuratorFrameworkFactory.builder()
                .retryPolicy(retryPolicy)
                .connectString(ZK_ADDRESS)
                .sessionTimeoutMs(SESSION_TIME_OUT_MS)
                .build();
    }

}
