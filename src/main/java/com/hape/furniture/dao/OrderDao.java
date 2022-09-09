package com.hape.furniture.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hape.furniture.pojo.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao {
    int insert(Order order);
    IPage<Order> findAllByUid(int uid,int pageSize,int currentPage);
    IPage<Order> findAll(int pageSize,int currentPage);
    List<Order> findConfirm();
    boolean del(int oid);
    boolean update(Order order);
}
