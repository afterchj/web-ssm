package com.tpadsz.ssm.model.genericity;

/**
 * @author hongjian.chen
 * @date 2019/8/29 11:00
 */
public class GeneratorDemo {

    public static void main(String[] args) {

        Generator<Coffee> coffeeGenerator = BaseGenerator.create(Coffee.class);
        BaseGenerator<Coffee> baseGenerator = new BaseGenerator<>();
        Drink drink = baseGenerator.next(Cappuccino.class);
        Coffee coffee = coffeeGenerator.next(Mocha.class);
        System.out.println(coffeeGenerator.next());
        System.out.println(coffee);
        System.out.println(drink);
    }
}
