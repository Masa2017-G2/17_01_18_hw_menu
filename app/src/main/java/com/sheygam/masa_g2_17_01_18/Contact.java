package com.sheygam.masa_g2_17_01_18;

import java.io.Serializable;

/**
 * Created by gregorysheygam on 17/01/2018.
 */

public class Contact implements Serializable{
    private String name;
    private String email;
    private String phone;
    private String description;

    public Contact() {
    }

    public Contact(String name, String email, String phone, String description) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.description = description;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return name+","+email+","+phone+","+description;
    }

    public static Contact newInstance(String data){
        String[] arr = data.split(",");
        return new Contact(arr[0],arr[1],arr[2],arr[3]);
    }
}
