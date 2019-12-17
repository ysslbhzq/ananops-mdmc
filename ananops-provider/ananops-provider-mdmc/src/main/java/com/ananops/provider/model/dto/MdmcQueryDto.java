package com.ananops.provider.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel
public class MdmcQueryDto implements Serializable {

    @ApiModelProperty("报修人id")
    private Long user_id;

    @ApiModelProperty("维修工id")
    private Long maintainer_id;

    @ApiModelProperty("服务商id")
    private Long facilitator_id;

    @ApiModelProperty("甲方id")
    private Long principal_id;

    @ApiModelProperty("工单状态")
    private Integer status;

    @ApiModelProperty("紧急程度")
    private Integer level;
}
