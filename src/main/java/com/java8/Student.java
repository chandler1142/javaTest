package com.java8;

/**
 * Created by chandlerzhao on 2017/7/25.
 */
public class Student {

    private Integer id;

    private String name;

    private Boolean gender;

    private Integer age;

    public Student(Integer id, String name, Boolean gender) {
        this.id = id;
        this.name = name;
        this.gender = gender;
    }

    public Student(Integer id, String name, Boolean gender, Integer age) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                '}';
    }
}
