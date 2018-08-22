/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: HelloController
 * Author:   jj
 * Date:     2018/8/2 20:29
 * Description: helloProcess测试流程
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cszt;

import org.activiti.engine.*;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.RuntimeServiceImpl;
import org.activiti.engine.impl.bpmn.behavior.ParallelMultiInstanceBehavior;
import org.activiti.engine.impl.bpmn.behavior.UserTaskActivityBehavior;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.ReadOnlyProcessDefinition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈helloProcess测试流程〉
 *
 * @author jj
 * @create 2018/8/2
 * @since 1.0.0
 */
@RestController
@RequestMapping("/hello")
public class HelloController {
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
                .name("hello测试流程")
                .addClasspathResource("diagrams/hello.bpmn")
                .addClasspathResource("diagrams/hello.png")
                .deploy();
        System.out.println("部署Id:" + deployment.getId());
        System.out.println("部署名称:" + deployment.getName());
//        IdentityService identityService = processEngine.getIdentityService();
//        //创建用户，用户组
//        Group group = identityService.newGroup("Leader");
//        group.setName("受理部门");
//        group.setType("assignment");
//        identityService.saveGroup(group);
//        User user = identityService.newUser("yty");
//        user.setId("bomgj");
//        user.setFirstName("jj");
//        user.setLastName("tt");
//        user.setEmail("111@qq.com");
//        identityService.saveUser(user);
//        identityService.createMembership("yty","deptLeader");
        return null;
    }
    /**
     * 启动流程实例
     */
    @GetMapping("/startProcessInstance")
    public String startProcessInstance() {
        //流程定义key
        String processDefinitionKey = "myProcess_1";
        //设置流程变量
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("day",5);

        ProcessInstance processInstance = processEngine.getRuntimeService()
                .startProcessInstanceByKey(processDefinitionKey,map);
        System.out.println("流程实例Id:" + processInstance.getId());
        System.out.println("流程定义ID:"+processInstance.getProcessDefinitionId());
        return null;
    }

    /**
     * 查询当前人的个人任务
     */
    @GetMapping("/findMyPersonalTask")
    public String findMyPersonalTask(String assignee) {
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
    public void completeMyPersonalTask() {
        String taskId = "10002";
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

    /**
     * 个人任务和组任务存放办理人对应的表：

     act_ru_identitylink表存放任务的办理人，包括个人任务和组任务，表示正在执行的任务

     act_hi_identitylink表存放任务的办理人，包括个人任务和组任务，表示历史任务

     区别在于：如果是个人任务TYPE的类型表示participant（参与者）

     如果是组任务TYPE的类型表示candidate（候选者）和participant（参与者）
     *
     * 查看CandidateUser任务
     * @return
     */
    @GetMapping("/findMyTask")
    public String findMyTask(){
        Task jackchenTask = processEngine.getTaskService()
                .createTaskQuery()
                .taskCandidateUser("李四")
                .singleResult();
        if(jackchenTask!=null){
            return "任务名称："+jackchenTask.getName()+"执行id："+jackchenTask.getExecutionId();
        }
        return null;
    }

    /**
     * 获取图片
     */
    @GetMapping("/getImage")
    public void getImage(){
        InputStream in = processEngine.getRepositoryService()
                .getProcessDiagram("myProcess_1:1:4");
        FileOutputStream out = null;
        FileInputStream in2 = null;
        try{
            out = new FileOutputStream("D:/hello.png");
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buf)) > 0) {
                out.write(buf, 0, bytesRead);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                in.close();
                out.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    /**
     *  根据ActivityId 查询出来想要活动Activity
     * @param id
     * @return
     */
    @GetMapping("/queryTargetActivity")//目标节点id  字段名act_id
    public ActivityImpl  queryTargetActivity(String id) {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        ReadOnlyProcessDefinition deployedProcessDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition("myProcess_1:2:5004");
        List<ActivityImpl> activities = (List<ActivityImpl>) deployedProcessDefinition.getActivities();
        for (ActivityImpl activityImpl : activities) {
            if (activityImpl.getId().equals(id)) {
                return activityImpl;
            }
        }
        return null;
    }
    /**
     * 查询当前的活动节点
     */
    @GetMapping("/qureyCurrentTask")//processDefinitionId
    public ActivityImpl qureyCurrentTask(String processDefinitionId){
        RuntimeService runtimeService = processEngine.getRuntimeService();
        Execution execution = runtimeService.createExecutionQuery().processDefinitionId(processDefinitionId).singleResult();
        String activityId = execution.getActivityId();
        ActivityImpl currentActivity = queryTargetActivity(activityId);
        System.out.println(currentActivity.getId()+""+currentActivity.getProperty("name"));
        return currentActivity;
    }

    /**
     *驳回任务
     */
    @GetMapping("/takeTaskForkOut")
    public void takeTaskForkOut () {
        //当前节点
        ActivityImpl currentActivityImpl = qureyCurrentTask("myProcess_1:2:5004");
        //目标节点
        ActivityImpl targetActivity = queryTargetActivity("_3");
        //当前正在执行的流程实例Id
        final String executionId="5005";
        ((RuntimeServiceImpl)processEngine.getRuntimeService()).getCommandExecutor().execute(new Command<Object>() {
            @Override
            public Object execute(CommandContext commandContext) {
                ExecutionEntity execution = commandContext.getExecutionEntityManager().findExecutionById(executionId);
                execution.destroyScope("_6");  //当前节点id   字段名act_id
                ProcessDefinitionImpl processDefinition = execution.getProcessDefinition();
                ActivityImpl findActivity = processDefinition.findActivity("_3");//目标节点id  字段名act_id
                execution.executeActivity(findActivity);
                return execution;
                }
        });
    }

    /**
     * 获取下一个节点信息
     * @param taskId
     * @return
     */
    @GetMapping("/getNextActivity")
    public String getNextActivity(String taskId){
        ProcessDefinitionEntity processDefinitionEntity = null;

        String id = null;

        TaskDefinition task = null;
        //获取流程实例Id信息
        String processInstanceId = processEngine.getTaskService().createTaskQuery().taskId(taskId).singleResult().getProcessInstanceId();

        //获取流程发布Id信息
        String definitionId = processEngine.getRuntimeService().createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult().getProcessDefinitionId();

        processDefinitionEntity = (ProcessDefinitionEntity) ((RepositoryServiceImpl) processEngine.getRepositoryService())
                .getDeployedProcessDefinition(definitionId);

        ExecutionEntity execution = (ExecutionEntity) processEngine.getRuntimeService().createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();

        //获取上一节点信息  act.getIncomingTransitions()效果一样
        HistoricTaskInstance curTask = processEngine.getHistoryService().createHistoricTaskInstanceQuery()
                .taskId(taskId)
                .singleResult();
        ActivityImpl activity = ((ProcessDefinitionImpl)processDefinitionEntity).findActivity(curTask.getTaskDefinitionKey());
        TaskDefinition taskDefinition1 =  ((UserTaskActivityBehavior)(activity.getActivityBehavior())).getTaskDefinition();
        System.out.println("************上一节点****************");
        System.out.println("处理人："+taskDefinition1.getAssigneeExpression().getExpressionText());
        System.out.println("节点名称："+activity.getProperty("name"));
        System.out.println("节点id："+activity.getId());

        //当前流程节点Id信息
        String activitiId = execution.getActivityId();
        //获取流程所有节点信息
        List<ActivityImpl> activitiList = processDefinitionEntity.getActivities();
        if(activitiList!=null&&activitiList.size()>0){
            for(ActivityImpl activityImp:activitiList){
                //获取该节点下一个节点信息
                String activityImpId = activityImp.getId();

                if(activityImpId.equals(activitiId)){
                    //获取从某个节点出来的所有线路
                    List<PvmTransition> pvmTransitionList = activityImp.getOutgoingTransitions();
                    for(PvmTransition p :pvmTransitionList){
                        PvmActivity ac = p.getDestination();
                        //actId->ActivityImpl
                        ActivityImpl act = queryTargetActivity(ac.getId());
//                        //画流程图
//                        System.out.println(act.getX()+" "+act.getY()+" "+act.getWidth()+" "+act.getHeight());
//                        ChangeImage.changeImage(act.getX(),act.getY(),act.getWidth(),act.getHeight());

                        TaskDefinition taskDefinition =  ((UserTaskActivityBehavior)(act.getActivityBehavior())).getTaskDefinition();
                        System.out.println("************下一节点****************");
                        System.out.println("处理人："+taskDefinition.getAssigneeExpression().getExpressionText());
                        System.out.println("节点名称："+ac.getProperty("name"));
                        System.out.println("节点id："+ac.getId());
                    }
                }
            }
        }
        return null;
    }
    /**
     * 查询的流程当前任务
     * @param processInstanceId
     * @return
     */
    @GetMapping("/findTask")
    public String findTask(String processInstanceId) {
        List<Task> taskList = processEngine.getTaskService()
                .createTaskQuery()
                .processInstanceId(processInstanceId)
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
            }
        } else {
            System.out.println("没有任务...");
        }
        return null;
    }
}