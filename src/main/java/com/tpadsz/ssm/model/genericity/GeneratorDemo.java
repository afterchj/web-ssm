package com.tpadsz.ssm.model.genericity;

/**
 * @author hongjian.chen
 * @date 2019/8/29 11:00
 */
public class GeneratorDemo {

    public static void main(String[] args) {

        Generator<Drink> coffeeGenerator = BaseGenerator.create(Drink.class);
        BaseGenerator<Coffee> baseGenerator = new BaseGenerator<>();
        Coffee Drink = baseGenerator.next(Coffee.class);
        System.out.println(Drink);
        System.out.println(coffeeGenerator.next());
        Coffee[] coffees=new Coffee[]{new Mocha(),new Latte(),new Cappuccino(),new Americano()};
        for (Coffee coffee:coffees){
            System.out.println(coffee);
        }
    }
}
