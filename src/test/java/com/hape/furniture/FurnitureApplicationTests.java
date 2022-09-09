package com.hape.furniture;


import com.hape.furniture.dao.UserDao;
import com.hape.furniture.service.OrderService;
import com.hape.furniture.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SpringBootTest
class FurnitureApplicationTests {
@Autowired
private OrderService orderService;

    @Test
    void test(){
        System.out.println(orderService.findOrderChart());
    }
}
