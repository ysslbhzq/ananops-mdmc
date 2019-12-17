package com.ananops.provider.model.dto;

import com.ananops.provider.model.enums.MdmcTaskStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel
public class MdmcTypeDto implements Serializable {
    @ApiModelProperty("工单编号")
    private Long task_id;

    @ApiModelProperty("评价表编号")
    private Long review_id;

    @ApiModelProperty("备件表编号")
    private Long device_id;

    @ApiModelProperty("设备名称枚举")
    private  MdmcTaskItemDeviceNameEnum[] deviceNameEnums;

    @ApiModelProperty("故障类型枚举")
    private MdmcTaskItemTroubleTypeEnum[] troubleTypeEnums;

    @ApiModelProperty("故障位置枚举")
    private MdmcTaskItemTroubleAddressEnum[] troubleAddressEnums;

    @ApiModelProperty("故障名称枚举")
    private  MdmcTaskItemTroubleNameEnum[] troubleNameEnums;

    @ApiModelProperty("状态枚举")
    private MdmcTaskStatusEnum[] statusEnums;

    @ApiModelProperty("紧急程度枚举")
    private MdmcTaskLevelEnum[] levelEnums;
//
//    @ApiModelProperty("故障类型枚举")
//    private MdmcTaskItemTroubleTypeEnum troubleTypeEnum;
//
//    @ApiModelProperty("故障位置")
//    private MdmcTaskItemTroubleAddressEnum troubleAddressEnum;
//
//    @ApiModelProperty("故障名称")
//    private MdmcTaskItemTroubleNameEnum troubleNameEnum;
//    @ApiModelProperty("设备名称枚举")

}
