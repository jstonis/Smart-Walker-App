package com.example.josceyn.loginregister;

import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class UserInteraction extends AppCompatActivity {

    UsbManager usbManager=(UsbManager) getSystemService(Context.USB_SERVICE);
    UsbDevice device;
    UsbDeviceConnection connection;
    MediaPlayer alertSound;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_interaction);




    }
}
