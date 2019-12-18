package com.ananops.provider.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel
@Data
public class MdmcPageDto implements Serializable {



    @ApiModelProperty("页码")
    private Integer pageNum;

    @ApiModelProperty("访问dto")
    private MdmcQueryDto queryDto;
}
