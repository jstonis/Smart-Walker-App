package com.example.josceyn.loginregister;

/**
 * Created by Josceyn on 1/28/2016.
 */
public class User {
    String name, username, password, physicalTherapist;
    boolean admin;

    public User(String name, String username, String password, String physicalTherapist, boolean admin){
        this.name=name;
        this.username=username;
        this.password=password;
        this.physicalTherapist=physicalTherapist;
        this.admin=admin;
    }
    public User(String username, String password, boolean admin){
        this.username=username;
        this.password=password;
        this.physicalTherapist="";
        this.admin=false;
    }


}
