package com.ananops.provider.web.frontend;

import com.ananops.provider.model.dto.MdmcApproveInfoDto;
import com.ananops.provider.model.dto.MdmcOrderDto;
import com.ananops.provider.model.dto.MdmcUpdateTaskDto;
import com.ananops.provider.service.MdmcDeviceService;
import com.ananops.provider.service.MdmcTaskService;
import com.ananops.provider.utils.WrapMapper;
import com.ananops.provider.utils.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhs on 2019/12/5 13:57
 */
@RestController
@RequestMapping(value = "ananops/api/v1/task",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "WEB - MdmcAddDevice",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MdmcTaskHandler {
    @Resource
    MdmcTaskService taskService;
    @Resource
    MdmcDeviceService deviceService;

    @PostMapping(value = "/submit/{taskId}")
    @ApiOperation(httpMethod = "POST",value = "提交维修任务申请")
    public Wrapper<String> submit(@ApiParam(name = "submitTask",value = "提交维修任务申请") @RequestBody MdmcOrderDto orderDto){
        try {
            String res = taskService.submitTask(orderDto);
            if (!res.equals("success")) {
                return WrapMapper.error(res);
            }
        } catch (Exception e){
            return WrapMapper.error(e.getMessage());
        }
        return WrapMapper.ok("success");
    }

    @PostMapping(value = "/emergency")
    @ApiOperation(httpMethod = "POST",value = "提交紧急维修任务申请")
    public Wrapper<String> Emergency(@ApiParam(name = "emergencyTask",value = "提交紧急维修任务申请") @RequestBody MdmcOrderDto orderDto,
                                     @ApiParam(name = "delay",value = "最长待接单时间") @RequestParam int delay){
        try {
            List<Long> all=new ArrayList<>();//获取所有服务商
            String res = taskService.submitTask(orderDto);
            taskService.timeLimit(orderDto.getUId(),delay,all);
            if (!res.equals("success")) {
                return WrapMapper.error(res);
            }
        } catch (Exception e){
            return WrapMapper.error(e.getMessage());
        }
        return WrapMapper.ok("success");
    }

    @PostMapping(value = "/takeOrder")
    @ApiOperation(httpMethod = "POST",value = "服务商接单")
    public Wrapper<String> Emergency(@ApiParam(name = "takeOrder",value = "服务商接单") @RequestBody MdmcUpdateTaskDto updateTaskDto){
        try {
            String res = taskService.updateTask(updateTaskDto);
            taskService.deleteFaciMap(updateTaskDto);
            if (!res.equals("success")) {
                return WrapMapper.error(res);
            }
        } catch (Exception e){
            return WrapMapper.error(e.getMessage());
        }
        return WrapMapper.ok("success");
    }

    @PostMapping(value = "/submitApproval")
    @ApiOperation(httpMethod = "POST", value = "提交审核申请")
    public Wrapper<String> approve(@ApiParam(name = "approve task", value = "审核维修申请") @RequestBody MdmcApproveInfoDto approveInfoDto) {

        try {
            String res=taskService.submitApproval(approveInfoDto);
           if (!res.equals("success")){
               return WrapMapper.error(res);
           }
            }catch (Exception e){
            return WrapMapper.error(e.getMessage());
        }


        return WrapMapper.ok("success");
    }

    @PostMapping(value = "/faci_reject")
    @ApiOperation(httpMethod = "POST", value = "服务商拒单")
    public Wrapper<String> approve(@ApiParam(name = "taskId", value = "工单id") @RequestBody MdmcUpdateTaskDto mdmcUpdateTaskDto) {
        try {
            taskService.updateTask(mdmcUpdateTaskDto);
            List<Long> list=new ArrayList<>();
            //调用接口获取所有服务商id
            String res=taskService.faciTransfer(mdmcUpdateTaskDto.getOrderId(),list);
            if (!res.equals("success")){
                return WrapMapper.error(res);
            }
        }catch (Exception e){
            return WrapMapper.error(e.getMessage());
        }


        return WrapMapper.ok("success");
    }

}
