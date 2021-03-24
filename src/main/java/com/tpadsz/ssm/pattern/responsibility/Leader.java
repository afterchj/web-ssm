package com.tpadsz.ssm.pattern.responsibility;

/**
 * @author hongjian.chen
 * @date 2019/11/15 17:06
 */
public abstract class Leader {
    private Leader next;
    public void setNext(Leader next)
    {
        this.next=next;
    }
    public Leader getNext()
    {
        return next;
    }
    //处理请求的方法
    public abstract void handleRequest(int LeaveDays);
}
