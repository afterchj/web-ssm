package com.tpadsz.ssm.test;

/**
 * Created by after on 2018/11/4.
 */
public enum Gender {
    MALE("男"), FEMALE("女");
    private String name;

    Gender(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static void main(String[] args) {
        System.out.println(Gender.MALE.getName());
        for (Gender gender:Gender.values()){
            System.out.println(gender.getName());
        }
    }
}
