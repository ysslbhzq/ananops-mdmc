package com.ananops.provider.web.frontend;

import com.ananops.provider.model.dto.MdmcApproveInfoDto;
import com.ananops.provider.model.dto.MdmcOrderDto;
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


}
