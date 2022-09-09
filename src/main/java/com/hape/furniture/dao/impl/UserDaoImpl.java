package com.hape.furniture.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hape.furniture.dao.UserDao;
import com.hape.furniture.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private User user;

    /**
     * 插入
     * @param user
     * @return
     */
    @Override
    public int insert(User user) {
        boolean flag = user.insert();
        if(!flag){
            return 0;
        }
        return user.getUid();
    }

    /**
     * 根据用户名和密码查询
     * @param uname
     * @param passwd
     * @return
     */
    @Override
    public User findOneByUserNameAndPasswd(String uname,String passwd) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("uname",uname).eq("passwd",passwd);
        return user.selectOne(wrapper);
    }

    /**
     * 根据id查询
     * @param uid
     * @return
     */
    @Override
    public User findByUid(int uid) {
        user.setUid(uid);
        return user.selectById();
    }

    @Override
    public User findByUname(String uname) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("uname",uname);
        return user.selectOne(wrapper);
    }

    @Override
    public List<User> findAll() {
        return user.selectAll();
    }

    /**
     * 根据居住地分组查询
     * @return
     */
    @Override
    public List<User> findGroupByCity() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.groupBy("city");
        return user.selectList(wrapper);
    }

    /**
     * 根据性别查询数量
     * @param gender
     * @return
     */
    @Override
    public int findCountByGender(int gender) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("gender",gender);
        return user.selectCount(wrapper);
    }

    /**
     * 根据居住地查询数量
     * @param city
     * @return
     */
    @Override
    public int findCountByCity(String city) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("city",city);
        return user.selectCount(wrapper);
    }

    /**
     * 查询出生日期小于等于的
     * @param birthday
     * @return
     */
    @Override
    public int findCountLessBirthday(String birthday) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.ge("birthday",birthday);
        return user.selectCount(wrapper);
    }

    /**
     * 查询出生日期大于的
     * @param birthday
     * @return
     */
    @Override
    public int findCountMoreBirthday(String birthday) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.lt("birthday",birthday);
        return user.selectCount(wrapper);
    }

    /**
     * 小于等于,大于
     * @param less
     * @param more
     * @return
     */
    @Override
    public int findCountLessAndMoreBirthday(String less, String more) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.lt("birthday",more).ge("birthday",less);
        return user.selectCount(wrapper);
    }


    @Override
    public boolean update(User user) {
        return user.updateById();
    }
}
