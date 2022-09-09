package com.hape.furniture.service;

import com.hape.furniture.pojo.ReturnInfo;
import com.hape.furniture.pojo.User;
import com.hape.furniture.pojo.UserChart;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User login(User user);
    List<User> findAll();
    ReturnInfo register(User user);
    boolean checkUname(String uname);
    boolean checkUpdateUname(String uname,String currentUname);
    boolean update(User user);
    UserChart queryUserChart();
}
