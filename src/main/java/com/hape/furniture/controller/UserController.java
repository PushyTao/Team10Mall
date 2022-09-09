package com.hape.furniture.controller;

import com.hape.furniture.pojo.ReturnInfo;
import com.hape.furniture.pojo.User;
import com.hape.furniture.pojo.UserChart;
import com.hape.furniture.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ReturnInfo returnInfo;

    /**
     * 验证 验证码是否正确
     * @param session
     * @param code
     * @return
     */
    private boolean verifyCheckCode(HttpSession session,String code){
        String sysCode = (String) session.getAttribute("code");
        return sysCode.equalsIgnoreCase(code);
    }

    /**
     * 登录
     * @param user
     * @param code
     * @param request
     * @return
     */
    @PostMapping("/login")
    public ReturnInfo login(User user, String code , HttpServletRequest request){
        returnInfo.setStatus(false);
        HttpSession session = request.getSession();
        String sysCode = (String) session.getAttribute("code");
        if(!sysCode.equalsIgnoreCase(code)){
            returnInfo.setMsg("验证码错误");
            return returnInfo;
        }
        User selectUser = userService.login(user);
        if(selectUser==null){
            returnInfo.setMsg("用户名或密码错误");
            return returnInfo;
        }
        session.setAttribute("user",selectUser);
        returnInfo.setStatus(true);
        return returnInfo;
    }

    /**
     * 注册
     * @param code
     * @param user
     * @param session
     * @return
     */
    @PostMapping("/register")
    public ReturnInfo register(String code,User user,HttpSession session){
        returnInfo.setStatus(false);
        if(!verifyCheckCode(session, code)){
            returnInfo.setMsg("验证码错误");
            return returnInfo;
        }
        returnInfo.setMsg("");
        return userService.register(user);
    }

    /**
     * 获取用户信息
     * @param session
     * @return
     */
    @GetMapping("/getUserInfo")
    public User getUserInfo(HttpSession session){
        User user = (User) session.getAttribute("user");
        return user;
    }

    /**
     * 查询所有用户
     * @return
     */
    @GetMapping("/findAll")
    public List<User> findAll(){
        return userService.findAll();
    }

    /**
     * 查询用户图表
     * @return
     */
    @GetMapping("findChart")
    public UserChart findChart(){
        return userService.queryUserChart();
    }

    /**
     * 退出
     * @param session
     */
    @GetMapping("logout")
    public void logOut(HttpSession session){
        session.removeAttribute("user");
    }

    /**
     * 是否有用户登录
     * @param session
     * @return
     */
    @GetMapping("/checkHaveUser")
    public boolean checkHaveUser(HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user!=null){
            return true;
        }
        return false;
    }

    /**
     * 检查用户名是否存在
     * @param uname
     * @return
     */
    @GetMapping("/checkUname")
    public boolean checkUname(String uname){
        return userService.checkUname(uname);
    }

    /**
     * 更新时检查用户名
     * @param uname
     * @return
     */
    @GetMapping("/checkUpdateUname")
    public boolean checkUpdateUname(String uname,HttpSession session){
        User user = getUserInfo(session);
        return userService.checkUpdateUname(uname,user.getUname());
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @PutMapping("/update")
    public ReturnInfo update(User user,HttpSession session,String code){
        returnInfo.setStatus(false);
        String sysCode = (String) session.getAttribute("code");
        if(!sysCode.equalsIgnoreCase(code)){
            returnInfo.setMsg("验证码错误");
            return returnInfo;
        }
        if(!userService.update(user)){
            returnInfo.setMsg("修改失败,请联系站长");
            return returnInfo;
        }
        logOut(session);
        session.setAttribute("user",user);
        returnInfo.setStatus(true);
        return returnInfo;
    }
}
