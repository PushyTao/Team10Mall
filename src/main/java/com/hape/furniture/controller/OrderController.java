package com.hape.furniture.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hape.furniture.pojo.FoRecord;
import com.hape.furniture.pojo.Order;
import com.hape.furniture.pojo.OrderChart;
import com.hape.furniture.pojo.User;
import com.hape.furniture.service.FoRecordService;
import com.hape.furniture.service.OrderService;
//import com.sun.tools.corba.se.idl.constExpr.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private FoRecordService foRecordService;
    @Autowired
    private UserController userController;

    /**
     * 添加
     * @param
     * @return
     */
    @PostMapping("/add")
    public int add(Order order,HttpSession session){
        order.setUid(userController.getUserInfo(session).getUid());
        return orderService.add(order);
    }


    /**
     * 管理员获取所有进行的订单信息
     * @param currentPage
     * @return
     */
    @GetMapping("/findAll/{currentPage}")
    public IPage<Order> findAll(@PathVariable("currentPage") int currentPage){
        return orderService.findAll(currentPage);
    }

    /**
     * 管理员查询所有删除的订单
     * @return
     */
    @GetMapping("/findDel/{currentPage}")
    public IPage<Order> findDel(@PathVariable("currentPage") int currentPage){
        return orderService.findDel(currentPage);
    }

    /**
     * 用户查询自己所有的订单
     * @param session
     * @param currentPage
     * @return
     */
    @GetMapping("/findAll/user/{currentPage}")
    public IPage<Order> findAllByUid(HttpSession session, @PathVariable("currentPage") int currentPage){
        User user = (User) session.getAttribute("user");
        return orderService.findAllByUid(currentPage,user.getUid());
    }

    /**
     * 用户获取自己正在进行的订单
     * @param currentPage
     * @return
     */
    @GetMapping("/findIng/user/{currentPage}")
    public IPage<Order> findAllIngByUid(@PathVariable("currentPage") int currentPage, HttpSession session){
        User user = userController.getUserInfo(session);
        return orderService.findAllIngByUid(user.getUid(), currentPage);
    }

    /**
     * 查询当前用户已完成的订单
     * @param currentPage
     * @param session
     * @return
     */
    @GetMapping("/findDel/user/{currentPage}")
    public IPage<Order> findDelByUid(@PathVariable("currentPage")int currentPage,HttpSession session){
        User user = (User) session.getAttribute("user");
        return orderService.findAllDelByUid(user.getUid(),currentPage);
    }

    /**
     * 查询订单图表
     * @return
     */
    @GetMapping("/findChart")
    public OrderChart findChart(){
        return orderService.findOrderChart();
    }

    /**
     * 发货
     * @param oid
     * @return
     */
    @PutMapping("/deliver/{oid}")
    public boolean deliver(@PathVariable("oid")int oid){
        return orderService.deliver(oid);
    }

    @PutMapping("confirm/{oid}")
    public boolean confirm(@PathVariable("oid")int oid){
        return orderService.confirm(oid);
    }

    /**
     * 批量删除订单
     * @param oids
     * @return
     */
    @DeleteMapping("/del")
    public boolean del(int[] oids){
        return orderService.del(oids);
    }
}
