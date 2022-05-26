package org.stop.eop.service.impl;

import cn.hutool.core.util.IdUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stop.eop.entity.dto.*;
import org.stop.eop.entity.po.*;
import org.stop.eop.mapper.BedRoomMapper;
import org.stop.eop.service.BedRoomService;
import org.stop.eop.service.StudentService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * BedRoomService
 * <p>some methods about bedRoom CRUD</p>
 *
 * @author Stop mail-> authabc@163.com
 */
@Service
public class BedRoomServiceImpl implements BedRoomService {

    private final BedRoomMapper bedRoomMapper;

    @Autowired
    private StudentService studentService;


    public BedRoomServiceImpl(BedRoomMapper bedRoomMapper) {
        this.bedRoomMapper = bedRoomMapper;
    }

    //根据room id查找room相关信息
    @Override
    public BedRoom findBedRoomById(String bedRoomId) {
        return bedRoomMapper.getByBedRoomId(bedRoomId);
    }

    /**
     * populate {@code Room} and {@code Floor} and {@code Building} to {@code WholeSchool}
     *
     * @return all {@code Building}s are wrapper as entity
     * @see WholeSchool
     * @see Building
     * @see Floor
     * @see Room
     */
    @Override
    public WholeSchool allBuildingsInfo() {
        //楼栋号去重
        Stream<Integer> distinctBuild = bedRoomMapper.allRooms().stream().map(BedRoom::getBuild).sorted(Integer::compareTo).distinct();

        List<Building> buildingList = distinctBuild.map(buildNo -> {
            Building building = new Building();
            //楼栋号
            building.setBuild(buildNo);
            //找到楼栋对应的楼层号列表
            List<Floor> floorList = bedRoomMapper.getByRoomMulti(null, buildNo, 0, 0).
                    stream().map(BedRoom::getFloor).distinct().sorted(Integer::compareTo).map(floorNo -> {
                        //每一个房间号
                        List<Room> rooms = bedRoomMapper.getByRoomMulti(null, buildNo, floorNo, 0).stream().map(BedRoom::getRoom).map(roomNo -> {
                            //楼栋-楼层-房间号 对应的 该行记录的id
                            List<String> bedRoomId = bedRoomMapper.getByRoomMulti(null, buildNo, floorNo, roomNo).stream().map(BedRoom::getBedRoomId).sorted(String::compareTo).collect(Collectors.toList());
                            //只有唯一一条记录编号
                            assert bedRoomId.size() == 1;
                            String bid = bedRoomId.get(0);
                            //该寝室的所有学生
                            List<Student> sameBedRoomStudents = studentService.findByBedRoomId(bid);

                            //transfer--> collect essential studentInfo
                            List<BasicStudent> basicStudents = sameBedRoomStudents.stream().map(student -> new BasicStudent(student.getStudentId(), student.getStudentName())).collect(Collectors.toList());

                            //封装寝室号+该寝室下住宿的学生
                            return new Room(bid,roomNo, basicStudents);
                        }).collect(Collectors.toList());//collect as list return
                        // 找到该room下所住宿的学生进行封装
                        return new Floor(floorNo, rooms);
                    }).collect(Collectors.toList());
            //
            building.setFloors(floorList);
            return building;
            //每一栋收集为整个wholeSchool
        }).collect(Collectors.toList());
        return new WholeSchool(buildingList);
    }

    /**
     * @param bedRoomDTO the given transfer {@code BedRoomDTO} entity from frontEnd
     * @return the response after add to database
     * @see IdUtil#getSnowflakeNextIdStr()
     */
    @Override
    public String addBedRoom(BedRoomDTO bedRoomDTO) {
        List<BedRoom> roomMulti = bedRoomMapper.getByRoomMulti(null, bedRoomDTO.getBuild(), bedRoomDTO.getFloor(), bedRoomDTO.getRoom());
        //检查数据库是否存在
        if (Objects.nonNull(roomMulti) && roomMulti.size() > 0) {
            return "添加失败,楼栋-楼层-寝室号已存在";
        }
        BedRoom bedRoom = new BedRoom();
        BeanUtils.copyProperties(bedRoomDTO, bedRoom);
        bedRoom.setBedRoomId(IdUtil.getSnowflakeNextIdStr());
        return bedRoomMapper.addBedRoom(bedRoom) > 0 ? "添加成功" : "添加失败";
    }

    /**
     * @return all buildings collection (unique)
     */
    public List<Integer> buildings() {
        //sure, I am lazy to write new methods to fetch unique building numbers from database
        return bedRoomMapper.allRooms().stream().map(BedRoom::getBuild).distinct().collect(Collectors.toList());
    }


    /**
     * @param buildingNumber the designated building number
     * @return all floors in the designated building
     */
    public List<Integer> floors(Integer buildingNumber) {
        return bedRoomMapper.getByRoomMulti(null, buildingNumber, 0, 0).stream().map(BedRoom::getFloor).distinct().collect(Collectors.toList());
    }

    //楼栋及楼楼栋入住人员个数
    public List<BuildCount> buildAndStuCount() {
        return bedRoomMapper.buildStudentCount();
    }


    /**
     * @param buildingNumber the designated building number
     * @param floorNumber    the designated floor number
     * @return all rooms with the buildingNumber and floorNumber
     */
    public List<Integer> rooms(Integer buildingNumber, Integer floorNumber) {
        return bedRoomMapper.getByRoomMulti(null, buildingNumber, floorNumber, 0).stream().map(BedRoom::getRoom).distinct().collect(Collectors.toList());
    }

    @Override
    public List<Student> roomStudents(Integer bid, Integer fid, Integer room) {
        List<BedRoom> roomMulti = bedRoomMapper.getByRoomMulti(null, bid, fid, room);
        if (!roomMulti.isEmpty()) {
            String bedRoomId = roomMulti.get(0).getBedRoomId();
            return studentService.findByBedRoomId(bedRoomId);
        }
        return null;
    }

    @Override
    public List<RoomStudent> roomAndStudents(Integer bid, Integer fid) {
       return bedRoomMapper.getRoomAndStudentsByBuildAndFloor(bid,fid);
    }


}
