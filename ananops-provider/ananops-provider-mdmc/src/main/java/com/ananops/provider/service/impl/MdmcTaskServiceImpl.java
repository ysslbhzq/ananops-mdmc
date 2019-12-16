package com.ananops.provider.service.impl;

import com.ananops.provider.mapper.MdmcTaskItemLogMapper;
import com.ananops.provider.mapper.MdmcTaskItemMapper;
import com.ananops.provider.mapper.MdmcTaskLogMapper;
import com.ananops.provider.mapper.MdmcTaskMapper;
import com.ananops.provider.model.domain.MdmcTask;
import com.ananops.provider.model.domain.MdmcTaskItem;
import com.ananops.provider.model.domain.MdmcTaskLog;
import com.ananops.provider.model.dto.*;
import com.ananops.provider.service.MdmcTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhs on 2019/12/5 13:58
 */
@Slf4j
@Service
public class MdmcTaskServiceImpl implements MdmcTaskService {
    @Resource
    private MdmcTaskMapper taskMapper;

    @Resource
    private MdmcTaskItemMapper itemMapper;

    @Resource
    private MdmcTaskLogMapper taskLogMapper;

    @Resource
    private MdmcTaskItemLogMapper itemLogMapper;

    static private Map<Long, MdmcTask> taskMap = new ConcurrentHashMap<>();

    static private Map<Long,List<Long>> faciMap=new ConcurrentHashMap<>();
//    @Override
//    public MdmcTask changeTaskStatus(Long taskId, Integer status) {
//        MdmcTask taskCache;
//        // 查缓存
//        synchronized(taskMap ){
//            taskCache = taskMap.get(taskId);
//        }
//        if(taskCache == null) {
//            // 缓存没有查数据库
//            MdmcTask task = taskMapper.selectByPrimaryKey(taskId);
//            if (task == null) {
//                return null;
//            } else {
//                task.setStatus(status);
//                taskCache = task;
//            }
//        }
//        // 修改工单状态
//        taskMapper.updateByPrimaryKey(taskCache);
//        synchronized(taskMap){
//            taskCache = taskMap.put(taskId, taskCache);
//        }
//
//        return taskCache;
//    }
//
//    @Override
//    public MdmcTask getTaskInfo(Long taskId) {
//        MdmcTask taskCache;
//        synchronized(taskMap){
//            taskCache = taskMap.get(taskId);
//        }
//        if (taskCache == null) {
//            taskCache = taskMapper.selectByPrimaryKey(taskId);
//            if (taskCache != null) {
//                synchronized (taskMap) {
//                    taskMapper.insert(taskCache);`
//                }
//            }
//        }
//        return taskCache;
//    }
//
//    @Override
//    public MdmcTask saveTask(MdmcTask task) {
//        Long taskId = task.getId();
//        MdmcTask taskCache;
//        synchronized (taskMap) {
//            taskCache = taskMap.get(taskId);
//        }
//        if (taskCache == null) {
//            task = taskMapper.selectByPrimaryKey(taskId);
//            if (task == null) {
//                taskMapper.insertSelective(task);
//            }
//            taskCache = task;
//            synchronized (taskMap) {
//                taskMap.put(taskId, taskCache);
//            }
//        }
//        return taskCache;
//    }
//

    @Override
    public void timeLimit(final Long id, final int delay,List<Long> all){
        final Timer timer=new Timer();
        timer.schedule(new TimerTask(){
            int count=0;
            @Override
            public void run(){
                count++;
                MdmcTask mdmcTask=taskMapper.selectByPrimaryKey(id);
                int status=mdmcTask.getStatus();
                if(status==1&&count==delay/1000){                         //未接单状态
                    faciTransfer(id,all);                                             //调用转单方法
                    timer.cancel();
                }
                if(status==2){                                            //已接单
                    timer.cancel();
                }
            }
        },0,1000);
    }


    @Override
    public String submitTask(MdmcOrderDto orderDto) throws Exception{

//        // 获取报修用户信息
//        @NotNull
//        Long uId = order.getUId();
//        User user = new User().getUserInfo(); // 调用账户模块验证身份
//        if (user == null) {
//            log.error("用户{}不存在或权限不足",uId);
//            return "您的账号信息已过期，请重新注册";
//        }

//        log.info("开始创建维修任务...");
        MdmcTask task = new MdmcTask();
        // 报修用户id， 创建用户id， 报修用户名
//        task.setUserId(user.getUId());
//        task.setCreatorId(user.getUId());
//        task.setCreator(user.getName());
//        task.setLastOperatorId(user.getUId());
//        task.setLastOperator(user.getName());
        Random rand = new Random();
        Long task_id=rand.nextLong();
        task.setId(task_id);
        task.setUser_id(orderDto.getUserId());
        task.setTotalCost(orderDto.getTotalCost());
        task.setFacilitatorId(orderDto.getFacilitatorId());
        task.setPrincipalId(orderDto.getPrincipalId());
        task.setClearingForm(orderDto.getPayMode());
        task.setProjectId(orderDto.getProjectId());
        task.setTitle(orderDto.getTitle());

        int a=taskMapper.insert(task);
        if (a<1){return "出错";}

        List<Long> list=new ArrayList<>();
        list.add(orderDto.getFacilitatorId());
        faciMap.put(task_id,list);

        List<MdmcTaskItemDto> taskItemDtoList=orderDto.getTaskItems();
        for (int i=0;i<taskItemDtoList.size();i++){
            MdmcTaskItem taskItem=new MdmcTaskItem();
            Random rand1 = new Random();
            Long task__item_id=rand1.nextLong();
            taskItem.setId(task__item_id);
            taskItem.setDescription(taskItemDtoList.get(i).getDescription());
            taskItem.setDeviceId(taskItemDtoList.get(i).getDeviceId());
            taskItem.setDeviceLatitude(taskItemDtoList.get(i).getDeviceLatitude());
            taskItem.setDeviceLongitude(taskItemDtoList.get(i).getDeviceLongitude());
            taskItem.setDeviceName(taskItemDtoList.get(i).getDeviceName());
            int b=itemMapper.insert(taskItem);
           if (b<1){return "出错";}
        }
        return "success";

//        // 任务名称
//        String title = task.getTitle();
//        task.setTitle(title);
//        // 审核人id
//        Long principalId = order.getPrincipalId();
//        task.setPrincipalId(principalId);
//        // 项目id
//        Long projectId = order.getProjectId();
//        task.setProjectId(projectId);
//        // 服务商id
//        Long facilitatorId = order.getFacilitatorId();
//        task.setFacilitatorId(facilitatorId);
        // 自动生成报修时间 todo 设置数据库可以自动插入新建时间
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(new Date());
//        task.setCreatedTime(cal.getTime());
        //cal.add(Calendar.DAY_OF_WEEK, 1); //增加一周
        //Timestamp ddl = new Timestamp(cal.getTime().getTime()); // 计划完成时间
        // 设备地点
//        BigDecimal requestLatitude = order.getLatitude();
//        BigDecimal requestLongitude = order.getLongitude();
//        task.setRequestLatitude(requestLatitude);
//        task.setRequestLongitude(requestLongitude);
        // 当前状态
//        task.setStatus(MdmcTaskStatusEnum.ShenQing.getType());
//        // 总花费
//        BigDecimal totalCost = order.getTotalCost();
//        task.setTotalCost(totalCost);
//        // 结算方式
//        Integer clearingForm = order.getPayMode();
//        task.setClearingForm(clearingForm);
//
//        if( taskMapper.insertSelective(task) < 1) {
//            log.error("数据库插入记录失败");
//            return "数据库插入记录失败";
//        }
//        task = taskMapper.selectOne(task);
//        MdmcTaskLog taskLog = new MdmcTaskLog();
//        taskLog.setTaskId(task.getId());
//        taskLog.setStatus(task.getStatus());
//        taskLog.setStatusTimestamp(task.getUpdateTime());
//        taskLog.setLastOperatorId(user.getUId());
//        taskLog.setLastOperator(user.getName());
//        taskLog.setMovement("维修任务提交成功，等待审核");
//        taskLogMapper.insertSelective(taskLog);
//        log.info("创建维修任务成功{}", task.getId());


        // 新建维修任务子项
//        log.info("在维修任务{}下添加任务子项", task.getId());
//        List<MdmcTaskItemDto> tasks = order.getTaskItems();
//        for (MdmcTaskItemDto itemDto: tasks) {
//
//            String deviceName = itemDto.getDeviceName();                  // 设备名称
//            Long deviceId = itemDto.getDeviceId();
////            String deviceAddress = itemDto.get("deviceAddress").getAsString();                 // 设备地址
//            String troubleType = itemDto.getTroubleType();                     // 故障类型
//            String description = itemDto.getDescription();                     // 故障信息
//            BigDecimal deviceLatitude = itemDto.getDeviceLatitude();
//            BigDecimal deviceLongitude = itemDto.getDeviceLongitude();
//            String imageUtl = itemDto.getImageUrl();                           // 图片
//            String videoUrl = itemDto.getVideoUrl();                           // 视频
//            String audioUrl = itemDto.getAudioUrl();                           // 音频
//
//            MdmcTaskItem item = new MdmcTaskItem();
//            item.setTaskId(task.getId());
//            item.setDeviceId(deviceId);
//            item.setDeviceName(deviceName);
//            item.setDescription(description);
//            item.setDeviceLatitude(deviceLatitude);
//            item.setDeviceLongitude(deviceLongitude);
//            item.setStatus(MdmcTaskStatusEnum.ShenQing.getType());
//            if (itemMapper.insertSelective(item) != 0){
//                MdmcTaskItemLog itemLog = new MdmcTaskItemLog();
//                itemLog.setTaskId(task.getId());
//                itemLog.setStatus(MdmcTaskStatusEnum.ShenQing.getType());
//                itemLog.setTaskItemId(item.getId());
//                itemLog.setLastOperatorId(user.getUId());
//                itemLog.setLastOperator(user.getName());
//                itemLog.setMovement("维修任务提交成功，等待审核");
////                itemLogMapper.insertSelective(itemLog);
////            }
//        }
//
//        log.info("维修任务创建成功，等待审核。报修人{}, 任务编号{}", user.getUId(), task.getId());
//
//
////        // 发送消息给负责人 todo
////        new ToLeaderMsg<Order>().send(uId, leaderId, order);
//
//        return "success";
    }

//    @Override
//    public String leaderApprovePass(MdmcApproveInfoDto approveInfo) throws Exception {
//
//        // 获取工单信息，工单状态变为待执行
//        @NotNull
//        Long taskId = approveInfo.getTaskId();
//        MdmcTask task = changeTaskStatus(taskId, MdmcTaskStatusEnum.JieDan.getType());
//        if (task == null) {
//            log.error("维修工单{}不存在", taskId);
//            return String.format("维修工单%s不存在", taskId);
//        }
//
//        Long principalId = approveInfo.getApproverId();       // 负责人id
//        Long uId = approveInfo.getProposerId();                                    // 报修用户id
//        String approveReult = approveInfo.getApproveMsg();      // 审核意见
//
//        // 给报修用户发送消息
//        new AuditResultMsg().send(leaderId, uId, task, auditComments);
//        // 给服务提供商发送消息
//        new ToLeaderMsg<MdmcTask>().send(leaderId, SPid, task);
//
//        // 记录此次操作,工单状态追踪
//        Operation op = new Operation();
//        op.setOperator(leaderId);
//        op.setTaskId(taskId);
//        op.setInfo("维修任务审批通过");
//        opService.logger(op);
//
//    }
////
////    @Override
////    public void leaderApproveFail(String data) {
////        JsonObject approveInfo = new JsonParser().parse(data).getAsJsonObject();
////
////        // 获取工单信息, 工单状态退回申请中
////        Long taskId = approveInfo.get("taskId").getAsLong();
////        MdmcTask task = taskService.changeTaskStatus(taskId, status.ShenQing);
////        if (task == null) {
////            return;
////        }
////
////        Long leaderId = approveInfo.get("leaderId").getAsLong();   // 负责人id
////        Long uId = task.getUId();                                // 报修用户id
////        String auditComments = approveInfo.get("auditComment").getAsString();  // 审核意见
////
////        // 给报修用户发送消息
////        new AuditResultMsg().send(leaderId, uId, task, auditComments);
////
////        // 记录此次操作,工单状态追踪
////        Operation op = new Operation();
////        op.setOperator(leaderId);
////        op.setTaskId(taskId);
////        op.setInfo("维修任务审批驳回");
////        opService.logger(op);
////    }
////
////    @Override
////    public void cancelTask(String data) {
////        JsonObject json = new JsonParser().parse(data).getAsJsonObject();
////
////        // 获取工单信息, 工单状态变为已取消
////        Long taskId = json.get("taskId").getAsLong();
////        MdmcTask task = taskService.changeTaskStatus(taskId, status.QvXiao);
////        if (task == null) {
////            return;
////        }
////
////        Long uId = task.getUId();                                // 报修用户id
////        // 给审核人发消息
////        Long leaderId = json.get("leaderId").getAsLong();   // 负责人id
////        new ToLeaderMsg<MdmcTask>().send(leaderId, leaderId, task);
////        // 报修用户发消息
////        new ToLeaderMsg<MdmcTask>().send(uId, uId, task);
////        // 给服务提供商发送消息
////        Long SPId = task.getServiceProviderId();                 // 服务商id
////        new ToLeaderMsg<MdmcTask>().send(leaderId, SPId, task);
////        // 给维修工发送消息
////        for (MdmcTask task:task.getTaskList()){
////            Long workerId = task.getWorkerId();                   // 维修工id
////            new ToLeaderMsg<MdmcTask>().send(uId, workerId, task);
////        }
////
////        // 记录此次操作,工单状态追踪
////        Operation op = new Operation();
////        op.setOperator(uId);
////        op.setTaskId(taskId);
////        op.setInfo("已取消维修任务");
////        opService.logger(op);
////
////    }
////
////    @Override
////    public void serviceProviderReceiveTask(String data) {
////
////        JsonObject json = new JsonParser().parse(data).getAsJsonObject();
////
////        // 获取工单信息
////        Long taskId = json.get("taskId").getAsLong();
////        MdmcTask task = taskService.getTaskInfo(taskId);
////        if (task == null) {
////            return;
////        }
////
////        // 派发任务:根据服务提供商和报修单位签订的合同，以及履行改合同义务的维修列表，指派维修工
////        Long SPId = json.get("SPId").getAsLong();
//////        Long leaderId = json.get("leaderId").getAsLong();
////        Long workerId = dispatchTask(taskId);  // TODO 派发任务
////        if (workerId == null) {
////            System.out.println("维修工正忙，请联系服务提供商更换维修工");
////        }
////        for (MdmcTask task: task.getTaskList()){
////            task.setWorkerId(workerId);
////        }
////
////        // 计划完成时间，接单时间加一周
////        Calendar cal = Calendar.getInstance();
////        cal.setTime(new Date());
////        cal.add(Calendar.DAY_OF_WEEK, 1); //增加一周
////        Timestamp ddl = new Timestamp(cal.getTime().getTime()); // 计划完成时间
////        task.setDdl(ddl);
////        task = taskService.saveTask(task);
////
////        // 工单状态变为待执行
////        task = taskService.changeTaskStatus(taskId, status.ZhiXing);
////
////
////        // 给用户发送消息
////        Long uId = task.getUId();  // 报修用户id
////        new ToLeaderMsg<MdmcTask>().send(SPId, uId, task);
////        // 给维修工发消息
////        new ToLeaderMsg<MdmcTask>().send(SPId, workerId, task);
////
////        // 记录此次操作，工单状态追踪
////        Operation op = new Operation();
////        op.setOperator(SPId);
////        op.setTaskId(taskId);
////        op.setInfo("服务提供商已接单");
////        opService.logger(op);
////
////    }
////
////    @Override
////    public void serviceProviderRejectTask(String data) {
////        JsonObject json = new JsonParser().parse(data).getAsJsonObject();
////
////        // 获取工单信息，工单状态退回申请
////        Long taskId = json.get("taskId").getAsLong();
////        MdmcTask task = taskService.changeTaskStatus(taskId, status.ShenQing);
////        if (task == null) {
////            return;
////        }
////
////        // 通知报修用户
////        Long SPId = json.get("SPId").getAsLong();
////        Long uId = task.getUId();
////        new AuditResultMsg().send(SPId, uId, task,"服务提供商正忙，请稍后重新申请，或与服务提供商联系");
////
////        // 记录此次操作，工单状态追踪
////        Operation op = new Operation();
////        op.setOperator(SPId);
////        op.setTaskId(taskId);
////        op.setInfo("服务提供商正忙，请稍后重新申请，或与服务提供商联系");
////        opService.logger(op);
////
////    }
////
////    @Override
////    public void maintenanceWorkerReceiveTask(String data) {
////        JsonObject json = new JsonParser().parse(data).getAsJsonObject();
////
////        // 获取工单信息，工单状态改为维修中
////        Long taskId = json.get("taskId").getAsLong();
////        MdmcTask task = taskService.changeTaskStatus(taskId, status.WeiXiu);
////        if (task == null) {
////            return;
////        }
////
////        // 通知报修用户
////        Long workerId = json.get("workerId").getAsLong();
////        Long uId = task.getUId();
////        new AuditResultMsg().send(workerId, uId, task,"维修工已接单，请等待维修工联系");
////        // 通知服务提供商
////        Long SPId = json.get("SPId").getAsLong();
////        new AuditResultMsg().send(workerId, SPId, task,"维修工已接单");
////
////        // 记录此次操作，工单状态追踪
////        Operation op = new Operation();
////        op.setOperator(workerId);
////        op.setTaskId(taskId);
////        op.setInfo("维修工已接单，请等待维修工联系");
////        opService.logger(op);
////
////    }
////
////    @Override
////    public void maintenanceWorkerRejectTask(String data) {
////        JsonObject json = new JsonParser().parse(data).getAsJsonObject();
////
////        // 获取工单信息，工单状态保持待执行
////        Long taskId = json.get("taskId").getAsLong();
////        MdmcTask task = taskService.getTaskInfo(taskId);
////        if (task == null) {
////            return;
////        }
////
////        // 重新分派维修工
////        Long SPId = json.get("SPId").getAsLong();
////        Long leaderId = json.get("leaderId").getAsLong();
////        Long workerId = dispatchTask(taskId);  // TODO 派发任务
////        if (workerId == null) {
////            System.out.println("维修工正忙，请联系服务提供商更换维修工");
////        }
////        for (MdmcTask task: task.getTaskList()){
////            task.setWorkerId(workerId);
////        }
////
////        // 工单状态变为待执行
////        task = taskService.changeTaskStatus(taskId, status.ZhiXing);
////
////        // 通知报修用户
////        Long uId = task.getUId();
////        new AuditResultMsg().send(workerId, uId, task,"维修工正忙，当前转单中");
////
////        // 记录此次操作，工单状态追踪
////        Operation op = new Operation();
////        op.setOperator(workerId);
////        op.setTaskId(taskId);
////        op.setInfo("维修工正忙，当前转单中");
////        opService.logger(op);
////
////    }
////
////    @Override
////    public void maintenanceWorkerEnsureService(String data) {
////        JsonObject json = new JsonParser().parse(data).getAsJsonObject();
////
////        // 获取工单信息，工单状态改为待确认
////        Long taskId = json.get("taskId").getAsLong();
////        MdmcTask task = taskService.getTaskInfo(taskId);
////        if (task == null) {
////            return;
////        }
////
////        Long taskId = json.get("taskId").getAsLong();
////        Boolean result = json.get("result").getAsBoolean(); // 维修结果
////        String record = json.get("record").getAsString();   // 维修记录
////        task.setResult(result);
////        task.setRecord(record);
////
////        // 工单状态改为待报修用户确认服务
////        task = taskService.changeTaskStatus(taskId, status.QueRenFuWu);
////
////        // 通知报修用户
////        Long uId = task.getUId();
////        Long workerId = task.getTaskList().get(0).getWorkerId();
////        new AuditResultMsg().send(workerId, uId, task,"维修服务已完成，请您及时确认");
////
////        // 记录此次操作，工单状态追踪
////        Operation op = new Operation();
////        op.setOperator(workerId);
////        op.setTaskId(taskId);
////        op.setInfo("维修服务已完成，等待报修人确认");
////        opService.logger(op);
////
////
////    }
////
////    @Override
////    public void maintenanceWorkerExchangeTask(String data) {
////        JsonObject json = new JsonParser().parse(data).getAsJsonObject();
////
////        // 获取工单信息，工单状态保持待执行
////        Long taskId = json.get("taskId").getAsLong();
////        MdmcTask task = taskService.getTaskInfo(taskId);
////        if (task == null) {
////            return;
////        }
////
////        // 重新分派维修工
////        Long SPId = json.get("SPId").getAsLong();
////        Long leaderId = json.get("leaderId").getAsLong();
////        Long workerId = dispatchTask(taskId);  // TODO 派发任务
////        if (workerId == null) {
////            System.out.println("维修工正忙，请联系服务提供商更换维修工");
////        }
////        for (MdmcTask task: task.getTaskList()){
////            task.setWorkerId(workerId);
////        }
////
////        // 工单状态变为待执行
////        task = taskService.changeTaskStatus(taskId, status.ZhiXing);
////
////        // 通知报修用户
////        Long uId = task.getUId();
////        new AuditResultMsg().send(workerId, uId, task,"维修工取消任务，等待转单中");
////
////        // 记录此次操作，工单状态追踪
////        Operation op = new Operation();
////        op.setOperator(workerId);
////        op.setTaskId(taskId);
////        op.setInfo("维修工取消任务，当前等待转单中");
////        opService.logger(op);
////    }
////
////    @Override
////    public void maintenanceWorkerApplyForDevices(String data) {
////        JsonObject json = new JsonParser().parse(data).getAsJsonObject();
////
////        // 获取工单信息
////        Long taskId = json.get("taskId").getAsLong();
////        MdmcTask task = taskService.getTaskInfo(taskId);
////        if (task == null) {
////            return;
////        }
////
////        // 获取备件申请信息
////        DeviceTask deviceTask = new DeviceTask();
////        deviceTask.setTaskId(taskId);
////
////        JsonArray deviceArray = json.get("devices").getAsJsonArray();
////        List<Device> devices = null;
////        float totalCost = 0;
////        for (int i=0; i< deviceArray.size(); i++) {
////            JsonObject deviceJson = (JsonObject) deviceArray.get(i);
////            String serial = deviceJson.get("serial").getAsString(); // 设备编号
////            String name = deviceJson.get("name").getAsString(); // 设备编号
////            String manufacturer = deviceJson.get("manufacturer").getAsString(); // 设备编号
////            String model = deviceJson.get("model").getAsString(); // 设备编号
////            float cost = deviceJson.get("cost").getAsFloat(); // 设备编号
////            totalCost += cost;
////            devices.add(new Device(serial, name, manufacturer, model, cost));
////        }
////        deviceTask.setDevices(devices);
////        deviceTask.setTotalCost(totalCost);
////
////        deviceTaskService.saveDeviceTask(deviceTask);
////
////        task = taskService.changeTaskStatus(taskId, status.SPShenHeZhangDan);
////
////        // 通知报修用户
////        Long workerId = json.get("workerId").getAsLong();
////        Long uId = task.getUId();
////        new AuditResultMsg().send(workerId, uId, task, "提交设备订单申请，服务提供商正在审核中");
////        Long SPId = task.getServiceProviderId();
////        new AuditResultMsg().send(workerId, SPId, task, "收到一份新的设备订单申请，请及时审核");
////
////        // 记录此次操作，工单状态追踪
////        Operation op = new Operation();
////        op.setOperator(workerId);
////        op.setTaskId(taskId);
////        op.setInfo("提交设备订单申请，服务提供商正在审核中");
////        opService.logger(op);
////
////    }
////
////    @Override
////    public void ensureService(String data) {
////        JsonObject json = new JsonParser().parse(data).getAsJsonObject();
////
////        // 获取工单信息, 工单状态变为待验收
////        Long taskId = json.get("taskId").getAsLong();
////        MdmcTask task = taskService.changeTaskStatus(taskId, status.YanShou);
////        if (task == null) {
////            return;
////        }
////
////        Long uId = task.getUId();                                // 报修用户id
////        // 给审核人发消息
////        Long leaderId = json.get("leaderId").getAsLong();   // 负责人id
////        new AuditResultMsg().send(uId, leaderId, task, "维修服务确认完成，请您及时支付");
////        // 给维修工发送消息
////        Long workerId = task.getTaskList().get(0).getWorkerId();
////        new AuditResultMsg().send(uId, leaderId, task, "用户确认维修服务完成");
////
////        // 记录此次操作,工单状态追踪
////        Operation op = new Operation();
////        op.setOperator(uId);
////        op.setTaskId(taskId);
////        op.setInfo("已确认维修服务完成");
////        opService.logger(op);
////    }
////
////    @Override
////    public void serviceProviderApproveBillPass(String data) {
////        JsonObject json = new JsonParser().parse(data).getAsJsonObject();
////
////        // 获取工单信息, 工单状态变为待审核人确认账单
////        Long taskId = json.get("taskId").getAsLong();
////        MdmcTask task = taskService.changeTaskStatus(taskId, status.LDShenHeZhangDan);
////        if (task == null) {
////            return;
////        }
////
////        Long deviceTaskId = json.get("deviceTaskId").getAsLong();
////        DeviceTask deviceTask = deviceTaskService.getDeviceTaskInfo(deviceTaskId);
////        if (deviceTask == null) {
////            return;
////        }
////        JsonArray deviceArray = json.get("devices").getAsJsonArray();
////        float discount = json.get("discount").getAsFloat();
////        if (discount-0 < 1.0e-6) {
////            discount = 1;
////        }
////        float totalCost = 0;
////        for (int i=0; i<deviceArray.size(); i++) {
////            JsonObject deviceJson = (JsonObject)deviceArray.get(0);
////            float cost = deviceJson.get("cost").getAsFloat();
////            totalCost += cost;
////        }
////        totalCost *= discount;
////        deviceTask.setTotalCost(totalCost);
////        deviceTaskService.saveDeviceTask(deviceTask);
////
////        // 给负责人发送消息
////        Long leaderId = json.get("leaderId").getAsLong(); // 负责人id
////        Long SPId = task.getServiceProviderId(); // 服务商id
////        Long workerId = task.getTaskList().get(0).getWorkerId();
////        String auditComments = json.get("auditComment").getAsString(); // 审核意见
////        new AuditResultMsg().send(SPId, workerId, task, auditComments);
////        new AuditResultMsg().send(SPId, leaderId, task, "您有新的账单等待审核，请及时查阅");
////
////
////        // 记录此次操作,工单状态追踪
////        Operation op = new Operation();
////        op.setOperator(SPId);
////        op.setTaskId(taskId);
////        op.setInfo("服务提供商审核备件更换申请通过，审核人审核中");
////        opService.logger(op);
////
////    }
////
////    @Override
////    public void serviceProviderApproveBillFail(String data) {
////
////        JsonObject json = new JsonParser().parse(data).getAsJsonObject();
////
////        // 获取工单信息, 工单状态变为维修中
////        Long taskId = json.get("taskId").getAsLong();
////        MdmcTask task = taskService.changeTaskStatus(taskId, status.WeiXiu);
////        if (task == null) {
////            return;
////        }
////
////        // 给负责人发送消息
////        Long SPId = task.getServiceProviderId(); // 服务商id
////        Long workerId = task.getTaskList().get(0).getWorkerId();
////        String auditComments = json.get("auditComment").getAsString(); // 审核意见
////        new AuditResultMsg().send(SPId, workerId, task, auditComments);
////
////
////        // 记录此次操作,工单状态追踪
////        Operation op = new Operation();
////        op.setOperator(SPId);
////        op.setTaskId(taskId);
////        op.setInfo("备件更换申请被驳回，请重新提交");
////        opService.logger(op);
////
////    }
////
////    @Override
////    public void leaderApproveBillPass(String data) {
////        JsonObject json = new JsonParser().parse(data).getAsJsonObject();
////
////        // 获取工单信息, 工单状态变为维修中
////        Long taskId = json.get("taskId").getAsLong();
////        MdmcTask task = taskService.changeTaskStatus(taskId, status.WeiXiu);
////        if (task == null) {
////            return;
////        }
////
////        Long SPId = task.getServiceProviderId(); // 服务商id
////        Long workerId = task.getTaskList().get(0).getWorkerId();
////        Long leaderId = task.getLeaderId();
////        String auditComments = json.get("auditComment").getAsString(); // 审核意见
////        // 通知服务商
////        new AuditResultMsg().send(leaderId, SPId, task, auditComments);
////        // 通知维修工
////        new AuditResultMsg().send(SPId, workerId, task, auditComments);
////
////        // 记录此次操作,工单状态追踪
////        Operation op = new Operation();
////        op.setOperator(SPId);
////        op.setTaskId(taskId);
////        op.setInfo("审核人通过备件更换申请，请开始维修");
////        opService.logger(op);
////
////    }
////
////    @Override
////    public void leaderApproveBillFail(String data) {
////        JsonObject json = new JsonParser().parse(data).getAsJsonObject();
////
////        // 获取工单信息, 工单状态变维修中
////        Long taskId = json.get("taskId").getAsLong();
////        MdmcTask task = taskService.changeTaskStatus(taskId, status.WeiXiu);
////        if (task == null) {
////            return;
////        }
////
////        Long SPId = task.getServiceProviderId(); // 服务商id
////        Long workerId = task.getTaskList().get(0).getWorkerId();
////        Long leaderId = task.getLeaderId();
////        String auditComments = json.get("auditComment").getAsString(); // 审核意见
////        // 通知服务商
////        new AuditResultMsg().send(leaderId, SPId, task, auditComments);
////        // 通知维修工
////        new AuditResultMsg().send(SPId, workerId, task, auditComments);
////
////        // 记录此次操作,工单状态追踪
////        Operation op = new Operation();
////        op.setOperator(SPId);
////        op.setTaskId(taskId);
////        op.setInfo("审核人驳回备件更换申请，请重新提交");
////        opService.logger(op);
////    }
////
////    @Override
////    public void leaderEnsureAndPay(String data) {
////        JsonObject json = new JsonParser().parse(data).getAsJsonObject();
////
////        // 获取工单信息, 工单状态变成待评价
////        Long taskId = json.get("taskId").getAsLong();
////        MdmcTask task = taskService.changeTaskStatus(taskId, status.PingJia);
////        if (task == null) {
////            return;
////        }
////
////        Long SPId = task.getServiceProviderId(); // 服务商id
////        Long workerId = task.getTaskList().get(0).getWorkerId();
////        Long leaderId = task.getLeaderId();
////        Long uId = task.getUId();
////        // 通知服务商
////        new AuditResultMsg().send(leaderId, SPId, task, "维修服务已支付");
////        // 通知维修工
////        new AuditResultMsg().send(SPId, workerId, task, "维修服务已支付");
////        // 通知报修用户
////        new AuditResultMsg().send(SPId, uId, task, "维修服务已支付,请及时评价");
////
////        // 记录此次操作,工单状态追踪
////        Operation op = new Operation();
////        op.setOperator(leaderId);
////        op.setTaskId(taskId);
////        op.setInfo("维修服务已支付");
////        opService.logger(op);
////    }
////
////    @Override
////    public void leaderRejectPay(String data) {
////        // todo
////    }
////
////    @Override
////    public void evaluate(String data) {
////        JsonObject json = new JsonParser().parse(data).getAsJsonObject();
////
////        // 获取工单信息, 工单状态变成已完成
////        Long taskId = json.get("taskId").getAsLong();
////        MdmcTask task = taskService.changeTaskStatus(taskId, status.WanCheng);
////        if (task == null) {
////            return;
////        }
////
////        Long uId = task.getUId();
////        int score = json.get("score").getAsInt();
////        String content = json.get("content").getAsString();
////        Review review = new Review();
////        review.setUserId(uId);
////        review.setScore(score);
////        review.setContents(content);
////        review = reviewService.saveReview(review);
////        task.setReviewId(review.getId());
////
////        // 记录此次操作,工单状态追踪
////        Operation op = new Operation();
////        op.setOperator(uId);
////        op.setTaskId(taskId);
////        op.setInfo("维修服务已完成");
////        opService.logger(op);
////    }
////
////    @Override
////    public Long dispatchTask(Long taskId) {
////
////        MdmcTask task = taskService.getTaskInfo(taskId);
////        if (task == null) {
////            System.err.println(new StringBuilder().append("无法分配维修工，工单").append(taskId).append("不存在"));
////            return null;
////        }
////
////        // todo 根据合同设置的维修工名单和故障类型，随机选择一位维修工，后面设计更好的分配策略\
////        return keyConstructor.nextGlobalId();
////    }

    public List<MdmcStatusRepairsDto> getRepairsStatusCountList(Long maintainer_id){
        List<MdmcStatusRepairsDto> statusRepairsDtoList=new ArrayList<>();
        for (Integer type=4;type<=6;type++){//type指的是status
            MdmcStatusRepairsDto statusRepairsDto=new MdmcStatusRepairsDto();
            List<MdmcTask> taskList=taskMapper.selectByMaintainerIdAndStatus(maintainer_id,type);
            statusRepairsDto.setCount(taskList.size());
            statusRepairsDtoList.add(statusRepairsDto);
        }

        return statusRepairsDtoList;
    }

    @Override
    public List<MdmcOrderDto> getTaskList(Long user_id) {
        List<MdmcOrderDto> orderDtoList=new ArrayList<>();
        MdmcTask task=new MdmcTask();
        task.setUser_id(user_id);
        List<MdmcTask> taskList=taskMapper.select(task);
        for (int i=0;i<taskList.size();i++){
            MdmcOrderDto orderDto=new MdmcOrderDto();

            orderDto.setFacilitatorId(taskList.get(i).getFacilitatorId());
            orderDto.setPayMode(taskList.get(i).getClearingForm());
            orderDto.setPrincipalId(taskList.get(i).getPrincipalId());
            orderDto.setProjectId(taskList.get(i).getProjectId());
            orderDto.setTitle(taskList.get(i).getTitle());
            orderDto.setTotalCost(taskList.get(i).getTotalCost());
            orderDto.setStatus(taskList.get(i).getStatus());
            orderDtoList.add(orderDto);
        }
        return orderDtoList;
    }

    @Override
    public List<MdmcProcessingDto> getProcessingList(Long task_id) {
        List<MdmcProcessingDto> processingDtoList=new ArrayList<>();
        MdmcTaskLog taskLog=new MdmcTaskLog();
        taskLog.setTaskId(task_id);
        List<MdmcTaskLog> taskLogList=taskLogMapper.select(taskLog);
        for (int i=0;i<taskLogList.size();i++){
            MdmcProcessingDto processingDto=new MdmcProcessingDto();
            processingDto.setLast_operator(taskLogList.get(i).getLastOperator());
            processingDto.setStatus(taskLogList.get(i).getStatus());
            processingDto.setMovement(taskLogList.get(i).getMovement());
            processingDto.setStatus_timestamp(taskLogList.get(i).getStatusTimestamp());
            processingDtoList.add(processingDto);
        }

        return processingDtoList;
    }

    @Override
    public MdmcProcessInfoDto getProcessInfo(Long task_id) {

        MdmcProcessInfoDto processInfoDto=new MdmcProcessInfoDto();
        MdmcTask task=taskMapper.selectByPrimaryKey(task_id);
        processInfoDto.setEndTime(task.getActualFinishTime());
        processInfoDto.setStartTime(task.getActualStartTime());
        processInfoDto.setOrderId(task_id);
        processInfoDto.setStatus(task.getStatus());
        processInfoDto.setTitle(task.getTitle());

        return processInfoDto;
    }

    @Override
    public List<MdmcRepairsTaskDto> getRepairsList(Long maintainer_id) {
        List<MdmcRepairsTaskDto> repairsTaskDtoList=new ArrayList<>();
        MdmcTaskItem taskItem=new MdmcTaskItem();
        taskItem.setMaintainerId(maintainer_id);
        List<MdmcTaskItem> taskItemList=itemMapper.select(taskItem);
        for (int i=0;i<taskItemList.size();i++){
        MdmcRepairsTaskDto repairsTaskDto=new MdmcRepairsTaskDto();
        MdmcTask task=new MdmcTask();
        task=taskMapper.selectByPrimaryKey(taskItemList.get(i).getTaskId());
        repairsTaskDto.setOrderId(taskItemList.get(i).getTaskId());
        repairsTaskDto.setCreateTime(task.getCreatedTime());
        repairsTaskDto.setStatus(task.getStatus());

        repairsTaskDtoList.add(repairsTaskDto);
        }
        return repairsTaskDtoList;
    }

    @Override
    public List<MdmcRepairsTaskDto> getFacilityTaskList(Long facilitator_id) {
        List<MdmcRepairsTaskDto> repairsTaskDtoList=new ArrayList<>();
        MdmcTask task=new MdmcTask();
        task.setFacilitatorId(facilitator_id);
        List<MdmcTask> taskList=taskMapper.select(task);
        for (int i=0;i<taskList.size();i++){
            MdmcRepairsTaskDto repairsTaskDto=new MdmcRepairsTaskDto();
            repairsTaskDto.setOrderId(taskList.get(i).getId());
            repairsTaskDto.setStatus(taskList.get(i).getStatus());
            repairsTaskDto.setCreateTime(taskList.get(i).getCreatedTime());
            repairsTaskDtoList.add(repairsTaskDto);
        }
        return repairsTaskDtoList;
    }

    @Override
    public List<MdmcPrincipleTaskDto> getPrincipalTaskList(Long principal_id) {
        List<MdmcPrincipleTaskDto> principleTaskDtoList=new ArrayList<>();
        MdmcTask task=new MdmcTask();
        task.setFacilitatorId(principal_id);
        List<MdmcTask> taskList=taskMapper.select(task);
        for (int i=0;i<taskList.size();i++){
            MdmcPrincipleTaskDto principleTaskDto=new MdmcPrincipleTaskDto();
            principleTaskDto.setFacilitatorId(taskList.get(i).getFacilitatorId());
            principleTaskDto.setPayMode(taskList.get(i).getClearingForm());
            principleTaskDto.setPrincipalId(taskList.get(i).getPrincipalId());
            principleTaskDto.setProjectId(taskList.get(i).getProjectId());
            principleTaskDto.setTitle(taskList.get(i).getTitle());
            principleTaskDto.setTotalCost(taskList.get(i).getTotalCost());
            principleTaskDtoList.add(principleTaskDto);
        }
        return principleTaskDtoList;
    }

    @Override
    public MdmcApproveInfoDto getApprovalInfo(Long task_id) {
        MdmcApproveInfoDto approveInfoDto=new MdmcApproveInfoDto();
        approveInfoDto.setTaskId(task_id);
        MdmcTask task=taskMapper.selectByPrimaryKey(task_id);
        approveInfoDto.setProposer(task.getCreator());
        approveInfoDto.setProposerId(task.getCreatorId());
        approveInfoDto.setStatus(task.getStatus());

        return approveInfoDto;
    }

    @Override
    public MdmcWorkLoadDto getWorkLoad(Long task_id) {
        MdmcWorkLoadDto workLoadDto=new MdmcWorkLoadDto();
        MdmcTask task=taskMapper.selectByPrimaryKey(task_id);
        workLoadDto.setActual_start_time(task.getActualStartTime());
        workLoadDto.setActual_finish_time(task.getActualFinishTime());
        workLoadDto.setCreator(task.getCreator());

        return workLoadDto;
    }

    @Override
    public List<MdmcOrderDto> getTaskListByStatus(Integer status) {
        List<MdmcOrderDto> taskDtoList=new ArrayList<>();
        MdmcTask task=new MdmcTask();
        task.setStatus(status);
        List<MdmcTask> taskList=taskMapper.select(task);
        for (int i=0;i<taskList.size();i++){
            MdmcOrderDto taskDto=new MdmcOrderDto();
            taskDto.setTotalCost(taskList.get(i).getTotalCost());
            taskDto.setTitle(taskList.get(i).getTitle());
            taskDto.setProjectId(taskList.get(i).getProjectId());
            taskDto.setPrincipalId(taskList.get(i).getPrincipalId());
            taskDto.setPayMode(taskList.get(i).getClearingForm());
            taskDto.setFacilitatorId(taskList.get(i).getFacilitatorId());

            taskDtoList.add(taskDto);
        }
        return taskDtoList;
    }

    @Override
    public String deleteTask(Long task_id) {


        int i=taskMapper.deleteByPrimaryKey(task_id);
        if (i<1){return "出错";}
        return "success";

    }

    @Override
    public String faciTransfer(Long taskId,List<Long> all){
        int count=0;
        Long faci=null;
      List<Long> list=faciMap.get(taskId);                           //获取当前任务id已分配过的服务商
      for(Long faciId:all){
          count++;
          if (!list.contains(faciId)) {                               //判断当前服务商是否已被分配过
              faci=faciId;
              list.add(faciId);
              faciMap.put(taskId,list);
              break;
          }
          if(count==list.size()){
             return "无服务商接单";                         //无服务商接单
          }
      }
      MdmcTask mdmcTask=taskMapper.selectByPrimaryKey(taskId);
      mdmcTask.setFacilitatorId(faci);
      if (taskMapper.updateByPrimaryKey(mdmcTask)==1){
          return "success";
      }
          return  "failed";
    }

    @Override
   public void deleteFaciMap(MdmcUpdateTaskDto updateTaskDto){
        Long taskId=updateTaskDto.getOrderId();
         faciMap.remove(taskId);
    }

    @Override
    public String updateTask(MdmcUpdateTaskDto updateTaskDto) {
        MdmcTask task= taskMapper.selectByPrimaryKey(updateTaskDto.getOrderId());


        task.setStatus(updateTaskDto.getStatus());
        task.setTotalCost(updateTaskDto.getTotal_cost());

        return "success";
    }

    @Override
    public String submitApproval(MdmcApproveInfoDto approveInfoDto) {

        MdmcTask task=new MdmcTask();
        task.setId(approveInfoDto.getTaskId());
        List<MdmcTask> taskList=taskMapper.select(task);
        taskList.get(0).setUser_id(approveInfoDto.getProposerId());
        taskList.get(0).setStatus(approveInfoDto.getStatus());
        return "success";
    }


//    @Override
//    public Void addTaskRecord(MdmcOrderDto taskDto) {
//        MdmcTask task=new MdmcTask();
//        int result=taskMapper.insert(task);
//        return null;
//    }
}
