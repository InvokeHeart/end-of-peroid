package org.stop.eop.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BedRoomDTO {

    @NotNull(message = "楼栋不能为空")
    @Min(message = "楼栋最少为1栋", value = 1L)
    @Max(message = "楼栋最多为20栋", value = 20L)
    private Integer build;

    @NotNull(message = "楼层不能为空")
    @Min(message = "楼层最少为1层", value = 1L)
    @Max(message = "楼层最多为7层", value = 7L)
    private Integer floor;

    @NotNull(message = "房间号不能为空")
    private Integer room;
}
