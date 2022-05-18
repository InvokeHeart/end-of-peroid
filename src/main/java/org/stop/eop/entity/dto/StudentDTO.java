package org.stop.eop.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class StudentDTO {

    @NotEmpty(message = "学号不能为空")
    private String studentId;

    @NotEmpty(message = "学生姓名不能为空")
    private String name;

    private String bedRoomId;


}
