package com.tpadsz.ssm.model.genericity;

import com.tpadsz.ssm.model.Shop;

public class GenericMethods {

    public <T> T f(T x) {
        System.out.println(x.getClass().getName());
        return x;
    }

    public static void main(String[] args) {
        GenericMethods genericMethods = new GenericMethods();
        genericMethods.f("");
        genericMethods.f(1);
        genericMethods.f(1.0);
        genericMethods.f(1.0f);
        Shop shop = genericMethods.f(new Shop());
        shop.setPid(101);
        System.out.println(shop.getPid());
    }
}
