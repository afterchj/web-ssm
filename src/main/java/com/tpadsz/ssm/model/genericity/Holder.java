package com.tpadsz.ssm.model.genericity;

import com.tpadsz.ssm.model.Shop;

/**
 * @author hongjian.chen
 * @date 2019/8/16 10:41
 */
public class Holder <T>{

    private T a;
    public Holder(T a) {
        this.a = a;
    }

    public T getA() {
        return a;
    }

    public void setA(T a) {
        this.a = a;
    }

    public static void main(String[] args) {
        Shop shop=new Shop();
        shop.setMid("mid_101");
        shop.setPid(101);
        Holder<Shop> shopHolder=new Holder<>(new Shop());
        System.out.println(shopHolder.getA().getMid());
        shopHolder.setA(shop);
        System.out.println(shopHolder.getA().getMid());
    }
}
