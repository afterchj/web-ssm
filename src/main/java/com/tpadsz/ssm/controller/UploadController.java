package com.tpadsz.ssm.controller;

import com.tpadsz.ssm.utils.ZipUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Iterator;


/**
 * Created by hongjian.chen on 2017/12/22.
 */
@Controller
@RequestMapping("/file")
public class UploadController {

    //    @ResponseBody
    @RequestMapping(value = "/upload1", method = RequestMethod.POST)
    public String addUser(HttpServletRequest request, @RequestParam("files") MultipartFile[] files) throws Exception {
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
                saveFile(files[i], savePath);
                int finalTime = (int) System.currentTimeMillis();
                System.out.println("上传时间=" + (finalTime - pre));
            }
        }
        return "success";
    }

    //    @ResponseBody
    @RequestMapping(value = "/upload2", method = RequestMethod.POST)
    public String upload2(HttpServletRequest request) throws Exception {
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
                    saveFile(file, savePath);
                }
                int finalTime = (int) System.currentTimeMillis();
                System.out.println("上传时间=" + (finalTime - pre));
            }
        }
        return "success";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String filesUpload(HttpServletRequest request, @RequestParam("files") MultipartFile[] files) throws Exception {
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

    private boolean saveFile(MultipartFile file, String savePath) throws Exception {
        System.out.println("file=" + savePath + file.getOriginalFilename());
        File targetFile = new File(savePath + file.getOriginalFilename());
        System.out.println("文件解压位置=" + ZipUtils.unZipFiles(targetFile, savePath, true).get(0).getPath());
        if (!file.isEmpty()) {
            try {
                file.transferTo(targetFile);
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
