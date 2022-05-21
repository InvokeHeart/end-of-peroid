package org.stop.eop.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.stop.eop.entity.dto.StudentDTO;
import org.stop.eop.entity.po.BedRoom;
import org.stop.eop.entity.po.Student;
import org.stop.eop.entity.po.StudentBuilding;
import org.stop.eop.mapper.StudentMapper;
import org.stop.eop.service.BedRoomService;
import org.stop.eop.service.StudentService;

import java.util.List;
import java.util.Objects;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentMapper studentMapper;


    private final BedRoomService bedRoomService;


    StudentServiceImpl(StudentMapper studentMapper, BedRoomService bedRoomService) {
        this.studentMapper = studentMapper;
        this.bedRoomService = bedRoomService;
    }

    /**
     * 向学生表中新增记录
     * 如存在学生 抛出异常
     * 不存在clazz || bedroomId抛出异常
     *
     * @param studentDTO 学生相关信息实体
     * @return 新增记录条数
     */
    @Override
    public String addStudent(StudentDTO studentDTO) {
        String bedRoomId = studentDTO.getBedRoomId();

        Student studentById = studentMapper.getByStudentId(studentDTO.getStudentId());

        //学生存在.
        if (Objects.nonNull(studentById) && !StringUtils.hasText(bedRoomId)) {
            throw new RuntimeException("学号已存在 请勿重复添加，如需继续添加请检查后重试");
        }

        //查询到的寝室号如果不为空 则无需操作
        if (Objects.nonNull(studentById) && Objects.nonNull(studentById.getBedRoomId())) {
            throw new RuntimeException("该学生已存在且入住");
        }

        //dto不包含寝室编号 视为添加学生
        if (!StringUtils.hasText(bedRoomId)) {
            //学生不存在 正常添加
            Student student = new Student(studentDTO);
            return studentMapper.addStudent(student) > 0 ? "添加成功" : "添加失败";
        } else {
            //dto包含寝室编号的情况
            //check bedroomId if exists
            BedRoom bedRoomById = bedRoomService.findBedRoomById(bedRoomId);
            if (Objects.isNull(bedRoomById)) {
                throw new RuntimeException("寝室不存在，请检查");
            }
            //检查寝室人数
            checkRoomNumber(bedRoomById.getBedRoomId());

            //正常添加 更新学生关联的寝室编号
            Student student = new Student(studentDTO);
            return studentMapper.addStudent(student) > 0 ? "寝室分配成功" : "寝室分配失败";
        }

    }

    @Override
    public String deleteStudent(String stuId) {
        if (Objects.isNull(findByStudentId(stuId))) {
            return "学生不存在 无法删除";
        }
        return studentMapper.deleteByStudentId(stuId) > 0 ? "学生删除成功" : "学生删除失败";
    }

    @Override
    public List<Student> findByBedRoomId(String bedRoomId) {
        return studentMapper.getByBedRoomId(bedRoomId);

    }

    @Override
    public Student findByStudentId(String stuId) {
        return studentMapper.getByStudentId(stuId);
    }

    @Override
    public String updateStudent(StudentDTO studentDTO) {
        //提交时存在寝室号
        String bedRoomId = studentDTO.getBedRoomId();
        if (StringUtils.hasText(bedRoomId)) {
            //检查寝室号是否存在||人数已满
            BedRoom bedRoomById = bedRoomService.findBedRoomById(bedRoomId);
            if (Objects.nonNull(bedRoomById)) {
                //检查人数
                if (studentMapper.getByBedRoomId(bedRoomId).size() > 5) {
                    throw new RuntimeException("该寝室已满人，请更换寝室");
                } else {
                    return studentMapper.updateStudentById(new Student(studentDTO)) > 0 ? "更新成功" : "更新失败";
                }
            }
            throw new RuntimeException("寝室不存在，请检查");

        }
        return studentMapper.updateStudentById(new Student(studentDTO)) > 0 ? "更新成功" : "更新失败";
    }

    // depends on stuId find out corresponding living room
    public List<StudentBuilding> findStuAndRoomsByStuId(String stuId) {
        return studentMapper.getStuAndRoomsByStuId(stuId);
    }

    @Override
    public List<StudentBuilding> findByStudentName(String name) {
        return studentMapper.getStuAndRoomsByStuName(name);
    }

    private void checkRoomNumber(String bedRoomId) {
        //检查该寝室人数
        List<Student> currentRoomStudents = findByBedRoomId(bedRoomId);
        //每个寝室限制五个人
        if (currentRoomStudents.size() > 5) {
            throw new RuntimeException("该寝室已满，请更换寝室");
        }
    }
}
