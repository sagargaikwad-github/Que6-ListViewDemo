package com.example.listviewarrayadapter;

import android.provider.ContactsContract;

import java.util.ArrayList;

public class FruitData {
    int image;
    String name;
    String short_desc;
    String desc;
    String email;

    public FruitData(int image, String name, String short_desc, String desc, String email) {
        this.image = image;
        this.name = name;
        this.desc = desc;
        this.short_desc = short_desc;
        this.email = email;


    }

    public String getShort_desc() {
        return short_desc;
    }

    public void setShort_desc(String short_desc) {
        this.short_desc = short_desc;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
