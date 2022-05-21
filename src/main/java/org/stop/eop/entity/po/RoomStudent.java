package org.stop.eop.entity.po;

import lombok.Data;

import java.util.List;

@Data
public class RoomStudent {
    private String bedRoomId;
    private Integer build;
    private Integer floor;
    private Integer room;
    List<Student> students;
}
