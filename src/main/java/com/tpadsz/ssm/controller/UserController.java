package com.tpadsz.ssm.controller;

import com.tpadsz.ssm.model.User;
import com.tpadsz.ssm.service.UserService;
import com.tpadsz.ssm.utils.AppUtils;
import com.tpadsz.ssm.utils.ChatUtils;
import com.tpadsz.ssm.utils.FileUtils;
import com.tpadsz.ssm.websocket.SpringWebSocketHandlerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.Map;


@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/showUser")
    public String showUser(Model model) {
        List<Map> userList = userService.getAllUser();
        model.addAttribute("userList", userList);
        return "showUser";
    }

    @RequestMapping("/login")
    public String showUser(HttpServletRequest request, User user) {
        String username = user.getUserName();
        log.warn("remoteIp {} ip {}", AppUtils.getRemoteIP(request), request.getRemoteAddr());
        username = StringUtils.isEmpty(username) ? ChatUtils.getRandomNickName() : username;
        request.getSession().setAttribute("WEBSOCKET_USERNAME", username);
//        User user1 = userService.selectByName(user);
//        if (user1 != null) {
//            model.addAttribute("user", user1);
//            //return "ok";
//        }
        return "websocket";
    }

    @ResponseBody
    @RequestMapping("/ws")
    public String test(HttpServletRequest request, User user) {
        String username = user.getUserName();
        log.warn("user=" + username);
        username = StringUtils.isEmpty(username) ? ChatUtils.getRandomNickName() : username;
        request.getSession().setAttribute("WEBSOCKET_USERNAME", username);
//        User user1 = userService.selectByName(user);
//        if (user1 != null) {
//            model.addAttribute("user", user1);
//            //return "ok";
//        }
        return "ok";
    }

    //    @ResponseBody
    @RequestMapping(value = "/upload.do")
    public String upload(String account, MultipartFile file,HttpServletRequest request ) {
        System.out.println("count=" + account + ",ip=" + AppUtils.getRemoteIP(null));
        account = account.isEmpty() ? "766256898@qq.com" : account;
//        System.out.println(info.getFileName() + "     " + info.getDesc());
//        HttpServletRequest request = AppUtils.getRequest();
        String path = request.getServletContext().getRealPath("/upload/");
        String fileName = file.getOriginalFilename();
//        System.out.println("savePath=" + path + ",fileName=" + fileName);
        File targetFile = new File(path, fileName);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        try {
            if (StringUtils.isNotEmpty(fileName)) {
                FileUtils.saveFile(file, path, fileName, true);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
//        request.setAttribute("path", fileName);
        request.setAttribute("account", account);
//        AppUtils.getSession().setAttribute("account", account);

        return "record_map";
    }

    @RequestMapping(value = "/test")
    public String test(HttpServletRequest request, User user, @RequestParam(value = "file") MultipartFile file) {
        System.out.println(user.getUserName() + "     " + user.getUserPwd());
//        System.out.println(file.getOriginalFilename());
        String path = request.getServletContext().getRealPath("/") + "upload/";
//        System.out.println("path=" + path);
        String fileName = file.getOriginalFilename();
        System.out.println(fileName);
//        String fileName = new Date().getTime()+".jpg";
        System.out.println(path);
        File targetFile = new File(path, fileName);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        //保存
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ok";
    }
}
