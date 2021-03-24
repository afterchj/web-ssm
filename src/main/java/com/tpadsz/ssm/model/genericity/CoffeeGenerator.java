package com.tpadsz.ssm.model.genericity;

/**
 * @author hongjian.chen
 * @date 2019/8/16 11:24
 */
public class CoffeeGenerator implements Generator<Coffee> {
//    private Class[] types={Latte.class,Mocha.class,Americano.class,Cappuccino.class};

    @Override
    public Coffee next(Class clazz) {
        try {
            return (Coffee) clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Coffee next() {
        return null;
    }

    public static void main(String[] args) {
        CoffeeGenerator generator=new CoffeeGenerator();
        System.out.println(generator.next(Latte.class));
        System.out.println(generator.next(Americano.class));
        System.out.println(generator.next(Cappuccino.class));
        System.out.println(generator.next(Mocha.class));
    }
}
