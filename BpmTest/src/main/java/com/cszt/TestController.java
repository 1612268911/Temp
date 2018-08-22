/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: HelloWorld
 * Author:   jj
 * Date:     2018/7/23 17:54
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cszt;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author jj
 * @create 2018/7/23
 * @since 1.0.0
 */
@RestController
public class TestController {
    @Autowired
    ProcessEngine processEngine;
    /**
     * 使用代码创建24张表
     */
    @GetMapping("/init")
    public String init(){
        this.processEngine = ProcessEngines.getDefaultProcessEngine();
        return null;
    }
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
        System.out.println("processEngine = "+processEngine);
        init();
        return null;
    }
    /**
     * 部署流程定义
     */
    @GetMapping("/processEngine")
    public String processEngine() {
        Deployment deployment = processEngine.getRepositoryService()
                .createDeployment()
                .name("helloworld")
                .addClasspathResource("diagrams/test.bpmn")
                .addClasspathResource("diagrams/test.png")
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
        String processDefinitionKey = "myProcess";
        ProcessInstance processInstance = processEngine.getRuntimeService()
                .startProcessInstanceByKey(processDefinitionKey);
        System.out.println("流程实例Id:" + processInstance.getId());
        System.out.println("流程定义ID:"+processInstance.getProcessDefinitionId());
        return null;
    }

    /**
     * 查询当前人的个人任务
     */
    @GetMapping("/findMyPersonalTask")
    public String findMyPersonalTask(String assignee,String isDealWith) {
        List<Task> taskList = processEngine.getTaskService()
                .createTaskQuery()
                .taskAssignee(assignee)
                .list();
        if (!CollectionUtils.isEmpty(taskList)) {
            for (Task task : taskList) {
                System.out.println("任务Id：" + task.getId());
                System.out.println("任务名称：" + task.getName());
                System.out.println("任务创建时间：" + task.getCreateTime());
                System.out.println("任务办理人：" + task.getAssignee());
                System.out.println("任务流程实例Id：" + task.getProcessInstanceId());
                System.out.println("执行对象Id：" + task.getExecutionId());
                System.out.println("流程定义Id：" + task.getProcessDefinitionId());
                if(!StringUtils.isEmpty(isDealWith)){
                    System.out.println("**********************************************************");
                }
            }
        } else {
            System.out.println("没有任务...");
        }
        return null;
    }

    /**
     * 完成我的任务
     */
    @GetMapping("/complete")
    public void completeMyPersonalTask(String taskId) {
        processEngine.getTaskService().complete(taskId);
        System.out.println("完成任务Id:" + taskId);
    }
    @GetMapping("/findProcessDefinition")
    public String findProcessDefinition(){
        List<ProcessDefinition> list = processEngine.getRepositoryService()
                .createProcessDefinitionQuery()
                .orderByProcessDefinitionId()
                .desc()
                .list();
        if(list != null && list.size()>0){
            for(ProcessDefinition pd :list){
                System.out.println("流程定义id:"+pd.getId());
                System.out.println("流程定义key:"+pd.getKey());
                System.out.println("流程定义名称："+pd.getName());
                System.out.println("部署id:"+pd.getDeploymentId());
                System.out.println("版本:"+pd.getVersion());
            }
        }
        return null;
    }
    @GetMapping("/findDeployment")
    public String findDeployment(){
        List<Deployment> list = processEngine.getRepositoryService()
                .createDeploymentQuery()
                .orderByDeploymentId()
                .desc()
                .list();
        if(list !=null && list.size()>0){
            for(Deployment deployment:list){
                System.out.println("部署id："+deployment.getId());
                System.out.println("部署名称："+deployment.getName());
                System.out.println("部署Category:"+deployment.getCategory());
                System.out.println("部署时间："+deployment.getDeploymentTime());
            }
        }
        return null;
    }
    @GetMapping("/deleteDeployment")
    public String deleteDeployment1(){
        String deploymentId = "5001";
        processEngine.getRepositoryService()
                .deleteDeployment(deploymentId);//不能删除已启动流程
        return null;
    }
    @GetMapping("/deleteDeployment2")
    public String deleteDeployment2(){
        String deploymentId = "5001";
        processEngine.getRepositoryService()
                .deleteDeployment(deploymentId,true);//可删除已启动流程
        return null;
    }
}