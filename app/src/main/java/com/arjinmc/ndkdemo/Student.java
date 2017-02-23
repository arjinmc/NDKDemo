package com.arjinmc.ndkdemo;

/**
 * Created by Eminem on 2017/2/19.
 */

public class Student {

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Student(String name,int age){
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student >> name:"+name+"\tage:"+age;
    }
}
