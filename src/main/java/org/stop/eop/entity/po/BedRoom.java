package org.stop.eop.entity.po;

import cn.hutool.core.util.IdUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BedRoom {
    private String bedRoomId;
    private Integer build;
    private Integer floor;
    private Integer room;

    public BedRoom(Integer build, Integer floor, Integer room) {
        this.bedRoomId = IdUtil.getSnowflakeNextIdStr();
        this.build = build;
        this.floor = floor;
        this.room = room;
    }
}
