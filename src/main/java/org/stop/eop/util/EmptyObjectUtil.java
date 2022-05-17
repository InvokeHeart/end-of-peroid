package org.stop.eop.util;

import org.stop.eop.entity.po.BedRoom;
import org.stop.eop.entity.po.Student;

public class EmptyObjectUtil {
    private static final BedRoom emptyRoom = new BedRoom();
    private static final Student emptyStudent = new Student();


    public static BedRoom getEmptyRoom() {
        return emptyRoom;
    }


    public static Student getEmptyStudent() {
        return emptyStudent;
    }
}
