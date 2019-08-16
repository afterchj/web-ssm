package com.tpadsz.ssm.model.genericity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hongjian.chen
 * @date 2019/8/16 13:14
 */
public class GenericVarargs {
    public static <T> List<T> makeList(T... args) {
        List<T> result = new ArrayList<>();
        for (T iten : args) {
            result.add(iten);
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> list=makeList("ABCDEFGHIJKLMNOPQRSTUVWXYZ".split(""));
        System.out.println(list);
    }
}
