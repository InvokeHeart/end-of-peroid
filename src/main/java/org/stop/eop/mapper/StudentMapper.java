package org.stop.eop.mapper;

import org.apache.ibatis.annotations.Param;
import org.stop.eop.entity.po.Student;

import java.util.List;

public interface StudentMapper {
    //所有学生
    List<Student> allStudents();

    //添加学生 如果存在重复主键则更新 不存在重复则插入
    Integer addStudent(Student student);

    // 批量添加学生
    Integer addStudentBatch(@Param("students") List<Student> students);

    Integer deleteByStudentId(String studentId);

    Student getByStudentId(String studentId);

    List<Student> getByBedRoomId(String bedRoomId);


    Integer updateStudentById(Student student);
}
