package com.tpadsz.ssm.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by issuser on 2020/04/20.
 */

@RestController
@RequestMapping(value = "/rpc")
public class IdaasController {

    private static Logger logger = org.apache.log4j.Logger.getLogger(IdaasController.class);

    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public String add(@RequestBody String param) {
        logger.warn("add event execute:" + param);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success", true);
        jsonObject.put("code", "200");
        jsonObject.put("message", "成功");
        jsonObject.put("method", "POST");
        return jsonObject.toJSONString();
    }

    //
    @RequestMapping(value = "/account", method = RequestMethod.PUT)
    public String update(@RequestBody String param) {
        logger.warn("update event execute:" + param);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success", true);
        jsonObject.put("code", "200");
        jsonObject.put("message", "成功");
        jsonObject.put("method", "PUT");
        return jsonObject.toJSONString();
    }

    @RequestMapping(value = "/account", method = RequestMethod.DELETE)
    public String delete(@RequestBody String param) {
        logger.warn("delete event execute:" + param);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success", true);
        jsonObject.put("success", true);
        jsonObject.put("code", "200");
        jsonObject.put("method", "DELETE");
        return jsonObject.toJSONString();
    }
}
