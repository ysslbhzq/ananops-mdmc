package com.ananops.provider.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/*生成工单界面*/
@Data
@ApiModel
public class MdmcAddTaskItemDto implements Serializable {

    private static final long serialVersionUID = 77608959690597491L;

    @ApiModelProperty("设备名称")
    private String deviceName;

    @ApiModelProperty("设备编号")
    private Long deviceNo;

//    @ApiModelProperty("设备型号")
//    private String deviceModel;
//
//    @ApiModelProperty("设备类型")
//    private String deviceType;

    @ApiModelProperty("报修人")
    private String creator;

    @ApiModelProperty("创建时间")
    private Date created_time;

    @ApiModelProperty("故障等级")
    private Integer level;

    @ApiModelProperty("故障描述")
    private String description;
}
