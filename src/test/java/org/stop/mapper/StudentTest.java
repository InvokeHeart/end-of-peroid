package org.stop.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.stop.eop.mapper.StudentMapper;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class StudentTest {

    @Resource
    private StudentMapper mapper;

    @Test
    public void testFindByName(){
        mapper.getRoomAndStusByStuName("周").forEach(System.out::println);
        System.out.println(mapper.getRoomAndStusByStuName("n").size());

    }
}
