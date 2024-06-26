package com.itheima.mp.service;

import com.itheima.mp.domain.po.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class IUserServiceTest {

    @Autowired
    private  IUserService userService;


    @Test
    void testSaveUser() {
        User user = new User();
//        user.setId(5L);
        user.setUsername("kainnahei");
        user.setPassword("123");
        user.setPhone("18688990011");
        user.setBalance(200);
        user.setInfo("{\"age\": 24, \"intro\": \"英文老师\", \"gender\": \"female\"}");
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userService.save(user);
    }
    @Test
    void testQuery(){
        List<User> userList = userService.listByIds(List.of(1L, 2L, 3L));
        userList.forEach(System.out::println);
    }
}