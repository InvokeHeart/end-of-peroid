package org.stop.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.stop.eop.entity.dto.Building;
import org.stop.eop.entity.dto.Floor;
import org.stop.eop.entity.dto.Room;
import org.stop.eop.entity.dto.WholeSchool;
import org.stop.eop.entity.po.*;
import org.stop.eop.mapper.BedRoomMapper;
import org.stop.eop.mapper.StudentMapper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class BedRoomTest {
    @Autowired
    private BedRoomMapper roomMapper;


    @Autowired
    private StudentMapper studentMapper;


    @Test
    public void testUpdate() {
        Integer x = roomMapper.updateBedRoomById(new BedRoom("11", 2, 3, 3));
        System.out.println(x);
    }

    @Test
    public void testSelect() {
        roomMapper.getByRoomMulti("", 14, 0, 0).forEach(System.out::println);
    }

    @Test
    public void populateBuildingsToWholeSchool() throws JsonProcessingException {
        //去重楼栋号
        Stream<Integer> distinctBuild = roomMapper.allRooms().stream().map(BedRoom::getBuild).distinct();

        List<Building> buildingList = distinctBuild.map(buildNo -> {
            Building building = new Building();
            //楼栋号
            building.setBuild(buildNo);
            //找到楼栋对应的楼层号列表
            List<Floor> floorList = roomMapper.getByRoomMulti(null, buildNo, 0, 0).
                    stream().map(BedRoom::getFloor).distinct().map(floorNo -> {
                        //每一个房间号
                        List<Room> rooms = roomMapper.getByRoomMulti(null, buildNo, floorNo, 0).stream().map(BedRoom::getRoom).map(roomNo -> {
                            //楼栋-楼层-房间号 对应的 该行记录的id
                            List<String> bedRoomId = roomMapper.getByRoomMulti(null, buildNo, floorNo, roomNo).stream().map(BedRoom::getBedRoomId).collect(Collectors.toList());
                            //只有唯一一条记录编号
                            assert bedRoomId.size() == 1;
                            String bid = bedRoomId.get(0);
                            //该寝室的所有学生
                            List<Student> sameBedRoomStudents = studentMapper.getByBedRoomId(bid);

                            //transfer--> collect essential studentInfo
                            List<BasicStudent> basicStudents = sameBedRoomStudents.stream().map(student -> new BasicStudent(student.getStudentId(), student.getStudentName())).collect(Collectors.toList());

                            //封装寝室号+该寝室下住宿的学生
                            return new Room(roomNo, basicStudents);
                        }).collect(Collectors.toList());//collect as list return
                        // 找到该room下所住宿的学生进行封装
                        return new Floor(floorNo, rooms);
                    }).collect(Collectors.toList());
            //
            building.setFloors(floorList);
            return building;
            //每一栋收集为整个wholeSchool
        }).collect(Collectors.toList());

        WholeSchool wholeSchool = new WholeSchool(buildingList);
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(wholeSchool));
        // System.out.println(wholeSchool);

    }
    @Test
    public void testSelectByAssociation(){
        List<StudentBuilding> stuAndRoomsByStuId = studentMapper.getStuAndRoomsByStuId("2021");
        System.out.println(stuAndRoomsByStuId);
    }

    @Test
    public void testGetRoomAndStudentsByBuildAndFloor() throws JsonProcessingException {
        List<RoomStudent> studentsByBuildAndFloor = roomMapper.getRoomAndStudentsByBuildAndFloor(14, 2);
        studentsByBuildAndFloor.forEach(System.out::println);
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(studentsByBuildAndFloor));
    }

}
