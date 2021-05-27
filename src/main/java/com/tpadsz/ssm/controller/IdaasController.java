package com.tpadsz.ssm.controller;

import com.alibaba.fastjson.JSONObject;
import com.isoft.after.model.dto.UserDTO;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;


/**
 * Created by issuser on 2020/04/20.
 */

@RestController
@RequestMapping(value = "/rpc")
public class IdaasController {

    private static Logger logger = org.apache.log4j.Logger.getLogger(IdaasController.class);

    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public Object add(@RequestBody(required = false) UserDTO param) {
        logger.warn("add event execute:" + param);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success", true);
        jsonObject.put("code", 200);
        jsonObject.put("message", "操作成功");
        jsonObject.put("method", "POST");
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    //
    @RequestMapping(value = "/account", method = RequestMethod.PUT)
    public Object update(@RequestBody String param) {
        logger.warn("update event execute:" + param);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success", true);
        jsonObject.put("status", 200);
        jsonObject.put("message", "成功");
        jsonObject.put("method", "PUT");
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public Object delete(@RequestBody String param) {
        logger.warn("delete event execute:" + param);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success", true);
        jsonObject.put("status", 200);
        jsonObject.put("method", "GET");
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }
}
