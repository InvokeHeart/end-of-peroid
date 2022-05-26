package org.stop.eop.entity.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.stop.eop.entity.po.BasicStudent;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    private String bedRoomId;
    private Integer roomNo;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<BasicStudent> students;
}
