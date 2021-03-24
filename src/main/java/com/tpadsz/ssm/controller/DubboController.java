//package com.tpadsz.ssm.controller;
//
//import com.tpadsz.after.api.RecordBillService;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//import java.util.Map;
//
///**
// * Created by hongjian.chen on 2018/12/11.
// */
//@RequestMapping("/zoo")
//@RestController
//public class DubboController {
//
//    @Resource(name = "recordBillService1")
//    private RecordBillService recordBillService1;
//
//    @Resource(name = "recordBillService2")
//    private RecordBillService recordBillService2;
//
//    @RequestMapping("v1")
//    public Map version1() {
//        Map map = (Map) recordBillService1.getByDeviceId("dev_111");
//        map.put("onther", "version0.3.0");
//        return map;
//    }
//
//    @RequestMapping("v2")
//    public Map version2() {
//        Map map = (Map) recordBillService2.getByDeviceId("dev_222");
//        map.put("onther", "version0.5.0");
//        return map;
//    }
//}
