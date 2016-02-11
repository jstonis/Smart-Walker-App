package com.example.josceyn.loginregister;

import android.app.Activity;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ScrollView;
import android.widget.TextView;

import com.hoho.android.usbserial.driver.UsbSerialDriver;
import com.hoho.android.usbserial.driver.UsbSerialPort;
import com.hoho.android.usbserial.driver.UsbSerialProber;
import com.hoho.android.usbserial.util.HexDump;
import com.hoho.android.usbserial.util.SerialInputOutputManager;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


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

    }


    private boolean authenticate() {
        if (userLocalStore.getLoggedInUser() == null) {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
            return false;
        }
        return true;
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


        UsbManager manager=(UsbManager) getSystemService(Context.USB_SERVICE);
        List<UsbSerialDriver> availableDrivers= UsbSerialProber.getDefaultProber().findAllDrivers(manager);

        if(availableDrivers.isEmpty()){
            return;
        }
        //open connection to the first available driver
        UsbSerialDriver driver=availableDrivers.get(0);
        UsbDeviceConnection connection=manager.openDevice(driver.getDevice());
        if(connection==null){
            //you probably need to call UsbManger.requestPermission(driver.getDevice(),...);
            return;
        }

        //read some data, most have just one port (port 0)
        List myPortList = driver.getPorts();
        UsbSerialPort port = (com.hoho.android.usbserial.driver.UsbSerialPort)myPortList.get(0);
        try {
            port.open(connection);
            byte buffer[]=new byte[16];
            int numBytesRead = port.read(buffer, 1000);
            Log.d("Tag","Read " + numBytesRead + " bytes.");
            port.close();

        } catch (IOException e) {
            e.printStackTrace();
        }




        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);

        etName= (EditText) findViewById(R.id.etName);
        etAge= (EditText) findViewById(R.id.etAge);
        etUsername= (EditText) findViewById(R.id.etUsername);
        etPhysicalTherapist= (EditText) findViewById(R.id.etPhysicalTherapist);
        etAdmin=(CheckBox) findViewById(R.id.etAdmin);
        bLogout= (Button) findViewById(R.id.bLogout);

        bLogout.setOnClickListener(this);

        userLocalStore=new UserLocalStore(this);



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
