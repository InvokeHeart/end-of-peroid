package org.stop.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.stop.eop.mapper.StudentMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class StudentMapperTest {
    @Autowired
    private StudentMapper studentMapper;

    @Test
    public void testGetRoomAndStudentsByStuId() {
        studentMapper.getRoomAndStudentsByStuId("20213752").forEach(System.out::println);
    }

    @Test
    public void testGetRoomAndStusByStuName() {
        studentMapper.getRoomAndStusByStuName("周").forEach(System.out::println);
    }

}
