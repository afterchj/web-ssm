package com.tpadsz.ssm.pattern.responsibility;

/**
 * @author hongjian.chen
 * @date 2019/11/15 17:10
 */
public class LeaveApprovalTest {
    public static void main(String[] args) {
        //组装责任链
        Leader teacher1 = new ClassAdviser();
        Leader teacher2 = new DepartmentHead();
        Leader teacher3 = new President();
        Leader teacher4=new DeanOfStudies();
        teacher1.setNext(teacher2);
        teacher2.setNext(teacher3);
        teacher3.setNext(teacher4);
        //提交请求
        teacher1.handleRequest(1);
        teacher1.handleRequest(5);
        teacher1.handleRequest(9);
        teacher1.handleRequest(12);
        teacher1.handleRequest(25);
    }
}
