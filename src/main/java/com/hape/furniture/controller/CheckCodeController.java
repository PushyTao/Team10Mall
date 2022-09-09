package com.hape.furniture.controller;

import com.hape.furniture.util.VerifyCodeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
public class CheckCodeController {
    @RequestMapping("/checkCode")
    public void getImage(HttpSession session, HttpServletResponse response) throws IOException {
        //生成验证码
        String securityCode = VerifyCodeUtils.getSecurityCode();
        //将验证码放入session
        session.setAttribute("code",securityCode);
        //生成图片
        BufferedImage image = VerifyCodeUtils.createImage(securityCode);
        //输出图片
        ServletOutputStream outputStream = response.getOutputStream();
        //调用工具类
        ImageIO.write(image,"png",outputStream);
    }
}
