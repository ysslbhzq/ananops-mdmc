package com.ananops.provider.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@ApiModel
public class MdmcPrincipleTaskDto implements Serializable {
    @ApiModelProperty("报修人ID")
    private Long uId;

    @ApiModelProperty("维修任务名称")
    private String title;

    @ApiModelProperty("审核人ID")
    private Long principalId;

    @ApiModelProperty("项目ID")
    private Long projectId;

    @ApiModelProperty("服务商ID")
    private Long facilitatorId;

    @ApiModelProperty("总花费")
    private BigDecimal totalCost;

    @ApiModelProperty("支付方式")
    private Integer payMode;

    @ApiModelProperty("任务子项")
    private List<MdmcTaskItemDto> taskItems;
}
