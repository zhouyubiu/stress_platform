package com.pressure.test.login.utils;

import com.pressure.test.login.common.ZKCli;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;


/**
 * @NAME: ZKCliUtils
 * @USER: zhouyu
 * @DATE: 2020/12/13
 */
public class ZKCliUtils {


    private CuratorFramework curatorFramework;


    public  InterProcessLock lock(String ip){
        curatorFramework = ZKCli.getZKCli();
        curatorFramework.start();
        InterProcessMutex lock = new InterProcessMutex(curatorFramework,ip);
        try {
            lock.acquire();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lock;
    }

    public void release(InterProcessLock lock){
        try {
            lock.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
        curatorFramework.close();
    }




}
