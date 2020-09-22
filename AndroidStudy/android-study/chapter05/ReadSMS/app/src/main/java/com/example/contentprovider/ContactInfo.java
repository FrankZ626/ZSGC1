package com.example.contentprovider;

public class ContactInfo {
    private int _id;
    private String number;
    private String name;

    public ContactInfo(int _id, String number, String name) {
        this._id = _id;
        this.number = number;
        this.name = name;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
