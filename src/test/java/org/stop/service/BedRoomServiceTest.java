package org.stop.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.stop.eop.entity.dto.BedRoomDTO;
import org.stop.eop.service.BedRoomService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class BedRoomServiceTest {
    @Autowired
    private BedRoomService bedRoomService;

    @Test
    public void testBuildings() {
        bedRoomService.rooms(12,2).forEach(System.out::println);
    }
}
