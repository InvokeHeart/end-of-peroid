package org.stop.eop.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Floor {
    private Integer floor;
    // private List<Integer> rooms;
    private List<Room> rooms;
}
