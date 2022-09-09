package com.hape.furniture.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hape.furniture.pojo.Order;
import com.hape.furniture.pojo.OrderChart;
import org.springframework.stereotype.Service;


@Service
public interface OrderService {
    int add(Order order);
    IPage<Order> findAllIngByUid(int uid,int currentPage);
    IPage<Order> findAllDelByUid(int uid,int currentPage);
    IPage<Order> findAllByUid(int currentPage,int uid);
    IPage<Order> findAll(int currentPage);
    IPage<Order> findDel(int currentPage);
    OrderChart findOrderChart();
    boolean del(int[] oids);
    boolean deliver(int oid);
    boolean confirm(int oid);
}
