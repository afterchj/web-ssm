package com.tpadsz.ssm.model.genericity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hongjian.chen
 * @date 2019/8/16 13:32
 */
public class BaseGeneratorDemo {
    public static void main(String[] args) {
        Drink[] coffees = new Coffee[]{new Latte(), new Mocha(), new Cappuccino()};
        Coffee[] coffee = new Mocha[]{new Mocha(),new Mocha(),new Mocha()};
        //可以写入子类和自身
        List<? super Coffee> list = News.list(coffees);
        list.add(new Americano());
        list.add(new Coffee());
//        list.add(new Drink());
//        list.add(new Object());
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println("size\t" + list.size());
        List<?> list1 = new ArrayList<>();
        list1.add(null);
        List<? extends Coffee> list2 = News.list(coffee);
//        list2.add(new Drink());
        for (int i = 0; i < list2.size(); i++) {
            System.out.println(list2.get(i));
        }
//        list2.add(new Coffee());
//        list2.add(new Object())
        System.out.println("size\t" + list2.size());
//        Generator<Coffee> gen = new BaseGenerator<>(Coffee.class);
//        Generator<Mocha> gen1 = BaseGenerator.create(Mocha.class);
//        for (int i = 0; i < 3; i++) {
//            System.out.println(gen.next() + "\t" + gen1.next());
//        }
    }
}
