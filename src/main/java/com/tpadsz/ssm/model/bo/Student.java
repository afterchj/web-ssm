package com.tpadsz.ssm.model.bo;


import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Student {

    @ExcelProperty(value = "id")
    private Integer id;

    @ExcelProperty(value = "name")
    private String name;

    @ExcelProperty(value = "age")
    private Integer age;

    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    @ExcelProperty(value = "create_time")
    private Date createTime;

    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    @ExcelProperty(value = "update_time")
    private Date updateTime;
}
