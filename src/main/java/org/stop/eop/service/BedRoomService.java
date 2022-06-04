package org.stop.eop.service;

import org.stop.eop.entity.dto.BedRoomDTO;
import org.stop.eop.entity.dto.WholeSchool;
import org.stop.eop.entity.po.BedRoom;
import org.stop.eop.entity.po.BuildCount;
import org.stop.eop.entity.po.RoomStudent;
import org.stop.eop.entity.po.Student;

import java.util.List;

public interface BedRoomService {

    BedRoom findBedRoomById(String bedRoomId);

    WholeSchool allBuildingsInfo();


    String addBedRoom(BedRoomDTO bedRoomDTO);

    List<Integer> buildings();

    List<BuildCount> buildAndStuCount();

    List<Integer> floors(Integer buildingNumber);


    List<Integer> rooms(Integer buildingNumber, Integer floorNumber);


    List<Student> roomStudents(Integer bid, Integer fid, Integer room);

    //输入楼栋/楼层得到 寝室及学生
    List<RoomStudent> roomAndStudents(Integer bid, Integer fid);
}
