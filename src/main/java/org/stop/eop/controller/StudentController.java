package org.stop.eop.controller;

import org.springframework.web.bind.annotation.*;
import org.stop.eop.entity.dto.StudentDTO;
import org.stop.eop.entity.po.Student;
import org.stop.eop.entity.resp.Result;
import org.stop.eop.service.StudentService;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("student")
@SuppressWarnings("rawtypes")
public class StudentController {

    private final StudentService studentService;


    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    //根据学号查找学生
    @GetMapping("{id}")
    Result getStudent(@PathVariable("id") String id) {
        Student student = studentService.findByStudentId(id);
        return Objects.nonNull(student) ? Result.ok(student) : Result.error("未找到指定学生");
    }

    //添加学生 或 添加学生时就为学生分配宿舍
    @PostMapping
    Result addStudent(@RequestBody @Valid StudentDTO studentDTO) {
        return Result.ok(studentService.addStudent(studentDTO));
    }

    //更新学生 姓名/宿舍
    @PutMapping
    Result updateStudent(@RequestBody @Valid StudentDTO studentDTO) {
        return Result.ok(studentService.updateStudent(studentDTO));
    }

    @DeleteMapping("{stuId}")
    Result deleteStudent(@PathVariable("stuId") String studentId) {
        return Result.ok(studentService.deleteStudent(studentId));
    }
}
