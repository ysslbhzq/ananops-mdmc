package com.ananops.provider.web.frontend;

import com.ananops.provider.model.domain.MdmcTask;
import com.ananops.provider.model.domain.MdmcTaskItem;
import com.ananops.provider.model.domain.MdmcTaskLog;
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

    @GetMapping(value = "/getTaskListByClassify")
    @ApiOperation(httpMethod = "GET",value = "获取工单列表")
    public Wrapper<List<MdmcTask>> getTaskListByStatus(@ApiParam(name = "queryDto",value = "分类对象") @RequestBody MdmcQueryDto queryDto){
        List<MdmcTask> taskList;
        try {
            taskList=taskService.getTaskListByClassify(queryDto);

        }catch (Exception e){
            return WrapMapper.error(e.getMessage());
        }
        return WrapMapper.ok(taskList);
    }
    @GetMapping(value = "/getList")
    @ApiOperation(httpMethod = "GET",value = "获取工单列表（PC端）")
    public Wrapper<List<MdmcTask>> getList(@ApiParam(name = "queryDto",value = "分类对象") @RequestBody MdmcQueryDto queryDto){
        List<MdmcTask> taskList;
        try {
            taskList=taskService.getListDto(queryDto);
        }catch (Exception e){
            return WrapMapper.error(e.getMessage());
        }
        return WrapMapper.ok(taskList);
    }

    @GetMapping(value = "/getPageList")
    @ApiOperation(httpMethod = "GET",value = "分页获取工单列表（PC端）")
    public Wrapper<List<MdmcTask>> getPageList(@ApiParam(name = "pageDto",value = "分页dto") @RequestBody MdmcPageDto pageDto){
        List<MdmcTask> taskList;
        try {
            taskList=taskService.getPageList(pageDto);
        }catch (Exception e){
            return WrapMapper.error(e.getMessage());
        }
        return WrapMapper.ok(taskList);
    }



    @GetMapping(value = "/getDeviceList")
    @ApiOperation(httpMethod = "GET",value = "获取备件列表")
    public Wrapper<List<MdmcResultDto>> getMdmcResult(@ApiParam(name = "task_id",value = "工单id") @RequestParam Long task_id){
        List<MdmcResultDto> resultDtoList;
        try {
            resultDtoList=taskService.getResult(task_id);
        }catch (Exception e){
            return WrapMapper.error(e.getMessage());
        }
        return WrapMapper.ok(resultDtoList);
    }

//    /*存疑*/
//    @GetMapping(value = "/countStatus")
//    @ApiOperation(httpMethod = "GET",value = "获取工单状态个数列表")
//    public Wrapper<List<MdmcStatusRepairsDto>> getStatusCountList(){
//        List<MdmcStatusRepairsDto> statusRepairsDtoList;
//        try {
//            statusRepairsDtoList=taskService.getRepairsStatusCount();
//        }catch (Exception e){
//            return WrapMapper.error(e.getMessage());
//        }
//        return WrapMapper.ok(statusRepairsDtoList);
//    }

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
    @ApiOperation(httpMethod = "GET",value = "获取工单的设备故障信息列表")
    public Wrapper<List<MdmcTaskItem>> getDeviceTrouble(@ApiParam(name = "task_id",value = "工单id") @RequestParam Long task_id){
        List<MdmcTaskItem> taskItemList;
        try {
            taskItemList=taskItemService.getDeviceTroubleList(task_id);
        }catch (Exception e){
            return WrapMapper.error(e.getMessage());
        }
        return WrapMapper.ok(taskItemList);
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
    public Wrapper<List<MdmcTaskLog>> getTaskLogList(@ApiParam(name = "task_id",value = "工单id") @RequestParam Long task_id){
        List<MdmcTaskLog> taskLogList;
        try {
            taskLogList=taskService.getTaskLogList(task_id);
        }catch (Exception e){
            return WrapMapper.error(e.getMessage());
        }
        return WrapMapper.ok(taskLogList);
    }
//    @PostMapping(value = "/deleteTask")
//    @ApiOperation(httpMethod = "POST",value = "撤销工单")
//    public Wrapper<String> deleteTask(@ApiParam(name = "task_id",value = "工单id") @RequestParam Long task_id){
//        try {
//            String a=taskService.deleteTask(task_id);
//            if (!a.equals("success")){
//                return WrapMapper.error(a);
//            }
//        }catch (Exception e){
//            return WrapMapper.error(e.getMessage());
//        }
//        return WrapMapper.ok("success");
//    }

//    @PostMapping(value = "/updateTask")
//    @ApiOperation(httpMethod = "POST",value = "修改工单")
//    public Wrapper<String> updateTask(@ApiParam(name = "taskDto",value = "修改工单") @RequestBody MdmcUpdateTaskDto updateTaskDto){
//        try {
//            String a =taskService.updateTask(updateTaskDto);
//            if (!a.equals("success")){
//                return WrapMapper.error(a);
//            }
//        }catch (Exception e){
//            return WrapMapper.error(e.getMessage());
//        }
//        return WrapMapper.ok("success");
//    }

    //    @PostMapping(value = "/submitTaskLog")
//    @ApiOperation(httpMethod = "POST",value = "提交任务日志")
//    public Wrapper<String> submitTaskLog(@ApiParam(name = "taskLog",value = "任务日志") @RequestBody MdmcTaskLogDto taskLogDto){
//        try {
//            String a=taskLogService.submitTaskLog(taskLogDto);
//            if (!a.equals("success")){
//                return WrapMapper.error(a);
//            }
//
//        }catch (Exception e){
//            return WrapMapper.error(e.getMessage());
//        }
//        return WrapMapper.ok("success");
//    }
    @GetMapping(value = "/getTypeList")
    @ApiOperation(httpMethod = "GET",value = "返回枚举列表")
    public Wrapper<MdmcTypeDto> getTypeList(){
        MdmcTypeDto typeDto;
        try {
            typeDto=taskService.getTypeList();
        }catch (Exception e){
            return WrapMapper.error(e.getMessage());
        }
        return WrapMapper.ok(typeDto);
    }

    @GetMapping(value = "/getApprovalInfo")
    @ApiOperation(httpMethod = "GET",value = "获取工单的审核信息或接单结果")
    public Wrapper<MdmcCheckDto> approvalInfo(@ApiParam(name = "task_id",value = "工单id") @RequestParam Long task_id){
        MdmcCheckDto checkDto;
        try {
            checkDto=taskService.getApprovalInfo(task_id);
        }catch (Exception e){
            return WrapMapper.error(e.getMessage());
        }
        return WrapMapper.ok(checkDto);
    }





}
