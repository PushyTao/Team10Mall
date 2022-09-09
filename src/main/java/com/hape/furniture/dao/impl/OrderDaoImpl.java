package com.hape.furniture.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hape.furniture.dao.OrderDao;
import com.hape.furniture.mapper.OrderMapper;
import com.hape.furniture.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {
    @Autowired
    private Order order;
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public int insert(Order order) {
        boolean flag = order.insert();
        if(flag){
            return order.getOid();
        }
        return 0;
    }

    @Override
    public IPage findAllByUid(int uid,int pageSize,int currentPage) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("uid",uid).orderByDesc("o_time");
        Page<Order> page = new Page<>(currentPage, pageSize);
        return orderMapper.selectPage(page,wrapper);
    }

    @Override
    public IPage findAll(int pageSize,int currentPage) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("o_time");
        Page<Order> page = new Page<>(currentPage, pageSize);
        return orderMapper.selectPage(page,wrapper);
    }

    /**
     * 查询所有已确认的
     * @return
     */
    @Override
    public List<Order> findConfirm() {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("rec_status",1);
        return order.selectList(wrapper);
    }

    @Override
    public boolean del(int oid) {
        order.setOid(oid);
        return order.deleteById();
    }

    @Override
    public boolean update(Order order) {
        return order.updateById();
    }
}
