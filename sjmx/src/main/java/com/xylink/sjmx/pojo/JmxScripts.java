package com.xylink.sjmx.pojo;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

/**
 * @NAME: JmxScripts
 * @USER: zhouyu
 * @DATE: 2020/8/2
 */

@Data
public class JmxScripts {

   private TestPlanComponent testPlanComponent;
   private ThreadGroupComponent threadGroupComponent;
   private HttpRequestsComponent httpRequestsComponentList;



}
