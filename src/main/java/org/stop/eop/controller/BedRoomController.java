package org.stop.eop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.stop.eop.entity.dto.BedRoomDTO;
import org.stop.eop.entity.resp.Result;
import org.stop.eop.service.BedRoomService;

import javax.validation.Valid;

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
        log.info("{}", bedRoomDTO);
        return Result.ok(bedRoomService.addBedRoom(bedRoomDTO));
    }

    @GetMapping("build")
    Result buildings() {
        return Result.ok("所有楼栋查询成功", bedRoomService.buildings());
    }

    @GetMapping("{bid}")
    Result buildingFloors(@PathVariable("bid") Integer bid) {
        return Result.ok("楼栋为" + bid + "的楼层", bedRoomService.floors(bid));
    }

    @GetMapping("{bid}/{fid}")
    Result buildingFloorAndRooms(@PathVariable("bid") Integer bid, @PathVariable("fid") Integer fid) {
        return Result.ok("楼栋" + bid + " 楼层" + fid, bedRoomService.rooms(bid, fid));
    }


}
