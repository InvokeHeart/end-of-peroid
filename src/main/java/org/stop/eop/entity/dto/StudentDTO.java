package org.stop.eop.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@ApiModel
public class StudentDTO {

    @ApiModelProperty(value = "学号")
    @NotEmpty(message = "学号不能为空")
    private String studentId;

    @ApiModelProperty(value = "学生姓名")
    @NotEmpty(message = "学生姓名不能为空")
    private String name;

    @ApiModelProperty(value = "寝室id")
    private String bedRoomId;


}
