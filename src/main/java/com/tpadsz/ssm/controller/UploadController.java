package com.tpadsz.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;


/**
 * Created by hongjian.chen on 2017/12/22.
 */
@Controller
@RequestMapping("/file")
public class UploadController {

    //    @ResponseBody
    @RequestMapping("/upload1")
    public String addUser(HttpServletRequest request, @RequestParam("files") MultipartFile[] files) {
        System.out.println("request=" + request.getParameter("comment"));
        String savePath = request.getServletContext().getRealPath("/upload/");
        File file = new File(savePath);
        if (!file.exists()) {
            file.mkdir();
        }
        for (int i = 0; i < files.length; i++) {
            System.out.println("fileName---------->" + files[i].getOriginalFilename());
            if (!files[i].isEmpty()) {
                int pre = (int) System.currentTimeMillis();
                try {
                    files[i].transferTo(new File(savePath + files[i].getOriginalFilename()));
                    int finalTime = (int) System.currentTimeMillis();
                    System.out.println("上传时间=" + (finalTime - pre));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "success";
    }

    @ResponseBody
    @RequestMapping("/upload2")
    public String upload2(HttpServletRequest request) throws IllegalStateException, IOException {
        System.out.println("request=" + request.getParameter("comment"));
        String savePath = request.getServletContext().getRealPath("/img/");
        File file1 = new File(savePath);
        if (!file1.exists()) {
            file1.mkdir();
        }
        MultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (multipartResolver.isMultipart(request)) {
            //转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                int pre = (int) System.currentTimeMillis();
                MultipartFile file = multiRequest.getFile(iter.next());
                if (file != null) {
                    String fileName = file.getOriginalFilename();
                    if (fileName.trim() != "") {
                        File localFile = new File(savePath + fileName);
                        file.transferTo(localFile);
                    }
                }
                int finalTime = (int) System.currentTimeMillis();
                System.out.println("上传时间=" + (finalTime - pre));
            }
        }
        return "000";
    }

    @RequestMapping("/upload")
    public String filesUpload(HttpServletRequest request, @RequestParam("files") MultipartFile[] files) {
        String savePath = request.getServletContext().getRealPath("/upload/");
        File filePath = new File(savePath);
        if (!filePath.exists()) {
            filePath.mkdir();
        }
        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                saveFile(file, savePath);
            }
        }
        return "ok";
    }

    private boolean saveFile(MultipartFile file, String savePath) {
        System.out.println("file=" + savePath + file.getOriginalFilename());
        if (!file.isEmpty()) {
            try {
                file.transferTo(new File(savePath + file.getOriginalFilename()));
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @RequestMapping("/toUpload")
    public String toUpload(ModelMap modelMap) {
        modelMap.put("result", "success");
        return "/upload";
    }
}
