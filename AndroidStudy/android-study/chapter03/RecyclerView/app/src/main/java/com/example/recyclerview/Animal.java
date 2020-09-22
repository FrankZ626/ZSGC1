package com.example.recyclerview;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Animal implements Serializable {
    private String name;
    private String introduces;
    private int icon;

    public Animal() {
    }

    public Animal(String name, String introduces, int icon) {
        this.name = name;
        this.introduces = introduces;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduces() {
        return introduces;
    }

    public void setIntroduces(String introduces) {
        this.introduces = introduces;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    @NonNull
    @Override
    public String toString() {
        return "{name=" + name + ", introduces=" + introduces + '}';
    }
}
