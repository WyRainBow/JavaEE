package com.experimental.entity;

public class User {
    private Integer id;
    private String name;
    private String email;
    private int age; // Example of a primitive type

    // Important: For Spring MVC to instantiate and populate this POJO,
    // it needs a public no-argument constructor (which is provided by default if no other constructor is defined).
    // It also needs public getter and setter methods for the properties to be bound.

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
} 