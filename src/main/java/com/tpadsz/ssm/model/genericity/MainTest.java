package com.tpadsz.ssm.model.genericity;

import javax.naming.Name;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by after on 2020/5/12.
 */
public abstract class MainTest {

    private String name = "admin";

    public static boolean isMatch(String s) {
        Map<Character, Character> source = new HashMap();
        source.put(')', '(');
        source.put(']', '[');
        source.put('}', '{');

        Stack list = new Stack();
        for (int i = 0; i < s.length(); i++) {
            char temp = s.charAt(i);
            if (source.containsValue(temp)) {
                System.out.println("value=" + temp);
                list.push(temp);
            } else if (source.containsKey(temp)) {
                if (list.isEmpty()) {
                    return false;
                }
                if (list.peek() == source.get(temp)) {
                    list.pop();
                } else {
                    return false;
                }
            }
        }
        return list.isEmpty() ? true : false;

    }

    public static void main(String[] args) {

        System.out.println(isMatch("(***)-[{-------}]")); //true
//        System.out.println(isMatch("(2+4)*a[5]")); //true
//        System.out.println(isMatch("({}[]]])")); //false
//        System.out.println(isMatch("())))")); //false
    }

}
