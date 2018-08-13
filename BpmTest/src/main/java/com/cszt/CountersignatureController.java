/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: TurnDown
 * Author:   jj
 * Date:     2018/8/5 13:40
 * Description: 驳回任务
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cszt;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈会签流程〉
 *
 * @author jj
 * @create 2018/8/5
 * @since 1.0.0
 */
@RestController
@RequestMapping("/countersignatur")
public class CountersignatureController {
    @Autowired
    ProcessEngine processEngine;
    /**
     * 使用代码创建24张表
     */
    @GetMapping("/createTable")
    public String createTable() {
        ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration
                .createStandaloneProcessEngineConfiguration();
        processEngineConfiguration.setJdbcDriver("com.mysql.jdbc.Driver");
        processEngineConfiguration.setJdbcUrl("jdbc:mysql://localhost:3306/activiti?useUnicode=true&characterEncoding=utf8");
        processEngineConfiguration.setJdbcUsername("root");
        processEngineConfiguration.setJdbcPassword("123456");



        processEngineConfiguration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
        this.processEngine = ProcessEngines.getDefaultProcessEngine();
        System.out.println("processEngine = "+processEngine);
        return null;
    }
    /**
     * 部署流程定义
     */
    @GetMapping("/processEngine")
    public String processEngine() {
        Deployment deployment = processEngine.getRepositoryService()
                .createDeployment()
                .name("countersignatur 测试流程")
                .addClasspathResource("diagrams/countersignature.bpmn")
                .addClasspathResource("diagrams/countersignature.png")
                .deploy();
        System.out.println("部署Id:" + deployment.getId());
        System.out.println("部署名称:" + deployment.getName());
        return null;
    }
    /**
     * 启动流程实例
     */
    @GetMapping("/startProcessInstance")
    public String startProcessInstance() {
        //流程定义key
        String processDefinitionKey = "countersignatureProcess";
        //设置流程变量
        String[] arrs = {"tom","join","sing"};
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("assigneeList", Arrays.asList(arrs));
        map.put("signCount",0);
        ProcessInstance processInstance = processEngine.getRuntimeService()
                .startProcessInstanceByKey(processDefinitionKey,map);
        System.out.println("流程实例Id:" + processInstance.getId());
        System.out.println("流程定义ID:"+processInstance.getProcessDefinitionId());
        return null;
    }
    /**
     * 完成我的任务
     */
    @GetMapping("/complete")
    public void completeMyPersonalTask(String taskId) {
        List<Task> taskResultList = processEngine.getTaskService().createTaskQuery().taskId(taskId)
                .list();
        //当前executionId
        String currentExecutionId = taskResultList.get(0).getExecutionId();
        //当前签署总数
        String currentSignCount = StringUtils.defaultString(processEngine.getRuntimeService()
                .getVariable(currentExecutionId, "signCount").toString(), "0");
        //签署数+1
        processEngine.getRuntimeService().setVariable(currentExecutionId, "signCount",
                Integer.parseInt(currentSignCount) + 1);
        //完成
        processEngine.getTaskService().complete(taskId);
    }
}