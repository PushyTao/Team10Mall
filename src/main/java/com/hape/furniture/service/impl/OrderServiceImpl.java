package com.hape.furniture.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hape.furniture.dao.*;
import com.hape.furniture.mapper.FurnitureMapper;
import com.hape.furniture.mapper.ImageMapper;
import com.hape.furniture.mapper.OrderMapper;
import com.hape.furniture.pojo.*;
import com.hape.furniture.service.FurnitureService;
import com.hape.furniture.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.*;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private Order order;
    @Value("${pageSize.order}")
    private int pageSize;
    @Autowired
    private FoRecordDao foRecordDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ImageMapper imageMapper;
    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private FurnitureMapper furnitureMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private User user;
    @Autowired
    private OrderChart orderChart;
    @Autowired
    private FurnitureDao furnitureDao;
    @Autowired
    private org.springframework.beans.factory.BeanFactory beanFactory;

    /**
     * 添加订单
     * @param order
     * @return
     */
    @Override
    public int add(Order order) {
        order.setONum(UUID.randomUUID().toString().replace("-",""));
        return orderDao.insert(order);
    }

    /**
     * 用户查询自己所有进行的订单
     * @param currentPage
     * @param uid
     * @return
     */
    public IPage<Order> findAllByUid(int currentPage,int uid){
        List<Order> orders = orderMapper.findAllByUid(uid);
        return setPage(uid,currentPage,orders);
    }

    /**
     * 获取当前用户所有进行的订单
     * @param uid
     * @param currentPage
     * @return
     */
    @Override
    public IPage<Order> findAllIngByUid(int uid, int currentPage) {
        IPage<Order> page = orderDao.findAllByUid(uid, pageSize, currentPage);
        List<Order> orders = page.getRecords();
        orders = setFurnitureInOrders(uid,orders);
        page.setRecords(orders);
        return page;
    }

    /**
     * 用户获取自己所有完成的订单
     * @param uid
     * @param currentPage
     * @return
     */
    public IPage<Order> findAllDelByUid(int uid,int currentPage){
        List<Order> orders = orderMapper.findAllDelByUid(uid);
        return setPage(uid,currentPage,orders);
    }

    /**
     * 管理员查询所有进行的订单
     * @param currentPage
     * @return
     */
    @Override
    public IPage<Order> findAll(int currentPage) {
        IPage<Order> page = orderDao.findAll(pageSize, currentPage);
        List<Order> orders = page.getRecords();
        orders = setFurnitureInOrders(0, orders);
        page.setRecords(orders);
        return page;
    }

    /**
     * 管理员查询所有删除的订单
     * @return
     */
    @Override
    public IPage<Order> findDel(int currentPage) {
        List<Order> list = orderMapper.findAllDel();
        return setPage(0,currentPage,list);
    }

    /**
     * 查询下单量
     * @return
     */
    @Override
    public OrderChart findOrderChart() {
        List<Order> confirm = orderMapper.findAllDel();
        List<Chart> chartList = new ArrayList<>();
        for (Order o : confirm) {
            List<FoRecord> records = foRecordDao.findByOid(o.getOid());
            for (FoRecord record : records) {
                Category category = categoryDao.findByCid(furnitureDao.findByFid(record.getFid()).getCid());
                int value = record.getCount();
                String name = category.getCname();
                int index = -1;
                for (int i = 0;i<chartList.size();i++) {
                    if(name.equals(chartList.get(i).getName())){
                        value = chartList.get(i).getValue()+value;
                        index = i;
                        break;
                    }
                }
                Chart chart = beanFactory.getBean(Chart.class);
                chart.setValue(value);chart.setName(name);
                if(index!=-1){
                    chartList.set(index,chart);
                }else {
                    chartList.add(chart);
                }
            }
        }
        orderChart.setOrder(chartList);
        return orderChart;
    }

    private List<Order> setFurnitureInOrders(int uid,List<Order> orders){
        List<Furniture> furnitures = new ArrayList<>();
        for (Order o : orders) {
            List<FoRecord> records = foRecordDao.findByOid(o.getOid());
            for (FoRecord record : records) {
                //封装家具
                Furniture furniture = furnitureMapper.findByFidAndDel(record.getFid());
                Category category = categoryDao.findByCid(furniture.getCid());
                Image image = imageMapper.findByFid(furniture.getFid());
                furniture.setImage(image);
                furniture.setCategory(category);
                furniture.setCount(record.getCount());
                furniture.setTotal(record.getTotal());
                furnitures.add(furniture);
            }
            //封装user
            if(uid==0){
                user = userDao.findByUid(o.getUid());
            }else {
                user = userDao.findByUid(uid);
            }
            user.setBirthday(null);
            user.setGender(null);
            user.setPasswd(null);
            user.setRole(null);
            user.setAddress(null);
            user.setCity(null);
            user.setPhoneNum(0);
            o.setUser(user);
            o.setFurniture(furnitures);
        }
        return orders;
    }

    private IPage<Order> setPage(int uid,int currentPage,List<Order> orders){
        orders = setFurnitureInOrders(uid,orders);
        List<Order> list = new ArrayList<>();
        int start = (currentPage-1)*pageSize;
        for (int i = 0; i < orders.size(); i++) {
            if(i>=start&&i<=start+pageSize-1){
                list.add(orders.get(i));
            }
            if(i>start+pageSize-1){
                break;
            }
        }
        Page<Order> page = new Page<>();
        page.setRecords(list);
        page.setCurrent(currentPage);
        page.setSize(pageSize);
        page.setTotal(orders.size());
        page.setPages((long) Math.ceil((double) orders.size()/(double) pageSize));
        return page;
    }

    /**
     * 批量删除
     * @param oids
     * @return
     */
    @Override
    public boolean del(int[] oids) {
        for (int oid : oids) {
            boolean flag = orderDao.del(oid);
            if(!flag){
                //回滚
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return false;
            }
        }
        return true;
    }

    /**
     * 发货
     * @param oid
     * @return
     */
    @Override
    public boolean deliver(int oid) {
        order.setOid(oid);
        order.setDeliverStatus(1);
        order.setRecStatus(0);
        boolean flag = orderDao.update(order);
        if(!flag){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        return true;
    }

    /**
     * 确认收货
     * @param oid
     * @return
     */
    @Override
    public boolean confirm(int oid) {
        order.setOid(oid);
        order.setDeliverStatus(1);
        order.setRecStatus(1);
        boolean flag = orderDao.update(order);
        if(!flag){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        flag = orderDao.del(order.getOid());
        if(!flag){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        return true;
    }
}
