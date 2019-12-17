package com.ananops.provider.web.frontend;

import com.ananops.provider.model.domain.MdmcReview;
import com.ananops.provider.model.dto.*;
import com.ananops.provider.service.MdmcDeviceService;
import com.ananops.provider.service.MdmcTaskService;
import com.ananops.provider.utils.WrapMapper;
import com.ananops.provider.utils.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping(value = "ananops/api/v1/task",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "WEB - MdmcAddDevice",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MdmcTaskHandler {
    @Resource
    MdmcTaskService taskService;
    @Resource
    MdmcDeviceService deviceService;

    @PostMapping(value = "/submit/{taskId}")
    @ApiOperation(httpMethod = "POST",value = "提交工单")
    public Wrapper<String> submit(@ApiParam(name = "orderDto",value = "工单") @RequestBody MdmcOrderDto orderDto){
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

    @PostMapping(value = "/submitResult")
    @ApiOperation(httpMethod = "POST",value = "提交备件方案和结果")
    public Wrapper<String> submitResult(@ApiParam(name = "Device and Result",value = "维修方案和结果") @RequestBody MdmcResultDto resultDto){
        try {
            String res=taskService.submitResult(resultDto);
            if (!res.equals("success")){
                return WrapMapper.error(res);
            }
        }catch (Exception e){
            return WrapMapper.error(e.getMessage());
        }


        return WrapMapper.ok("success");
    }

    @PostMapping(value = "/submitReview")
    @ApiOperation(httpMethod = "POST",value = "提交评价")
    public Wrapper<String> submitReview(@ApiParam(name = "review",value = "评价表") @RequestBody MdmcReview review){
        try {
            String res=taskService.submitReview(review);
            if (!res.equals("success")){
                return WrapMapper.error(res);
            }
        }catch (Exception e){
            return WrapMapper.error(e.getMessage());
        }


        return WrapMapper.ok("success");
    }


    @PostMapping(value = "/checkTask")
    @ApiOperation(httpMethod = "POST",value = "审核工单")
    public Wrapper<String> checkTask(@ApiParam(name = "checkDto",value = "审核dto") @RequestBody MdmcCheckDto checkDto){
        try {
            String res=taskService.checkTask(checkDto);
            if (!res.equals("success")){
                return WrapMapper.error(res);
            }
        }catch (Exception e){
            return WrapMapper.error(e.getMessage());
        }


        return WrapMapper.ok("success");
    }

    @PostMapping(value = "/confirmTask")
    @ApiOperation(httpMethod = "POST",value = "确认工单")
    public Wrapper<String> confirmTask(@ApiParam(name = "checkDto",value = "确认dto") @RequestBody MdmcCheckDto checkDto){
        try {
            String res=taskService.confirmTask(checkDto);
            if (!res.equals("success")){
                return WrapMapper.error(res);
            }
        }catch (Exception e){
            return WrapMapper.error(e.getMessage());
        }


        return WrapMapper.ok("success");
    }

    @PostMapping(value = "/AcceptTask")
    @ApiOperation(httpMethod = "POST",value = "接单")
    public Wrapper<String> acceptTask(@ApiParam(name = "checkDto",value = "接单dto") @RequestBody MdmcCheckDto checkDto){
        try {
            String res=taskService.acceptTask(checkDto);
            if (!res.equals("success")){
                return WrapMapper.error(res);
            }
        }catch (Exception e){
            return WrapMapper.error(e.getMessage());
        }


        return WrapMapper.ok("success");
    }


}
