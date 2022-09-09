package com.hape.furniture.service.impl;

import com.hape.furniture.dao.UserDao;
import com.hape.furniture.pojo.Chart;
import com.hape.furniture.pojo.ReturnInfo;
import com.hape.furniture.pojo.User;
import com.hape.furniture.pojo.UserChart;
import com.hape.furniture.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private ReturnInfo returnInfo;
    @Autowired
    private UserChart userChart;
    @Autowired
    private org.springframework.beans.factory.BeanFactory beanFactory;

    /**
     * 登录
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        return userDao.findOneByUserNameAndPasswd(user.getUname(), user.getPasswd());
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    /**
     * 注册
     * @param user
     * @return
     */
    @Override
    public ReturnInfo register(User user) {
        returnInfo.setStatus(false);
        User selectUser = userDao.findOneByUserNameAndPasswd(user.getUname(), user.getPasswd());
        if(selectUser!=null){
            returnInfo.setMsg("您已经注册过了");
            return returnInfo;
        }
        int uid = userDao.insert(user);
        if(uid==0){
            returnInfo.setMsg("注册失败，请联系站长");
            return returnInfo;
        }
        returnInfo.setStatus(true);
        return returnInfo;
    }

    @Override
    public boolean checkUname(String uname) {
        User user = userDao.findByUname(uname);
        return user == null;
    }

    @Override
    public boolean checkUpdateUname(String uname,String currentUname) {
        User user = userDao.findByUname(uname);
        return user == null || user.getUname().equals(currentUname);
    }

    @Override
    public boolean update(User user) {
        return userDao.update(user);
    }

    /**
     * 查询用户图表信息
     * @return
     */
    @Override
    public UserChart queryUserChart() {
        Chart chart;
        //性别
        chart = beanFactory.getBean(Chart.class);
        List<Chart> gender = new ArrayList<>();
        int female = userDao.findCountByGender(0);
        int male = userDao.findCountByGender(1);
        chart.setName("男");chart.setValue(male);
        gender.add(chart);
        chart = beanFactory.getBean(Chart.class);
        chart.setName("女");chart.setValue(female);
        gender.add(chart);
        //居住地
        List<Chart> city = new ArrayList<>();
        List<User> groupByCity = userDao.findGroupByCity();
        for (User gc : groupByCity) {
            chart = beanFactory.getBean(Chart.class);
            int count = userDao.findCountByCity(gc.getCity());
            chart.setName(gc.getCity());chart.setValue(count);
            city.add(chart);
        }
        //年龄
        chart = beanFactory.getBean(Chart.class);
        List<Chart> birthday = new ArrayList<>();
        Calendar calendar;
        Date date;

        calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR,-18);
        date = new Date();
        date.setTime(calendar.getTimeInMillis());
        String age18 = new SimpleDateFormat("yyyy-MM-dd").format(date);

        calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR,-30);
        date = new Date();
        date.setTime(calendar.getTimeInMillis());
        String age30 = new SimpleDateFormat("yyyy-MM-dd").format(date);

        int count = userDao.findCountLessBirthday(age18);
        chart.setName("age<=18岁");chart.setValue(count);
        birthday.add(chart);
        chart = beanFactory.getBean(Chart.class);
        count = userDao.findCountLessAndMoreBirthday(age30,age18);
        chart.setName("18岁<age<=30岁");chart.setValue(count);
        birthday.add(chart);
        chart = beanFactory.getBean(Chart.class);
        count = userDao.findCountMoreBirthday(age30);
        chart.setName("age>30岁");chart.setValue(count);
        birthday.add(chart);

        userChart.setGender(gender);
        userChart.setCity(city);
        userChart.setBirthday(birthday);

        return userChart;
    }
}
