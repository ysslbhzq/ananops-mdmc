package com.ananops.provider.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/*维修工获取显示不同状态的工单个数列表*/
@Data
@ApiModel
public class MdmcStatusRepairsDto implements Serializable {

    @ApiModelProperty("当前状态下工单个数")
    private Integer count;
}
