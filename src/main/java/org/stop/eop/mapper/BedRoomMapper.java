package org.stop.eop.mapper;

import org.apache.ibatis.annotations.Param;
import org.stop.eop.entity.po.BedRoom;
import org.stop.eop.entity.po.BuildCount;
import org.stop.eop.entity.po.RoomStudent;

import java.util.List;

public interface BedRoomMapper {
    Integer addBedRoom(BedRoom room);

    Integer deleteBedRoomById(String bedRoomId);

    BedRoom getByBedRoomId(String bedRoomId);


    //根据传入字段拼接
    //可以是一条也可以是多条记录
    List<BedRoom> getByRoomMulti(@Param("bedRoomId") String bedRoomId,@Param("build") Integer build, @Param("floor")Integer floor,@Param("room") Integer room);

    List<BuildCount> buildStudentCount();

    Integer updateBedRoomById(BedRoom bedRoom);

    List<BedRoom> allRooms();

    List<RoomStudent> getRoomAndStudentsByBuildAndFloor(@Param("bid") Integer bid, @Param("fid") Integer fid);
}
