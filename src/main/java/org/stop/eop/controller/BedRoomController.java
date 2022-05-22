package org.stop.eop.controller;

import cn.hutool.core.lang.hash.Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.stop.eop.entity.dto.BedRoomDTO;
import org.stop.eop.entity.po.RoomStudent;
import org.stop.eop.entity.po.Student;
import org.stop.eop.entity.resp.Result;
import org.stop.eop.service.BedRoomService;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import java.net.NetworkInterface;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("bedroom")
@SuppressWarnings("rawtypes")
public class BedRoomController {
    private final BedRoomService bedRoomService;

    private static final Logger log = LoggerFactory.getLogger(BedRoomController.class);

    BedRoomController(BedRoomService bedRoomService) {
        this.bedRoomService = bedRoomService;
    }

    @GetMapping
    Result all() {
        return Result.ok("查询成功", bedRoomService.allBuildingsInfo());
    }

    @PostMapping
    Result addBedRoom(@RequestBody @Valid BedRoomDTO bedRoomDTO) {
        if (!String.valueOf(bedRoomDTO.getRoom()).startsWith(String.valueOf(bedRoomDTO.getFloor()))) {
            return Result.error("寝室号格式错误 如 楼层为2,房间号应为2xx格式");
        }
        log.info("{}", bedRoomDTO);
        return Result.ok(bedRoomService.addBedRoom(bedRoomDTO));
    }

    @GetMapping("build")
    Result buildings() {
        // return Result.ok("所有楼栋查询成功", bedRoomService.buildings());
        return Result.ok(bedRoomService.buildAndStuCount());
    }

    @GetMapping("{bid}")
    Result buildingFloors(@PathVariable("bid") @Valid  Integer bid) {
        return Result.ok("楼栋为" + bid + "的楼层", bedRoomService.floors(bid));

    }

    /*
    @GetMapping("{bid}/{fid}")
    Result buildingFloorAndRooms(@PathVariable("bid") Integer bid, @PathVariable("fid") Integer fid) {
        return Result.ok("楼栋" + bid + " 楼层" + fid, bedRoomService.rooms(bid, fid));
    }

     */
    //lht需要 返回楼层内的所有寝室以及对应的学生
    @GetMapping("{bid}/{fid}")
    Result buildingFloorAndRoomsv3(@PathVariable("bid") Integer bid, @PathVariable("fid") Integer fid) {
        List<RoomStudent> data = bedRoomService.roomAndStudents(bid, fid);
        if (Objects.nonNull(data) && !data.isEmpty()) {
            return Result.ok("楼栋" + bid + " 楼层" + fid, data);
        }
        return Result.error("未找到楼栋" + bid + " 楼层" + fid + "相关信息");
    }

    //指定 寝室内的学生
    @GetMapping("{bid}/{fid}/{room}")
    Result roomAndStudents(@PathVariable("bid") Integer bid, @PathVariable("fid") Integer fid, @PathVariable("room") Integer room) {
        List<Student> students = bedRoomService.roomStudents(bid, fid, room);
        return Objects.nonNull(students) && !students.isEmpty() ? Result.ok(students) : Result.error("未找到该寝室学生的相关信息");
    }
}
