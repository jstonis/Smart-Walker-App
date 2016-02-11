package com.example.josceyn.loginregister;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;

public class Register extends Activity implements View.OnClickListener {


    Button bRegister;
    EditText etName, etAge, etUsername, etPassword, etPhysicalTherapist;
    CheckBox etAdmin;

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bRegister:
                String name=etName.getText().toString();
                String username=etUsername.getText().toString();
                String password=etPassword.getText().toString();
                String physicalTherapist=etPhysicalTherapist.getText().toString();
                boolean admin=etAdmin.isChecked();

                User user=new User(name,username,password,physicalTherapist,admin);

                registerUser(user);
                break;

        }
    }
    private void registerUser(User user){
        ServerRequests serverRequests=new ServerRequests(this);
        serverRequests.storeUserDataInBackground(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName= (EditText) findViewById(R.id.etName);
        etAge= (EditText) findViewById(R.id.etAge);
        etUsername= (EditText) findViewById(R.id.etUsername);
        etPassword= (EditText) findViewById(R.id.etPassword);
        etPhysicalTherapist=(EditText) findViewById(R.id.etPhysicalTherapist);
        bRegister= (Button) findViewById(R.id.bRegister);
        etAdmin= (CheckBox) findViewById(R.id.etAdmin);
        bRegister.setOnClickListener(this);

   /*     Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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
