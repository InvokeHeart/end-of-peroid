package org.stop.eop.entity.po;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.ToString;
import org.stop.eop.entity.dto.StudentDTO;


public class Student extends BasicStudent {

    //宿舍
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String bedRoomId;


    public Student(String studentId, String studentName, String bedRoomId) {
        super(studentId, studentName);
        this.bedRoomId = bedRoomId;
    }

    public Student(String bedRoomId) {
        this.bedRoomId = bedRoomId;
    }

    //将dto转换为数据库pojo
    public Student(StudentDTO studentDTO) {
        super(studentDTO.getStudentId(), studentDTO.getName());
        this.bedRoomId = studentDTO.getBedRoomId();
    }

    public Student() {
    }

    public String getBedRoomId() {
        return bedRoomId;
    }

    public void setBedRoomId(String bedRoomId) {
        this.bedRoomId = bedRoomId;
    }


    @Override
    public String toString() {
        return "Student{" +
                "bedRoomId='" + bedRoomId + '\'' +
                "} " + super.toString();
    }
}
