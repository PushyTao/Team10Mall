package com.hape.furniture.dao;

import com.hape.furniture.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    int insert(User user);
    User findOneByUserNameAndPasswd(String uname,String passwd);
    User findByUid(int uid);
    User findByUname(String uname);
    List<User> findAll();
    List<User> findGroupByCity();
    int findCountByGender(int gender);
    int findCountByCity(String city);
    int findCountLessBirthday(String birthday);
    int findCountMoreBirthday(String birthday);
    int findCountLessAndMoreBirthday(String less,String more);
    boolean update(User user);
}
