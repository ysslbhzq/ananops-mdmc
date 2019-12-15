package com.ananops.provider.web.frontend;

import com.ananops.provider.model.dto.*;
import com.ananops.provider.service.MdmcDeviceService;
import com.ananops.provider.service.MdmcTaskItemService;
import com.ananops.provider.service.MdmcTaskLogService;
import com.ananops.provider.service.MdmcTaskService;
import com.ananops.provider.utils.WrapMapper;
import com.ananops.provider.utils.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhs on 2019/12/5 13:57
 */
@RestController
@RequestMapping(value = "ananops/api/v1/dmc",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "WEB - MdmcAddDevice",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DMCHanddler  {

    @Resource
    MdmcDeviceService deviceService;
    @Resource
    MdmcTaskItemService taskItemService;

    @Resource
    MdmcTaskService taskService;

    @Resource
    MdmcTaskLogService taskLogService;

    @GetMapping(value = "/getTaskList")
    @ApiOperation(httpMethod = "GET",value = "用户获取工单列表")
    public Wrapper<List<MdmcOrderDto>> getTaskList(@ApiParam(name = "user_id",value = "发起维修的用户id") @RequestParam Long user_id){
        List<MdmcOrderDto> orderDtoList;
        try {
            orderDtoList=taskService.getTaskList(user_id);
        }catch (Exception e){
            return WrapMapper.error(e.getMessage());
        }
        return WrapMapper.ok(orderDtoList);
    }

    @GetMapping(value = "/getPrincipalTaskList")
    @ApiOperation(httpMethod = "GET",value = "甲方获取工单列表")
    public Wrapper<List<MdmcPrincipleTaskDto>> getPrincipalTaskList(@ApiParam(name = "principal_id",value = "甲方id") @RequestParam Long principal_id){
        List<MdmcPrincipleTaskDto> principleTaskDtoList;
        try {
            principleTaskDtoList=taskService.getPrincipalTaskList(principal_id);
        }catch (Exception e){
            return WrapMapper.error(e.getMessage());
        }
        return WrapMapper.ok(principleTaskDtoList);
    }

    @GetMapping(value = "/getDeviceInfoList")
    @ApiOperation(httpMethod = "GET",value = "获取工单的设备信息列表")
    public Wrapper<List<MdmcDeviceInfoDto>> getDeviceInfoList(@ApiParam(name = "task_id",value = "工单id") @RequestParam Long task_id) {
        List<MdmcDeviceInfoDto> deviceInfoDtoList;
        try {
            deviceInfoDtoList = deviceService.getDeviceInfoList(task_id);
        } catch (Exception e) {
            return WrapMapper.error(e.getMessage());
        }
        return WrapMapper.ok(deviceInfoDtoList);
    }

    @GetMapping(value = "/getTroubleInfoList")
    @ApiOperation(httpMethod = "GET",value = "获取工单的故障信息列表")
    public Wrapper<List<MdmcTroubleInfoDto>> getTroubleInfoList(@ApiParam(name = "task_id",value = "工单id") @RequestParam Long task_id){
        List<MdmcTroubleInfoDto> troubleInfoDtoList;
        try {
            troubleInfoDtoList=taskItemService.getTroubleInfo(task_id);
        }catch (Exception e){
            return WrapMapper.error(e.getMessage());
        }
        return WrapMapper.ok(troubleInfoDtoList);
    }

    /*获取工单的维修过程信息*/
    @GetMapping(value = "/getProcessInfo")
    @ApiOperation(httpMethod = "GET",value = "获取工单的维修过程信息")
    public Wrapper<MdmcProcessInfoDto> processInfo(@ApiParam(name = "task_id",value = "工单id") @RequestParam Long task_id){
        MdmcProcessInfoDto processInfoDto;
        try {
            processInfoDto=taskService.getProcessInfo(task_id);
        }catch (Exception e){
            return WrapMapper.error(e.getMessage());
        }
        return WrapMapper.ok(processInfoDto);
    }

    @GetMapping(value = "/getApprovalInfo")
    @ApiOperation(httpMethod = "GET",value = "获取工单的审核信息")
    public Wrapper<MdmcApproveInfoDto> approvalInfo(@ApiParam(name = "task_id",value = "工单id") @RequestParam Long task_id){
        MdmcApproveInfoDto approveInfoDto;
        try {
            approveInfoDto=taskService.getApprovalInfo(task_id);
        }catch (Exception e){
            return WrapMapper.error(e.getMessage());
        }
        return WrapMapper.ok(approveInfoDto);
    }



//    @GetMapping(value = "/getstatustasklist")
//    @ApiOperation(httpMethod = "GET",value = "用户获取某一状态下所有报修单列表")
//    public Wrapper<List<MdmcTaskCategoryDto>> getStatusTaskList(@ApiParam(name = "status task list",value = "用户获取某一状态下所有报修单列表") @RequestParam Long user_id,@RequestParam Integer status){
//        List<MdmcTaskCategoryDto> taskList;
//        try {
//            taskList=taskItemService.getTaskListByStatus(user_id,status);
//        }catch (Exception e){
//            return WrapMapper.error(e.getMessage());
//        }
//        return WrapMapper.ok(taskList);
//    }

//    @GetMapping(value = "/getleveltasklist")
//    @ApiOperation(httpMethod = "GET",value = "获取用户某一紧急程度下所有工单列表")
//    public Wrapper<List<MdmcTaskCategoryDto>> getLevelTaskList(@ApiParam(name = "level task list",value = "获取用户某一紧急程度下所有工单列表") @RequestParam Long user_id,@RequestParam Integer level){
//        List<MdmcTaskCategoryDto> taskList;
//        try{
//            taskList=taskItemService.getTaskListByLevel(user_id,level);
//        }catch (Exception e){
//            return WrapMapper.error(e.getMessage());
//        }
//        return WrapMapper.ok(taskList);
//    }

    @GetMapping(value = "/getRepairsList")
    @ApiOperation(httpMethod = "GET",value = "维修工获取维修单列表")
    public Wrapper<List<MdmcRepairsTaskDto>> getRepairsList(@ApiParam(name = "maintainer_id",value = "维修工id") @RequestParam Long maintainer_id){
        List<MdmcRepairsTaskDto> repairsTaskDtoList;
        try {
            repairsTaskDtoList=taskService.getRepairsList(maintainer_id);
        }catch (Exception e){
            return WrapMapper.error(e.getMessage());
        }
        return WrapMapper.ok(repairsTaskDtoList);
    }

    @GetMapping(value = "/getFacilityTaskList")
    @ApiOperation(httpMethod = "GET",value = "服务商获取维修单列表")
    public Wrapper<List<MdmcRepairsTaskDto>> getFacilityTaskList(@ApiParam(name = "facilitator_id",value = "服务商id") @RequestParam Long facilitator_id){
        List<MdmcRepairsTaskDto> repairsTaskDtoList;
        try {
            repairsTaskDtoList=taskService.getFacilityTaskList(facilitator_id);
        }catch (Exception e){
            return WrapMapper.error(e.getMessage());
        }
        return WrapMapper.ok(repairsTaskDtoList);
    }

    @GetMapping(value = "/getTaskListByStatus")
    @ApiOperation(httpMethod = "GET",value = "按状态获取工单列表")
    public Wrapper<List<MdmcOrderDto>> getTaskListByStatus(@ApiParam(name = "status",value = "状态") @RequestParam Integer status){
        List<MdmcOrderDto> taskDtoList;
        try {
            taskDtoList=taskService.getTaskListByStatus(status);
        }catch (Exception e){
            return WrapMapper.error(e.getMessage());
        }
        return WrapMapper.ok(taskDtoList);
    }

    /*存疑*/
    @GetMapping(value = "/countTaskByStatus")
    @ApiOperation(httpMethod = "GET",value = "维修工获取显示不同状态的工单个数列表")
    public Wrapper<List<MdmcStatusRepairsDto>> getStatusCountList(@ApiParam(name = "maintainer_id",value = "维修工ID") @RequestParam Long maintainer_id){
        List<MdmcStatusRepairsDto> statusRepairsDtoList;
        try {
            statusRepairsDtoList=taskService.getRepairsStatusCountList(maintainer_id);
        }catch (Exception e){
            return WrapMapper.error(e.getMessage());
        }
        return WrapMapper.ok(statusRepairsDtoList);
    }

//    /*测试*/
//    @GetMapping(value = "/getAllTaskTerm")
//    @ApiOperation(httpMethod = "GET",value = "维修工获取显示不同状态的工单个数列表")
//    public Wrapper<List<TaskItem>> getStatusCountList(){
////        List<TaskItem> items;
////        try {
////            TaskItem item = new TaskItem();
////            item.setMaintainerId(Long.valueOf(123L));
////            item.setStatus(4);
////            items =  taskMapper.select(item);
////        }catch (Exception e){
////            return WrapMapper.error(e.getMessage());
////        }
//        return WrapMapper.ok();
//    }


//    @PostMapping(value = "/addTaskRecord")
//    @ApiOperation(httpMethod = "POST",value = "插入一条工单记录")
//    public Wrapper<Void> addTaskItem(@ApiParam(name = "MdmcOrderDto",value = "工单dto") @RequestBody MdmcOrderDto taskDto ){
//        try {
//            taskService.addTaskRecord(taskDto);
//        }catch (Exception e){
//            return WrapMapper.error(e.getMessage());
//        }
//        return WrapMapper.ok(taskItemService.addTaskRecord(taskDto));
//
//    }

    @GetMapping(value = "/getDeviceTroubleList")
    @ApiOperation(httpMethod = "GET",value = "服务商或维修工获取工单的设备故障信息列表")
    public Wrapper<List<MdmcDeviceTroubleDto>> getDeviceTrouble(@ApiParam(name = "task_id",value = "工单id") @RequestParam Long task_id){
        List<MdmcDeviceTroubleDto> deviceTroubleDtoList;
        try {
            deviceTroubleDtoList=taskItemService.getDeviceTroubleList(task_id);
        }catch (Exception e){
            return WrapMapper.error(e.getMessage());
        }
        return WrapMapper.ok(deviceTroubleDtoList);
    }

    @GetMapping(value = "/getWorkLoad")
    @ApiOperation(httpMethod = "GET",value = "获取工单的工作量信息")
    public Wrapper<MdmcWorkLoadDto> getWorkLoad(@ApiParam(name = "task_id",value = "工单id") @RequestParam Long task_id){
        MdmcWorkLoadDto workLoadDto;
        try {
            workLoadDto=taskService.getWorkLoad(task_id);
        }catch (Exception e){
            return WrapMapper.error(e.getMessage());
        }
        return WrapMapper.ok(workLoadDto);
    }

    @GetMapping(value = "/getProcessingList")
    @ApiOperation(httpMethod = "GET",value = "获取工单的处理进度列表")
    public Wrapper<List<MdmcProcessingDto>> getProcessingList(@ApiParam(name = "task_id",value = "工单id") @RequestParam Long task_id){
        List<MdmcProcessingDto> processingDtoList;
        try {
            processingDtoList=taskService.getProcessingList(task_id);
        }catch (Exception e){
            return WrapMapper.error(e.getMessage());
        }
        return WrapMapper.ok(processingDtoList);
    }
    @PostMapping(value = "/deleteTask")
    @ApiOperation(httpMethod = "POST",value = "撤销工单")
    public Wrapper<String> deleteTask(@ApiParam(name = "task_id",value = "工单id") @RequestParam Long task_id){
        try {
            String a=taskService.deleteTask(task_id);
            if (!a.equals("success")){
                return WrapMapper.error(a);
            }
        }catch (Exception e){
            return WrapMapper.error(e.getMessage());
        }
        return WrapMapper.ok("success");
    }

    @PostMapping(value = "/updateTask")
    @ApiOperation(httpMethod = "POST",value = "修改工单")
    public Wrapper<String> updateTask(@ApiParam(name = "taskDto",value = "修改工单") @RequestBody MdmcUpdateTaskDto updateTaskDto){
        try {
            String a =taskService.updateTask(updateTaskDto);
            if (!a.equals("success")){
                return WrapMapper.error(a);
            }
        }catch (Exception e){
            return WrapMapper.error(e.getMessage());
        }
        return WrapMapper.ok("success");
    }

    @PostMapping(value = "/submitTaskLog")
    @ApiOperation(httpMethod = "POST",value = "提交任务日志")
    public Wrapper<String> submitTaskLog(@ApiParam(name = "taskLog",value = "任务日志") @RequestBody MdmcTaskLogDto taskLogDto){
        try {
            String a=taskLogService.submitTaskLog(taskLogDto);
            if (!a.equals("success")){
                return WrapMapper.error(a);
            }

        }catch (Exception e){
            return WrapMapper.error(e.getMessage());
        }
        return WrapMapper.ok("success");
    }







}
