package com.tpadsz.ssm.model.genericity;

/**
 * @author hongjian.chen
 * @date 2019/8/16 18:00
 */
public class Drink {

    private static long counter = 0;
    private final long id = counter++;

    public String toString() {
        return getClass().getSimpleName() + "\t" + id;
    }
}
