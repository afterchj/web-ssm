package com.tpadsz.ssm.pattern.responsibility;

/**
 * @author hongjian.chen
 * @date 2019/11/15 17:07
 */
public class ClassAdviser extends Leader {

    public void handleRequest(int LeaveDays) {
        if (LeaveDays <= 2) {
            System.out.println("班主任批准您请假" + LeaveDays + "天。");
        } else {
            if (getNext() != null) {
                getNext().handleRequest(LeaveDays);
            } else {
                System.out.println("请假天数太多，没有人批准该假条！");
            }
        }
    }
}
