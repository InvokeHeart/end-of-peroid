package org.stop.eop.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class BedRoomDTO {

    @NotNull(message = "楼栋不能为空")
    @ApiModelProperty("楼栋")
    private Integer build;

    @NotNull(message = "楼层不能为空")
    @ApiModelProperty("楼层")
    private Integer floor;

    @NotNull(message = "房间号不能为空")
    @ApiModelProperty("寝室号")
    private Integer room;
}
