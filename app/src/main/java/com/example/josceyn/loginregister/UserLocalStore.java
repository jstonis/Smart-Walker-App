package com.example.josceyn.loginregister;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Josceyn on 1/28/2016.
 */
public class UserLocalStore {
    public static final String SP_NAME="userDetails";
    SharedPreferences userLocalDatabase;


    public UserLocalStore(Context context){
    userLocalDatabase=context.getSharedPreferences(SP_NAME,0);

    }

    public void storeUserData(User user){
        SharedPreferences.Editor spEditor=userLocalDatabase.edit();
        spEditor.putString("name",user.name);
        spEditor.putString("username",user.username);
        spEditor.putString("password",user.password);
        spEditor.putString("physicalTherapist", user.physicalTherapist);
        spEditor.putBoolean("admin",user.admin);
        spEditor.commit();
    }

    //gets details of user logged in in local database

    public User getLoggedInUser(){
        String name=userLocalDatabase.getString("name", "");
        String username=userLocalDatabase.getString("username","");
        String password=userLocalDatabase.getString("password","");
        String physicalTherapist=userLocalDatabase.getString("physicalTherapist","");
        Boolean admin=userLocalDatabase.getBoolean("admin", false);

        User storedUser=new User(name,username,password,physicalTherapist,admin);

        return storedUser;
    }

    public void setUserLoggedIn(boolean loggedIn){
        SharedPreferences.Editor spEditor=userLocalDatabase.edit();

        spEditor.putBoolean("loggedIn", loggedIn);
        spEditor.commit();

    }
    public boolean getUserLoggedIn(){
        if (userLocalDatabase.getBoolean("LoggedIn",false)==true)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void clearUserData(){
        SharedPreferences.Editor spEditor=userLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }



}
