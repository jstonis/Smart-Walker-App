package com.example.josceyn.loginregister;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends Activity implements View.OnClickListener{

    Button bLogout;
    EditText etName, etAge, etUsername, etPhysicalTherapist;
    CheckBox etAdmin;
    UserLocalStore userLocalStore;

    @Override
    public void onStart(){
        super.onStart();

        if (authenticate()==true)
        {
            displayUserDetails();
        }
        else
        {
            startActivity(new Intent(MainActivity.this,Login.class));
        }

    }


    private boolean authenticate(){
        return userLocalStore.getUserLoggedIn();
    }

    private void displayUserDetails(){
        User user=userLocalStore.getLoggedInUser();


        etUsername.setText(user.username);
        etName.setText(user.name);
        etPhysicalTherapist.setText(user.physicalTherapist);
        if(user.admin==true)
        {
            etAdmin.setChecked(true);
        }
        else
        {
            etAdmin.setChecked(false);
        }

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bLogout:
                userLocalStore.clearUserData();
                userLocalStore.setUserLoggedIn(false);

                startActivity(new Intent(this, Login.class));

                break;

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName= (EditText) findViewById(R.id.etName);
        etAge= (EditText) findViewById(R.id.etAge);
        etUsername= (EditText) findViewById(R.id.etUsername);
        etPhysicalTherapist= (EditText) findViewById(R.id.etPhysicalTherapist);
        etAdmin=(CheckBox) findViewById(R.id.etAdmin);


        bLogout= (Button) findViewById(R.id.bLogout);
        userLocalStore=new UserLocalStore(this);

     bLogout.setOnClickListener(this);

      /*  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

}
