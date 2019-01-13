package com.tpadsz.ssm.controller;

import com.tpadsz.ssm.model.FileInfo;
import com.tpadsz.ssm.model.User;
import com.tpadsz.ssm.service.UserService;

import com.tpadsz.ssm.utils.AppUtils;
import com.tpadsz.ssm.utils.FileUtils;
import com.tpadsz.ssm.utils.ZipUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/user")
public class UserController {

    private Logger log = LoggerFactory.getLogger(UserController.class);
    @Resource
    private UserService userService;

    @RequestMapping("/showUser")
    public String showUser(Model model) {
        List<User> userList = userService.getAllUser();
        model.addAttribute("userList", userList);
        return "showUser";
    }

    @RequestMapping("/login")
    public String showUser(HttpServletRequest request, User user, Model model) {
        User user1 = userService.selectByName(user);
        System.out.println("name=" + user.getUserName() + ",pwd=" + user.getUserPwd());
        if (user1 != null) {
            model.addAttribute("user", user1);
            //return "ok";
        }
        String savePath = request.getServletContext().getRealPath("/") + "upload";
        System.out.println("savePath=" + savePath);
        File file = new File(savePath);
        if (!file.exists() && !file.isDirectory()) {
            System.out.println(savePath + "目录不存在，需要创建");
            file.mkdir();

        }
        try {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setHeaderEncoding("UTF-8");
            if (!ServletFileUpload.isMultipartContent(request)) {
                return "";
            }
            List<FileItem> list = upload.parseRequest(request);
            System.out.println("list=" + list.size());
            for (FileItem item : list) {
                if (item.isFormField()) {
                    String name = item.getFieldName();
                    String value = item.getString("UTF-8");
                    System.out.println(name + "=" + value);
                } else {
                    String filename = item.getName();
                    System.out.println("filename=" + filename);
                    if (filename == null || filename.trim().equals("")) {
                        continue;
                    }
                    filename = filename.substring(filename.lastIndexOf("\\") + 1);
                    InputStream in = item.getInputStream();
                    FileOutputStream out = new FileOutputStream(savePath + "\\" + filename);
                    byte buffer[] = new byte[1024];
                    int len;
                    while ((len = in.read(buffer)) > 0) {
                        out.write(buffer, 0, len);
                    }
                    in.close();
                    out.close();
                    item.delete();
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return "ok";
    }

    //    @ResponseBody
    @RequestMapping(value = "/upload.do")
    public String upload(String account, MultipartFile file) {
        account = account.isEmpty() ? "766256898@qq.com" : account;
//        System.out.println(info.getFileName() + "     " + info.getDesc());
        HttpServletRequest request = AppUtils.getRequest();
        String path = request.getServletContext().getRealPath("/upload/");
        String fileName = file.getOriginalFilename();
//        System.out.println("savePath=" + path + ",fileName=" + fileName);
        File targetFile = new File(path, fileName);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        try {
            FileUtils.saveFile(file, path, fileName, true);
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
        String path = request.getServletContext().getRealPath("/") + "upload";
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
