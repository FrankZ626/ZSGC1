package com.example.contentresolver;


import androidx.annotation.NonNull;

import java.io.Serializable;

public class Address implements Serializable {
    private int _id;
    private String name;
    private String phone;

    public Address() {
    }

    public Address(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public Address(int _id, String name, String phone) {
        this._id = _id;
        this.name = name;
        this.phone = phone;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @NonNull
    @Override
    public String toString() {
        return "Address{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
