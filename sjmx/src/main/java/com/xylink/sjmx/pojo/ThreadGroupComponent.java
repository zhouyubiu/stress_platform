package com.xylink.sjmx.pojo;

import lombok.Data;

/**
 * @NAME: ThreadGroupComponent
 * @USER: zhouyu
 * @DATE: 2020/8/18
 */


@Data
public class ThreadGroupComponent {

    private String name="defaultThreadGroupName";
    private Integer threads=1;
    private Integer loopCounts=-1;

}
