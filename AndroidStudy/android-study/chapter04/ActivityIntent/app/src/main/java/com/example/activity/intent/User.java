package com.example.activity.intent;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private int age;
    private String classmate;

    public User(String username, int age, String classmate) {
        this.username = username;
        this.age = age;
        this.classmate = classmate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getClassmate() {
        return classmate;
    }

    public void setClassmate(String classmate) {
        this.classmate = classmate;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", age=" + age +
                ", classmate='" + classmate + '\'' +
                '}';
    }
}
