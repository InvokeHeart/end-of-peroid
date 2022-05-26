package org.stop.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.util.pattern.PathPattern;
import org.stop.eop.entity.dto.WholeSchool;
import org.stop.eop.service.BedRoomService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class BedRoomServiceTest {
    @Autowired
    private BedRoomService service;
    @Test
    public void testAll() throws JsonProcessingException {
        WholeSchool x = service.allBuildingsInfo();
        System.out.println(x);
        System.out.println(new ObjectMapper().writeValueAsString(x));
    }

}
