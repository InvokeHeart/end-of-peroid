package org.stop.eop.service;

import org.stop.eop.entity.dto.BedRoomDTO;
import org.stop.eop.entity.dto.WholeSchool;
import org.stop.eop.entity.po.BedRoom;

import java.util.List;

public interface BedRoomService {


    BedRoom findBedRoomById(String bedRoomId);

    WholeSchool allBuildingsInfo();


    String addBedRoom(BedRoomDTO bedRoomDTO);

    public List<Integer> buildings();

    public List<Integer> floors(Integer buildingNumber);

    public List<Integer> rooms(Integer buildingNumber, Integer floorNumber);
}
