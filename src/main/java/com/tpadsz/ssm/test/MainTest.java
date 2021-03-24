package com.tpadsz.ssm.test;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.tpadsz.ssm.linstener.ExcelListener;
import com.tpadsz.ssm.model.bo.Student;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
public class MainTest {

    public static void main(String[] args) {

        String fileName = "E:\\t_student.xlsx";
        String outName = "E:\\t_student_out.xlsx";

        File file = new File(fileName);


        EasyExcel.read(file, Student.class, new ExcelListener(p -> p.stream().forEach(s -> log.info("s {}", JSON.toJSONString(s))))).sheet().doRead();
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Student student = new Student();
            student.setId(10 * (i + 1));
            student.setName("test" + i);
            student.setAge(17 + i);
            student.setCreateTime(new Date());
            student.setUpdateTime(new Date());
            students.add(student);
        }

//        EasyExcel.write(outName,Student.class).sheet().doWrite(students);
    }
}
