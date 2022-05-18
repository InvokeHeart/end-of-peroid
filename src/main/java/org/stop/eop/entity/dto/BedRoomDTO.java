package org.stop.eop.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BedRoomDTO {

    @NotNull(message = "楼栋不能为空")
    private Integer build;

    @NotNull(message = "楼层不能为空")
    private Integer floor;

    @NotNull(message = "房间号不能为空")
    private Integer room;
}
