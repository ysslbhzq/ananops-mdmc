package com.ananops.provider.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/*维修单列表*/
@Data
@ApiModel
public class MdmcRepairsTaskDto implements Serializable {

    @ApiModelProperty("工单编号")
    private Long orderId;

//    @ApiModelProperty("设备名称")
//    private String deviceName;
//
//    @ApiModelProperty("设备编号")
//    private Long deviceNo;
//
//    @ApiModelProperty("故障等级")
//    private Integer level;

    @ApiModelProperty("报修时间")
    private Date createTime;

    @ApiModelProperty("工单状态")
    private Integer status;


}
