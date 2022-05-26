package org.stop.eop.service;


import org.stop.eop.entity.dto.StudentDTO;
import org.stop.eop.entity.po.RoomStudent;
import org.stop.eop.entity.po.Student;

import java.util.List;

public interface StudentService {
    String addStudent(StudentDTO student);

    String deleteStudent(String stuId);

    List<Student> findByBedRoomId(String roomId);


    Student findByStudentId(String stuId);

    String updateStudent(StudentDTO studentDTO);

    List<RoomStudent> findRoomAndStudentsByStuId(String stuId);


    List<RoomStudent> findRoomAndStusByStuName(String name);
}
