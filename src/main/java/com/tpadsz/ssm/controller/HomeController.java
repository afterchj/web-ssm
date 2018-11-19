package com.tpadsz.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by zhm on 2015/7/13.
 */
@Controller
public class HomeController {
    @RequestMapping(value = "/webchat/{username}")
    public String webchat(@PathVariable String username, HttpSession session) {
        session.setAttribute("USERNAME", username);
        return "websocket";
    }

    @RequestMapping(value = "/upload")
    public String upload(HttpSession session) {
        return "fileupload";
    }
}
