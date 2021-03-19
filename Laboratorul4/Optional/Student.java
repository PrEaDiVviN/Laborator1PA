package com.company;

import java.util.stream.Stream;

public class Student {
    private String name;
    private int age;
    private Sex sex;

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

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public static int compareByName(Student s1, Student s2) {
        if (s1 != null && s2 != null) {
            if (s1.name != null && s2.name != null)
                return s1.name.compareTo(s2.name);
        }
        return -1;
    }
    public static int compareByAgeDescendent(Student s1, Student s2) {
        if (s1.getAge() < s2.getAge())
            return -1;
        else if(s1.getAge() == s2.getAge())
            return 0;
        else
            return 1;
    }

    Student(String name) {
        this.name = name;
    }


    Student(String name, int age, Sex sex) {
        setName(name);
        setAge(age);
        setSex(sex);
    }

    @Override
    public String toString() {
        return "Studentul " + getName() + " cu varsta " + getAge() + " si sexul " + getSex() + ".";
    }

}
