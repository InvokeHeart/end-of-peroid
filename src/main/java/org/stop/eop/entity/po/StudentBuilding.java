package org.stop.eop.entity.po;

public class StudentBuilding {
    // 学号
    private String studentId;
    // 学生姓名
    private String studentName;

    private BedRoom bedRoom;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public BedRoom getBedRoom() {
        return bedRoom;
    }

    public void setBedRoom(BedRoom bedRoom) {
        this.bedRoom = bedRoom;
    }


    @Override
    public String toString() {
        return "StudentBuilding{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", bedRoom=" + bedRoom +
                '}';
    }
}
