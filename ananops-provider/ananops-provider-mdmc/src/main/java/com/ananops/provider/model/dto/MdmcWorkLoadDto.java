package com.ananops.provider.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/*维修单的工作量信息*/
@Data
@ApiModel
public class MdmcWorkLoadDto implements Serializable {

    @ApiModelProperty("报修人")
    private String creator;

    @ApiModelProperty("开始时间")
    private Date actual_start_time;

    @ApiModelProperty("结束时间")
    private Date actual_finish_time;

//    @ApiModelProperty("维修用时")
//    private String repairs_time;

//    @ApiModelProperty("维修内容")
//    private String repairs_content;
}
