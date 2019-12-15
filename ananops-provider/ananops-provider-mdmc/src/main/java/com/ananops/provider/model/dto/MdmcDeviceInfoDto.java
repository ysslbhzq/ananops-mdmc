package com.ananops.provider.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/*报修单设备信息*/
@Data
@ApiModel
public class MdmcDeviceInfoDto implements Serializable {
    private static final long serialVersionUID = 7760895967590597491L;

    @ApiModelProperty("工单编号")
    private Long orderId;

    @ApiModelProperty("设备名称")
    private String deviceName;

    @ApiModelProperty("设备编号")
    private Long deviceNo;

//    @ApiModelProperty("设备型号")
//    private String deviceModel;
//
//    @ApiModelProperty("设备类型")
//    private String deviceType;

//    @ApiModelProperty("设备所在部门")
//    private String deviceDepartment;

    @ApiModelProperty("设备纬度")
    private BigDecimal deviceLatitude;

    @ApiModelProperty("设备经度")
    private BigDecimal deviceLongtitude;
}
