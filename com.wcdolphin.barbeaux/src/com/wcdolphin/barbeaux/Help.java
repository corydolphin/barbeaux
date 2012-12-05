package com.wcdolphin.barbeaux;

import java.util.HashMap;
import java.util.Iterator;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class Help extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);
        TextView tv = (TextView)findViewById(R.id.help_content);
        tv.setText("Get ready for some awesome Debug Spew!\n");
        UsbManager usbManager = (UsbManager) getSystemService(Context.USB_SERVICE);
        UsbDevice usbDevice = null;
        HashMap<String, UsbDevice> usbDeviceList = usbManager.getDeviceList();
  
        Iterator<UsbDevice> deviceIterator = usbDeviceList.values().iterator();
        if (deviceIterator.hasNext()) {
            UsbDevice tempUsbDevice = deviceIterator.next();

 
            if (tempUsbDevice.getVendorId() == ArduinoCommunicatorActivity.ARDUINO_USB_VENDOR_ID) {
            	tv.append("We found an Arduino!\n");
                switch (tempUsbDevice.getProductId()) {
                case ArduinoCommunicatorActivity.ARDUINO_UNO_USB_PRODUCT_ID:
                    Toast.makeText(getBaseContext(), "Arduino Uno " + getString(R.string.found), Toast.LENGTH_SHORT).show();
                    tv.append("Arduino Uno " + getString(R.string.found) + "\n");
                    usbDevice = tempUsbDevice;
                    break;
                case ArduinoCommunicatorActivity.ARDUINO_UNO_R3_USB_PRODUCT_ID:
                    tv.append("Arduino Uno R3" + getString(R.string.found) + "\n");
                    usbDevice = tempUsbDevice;
                    break;
                }
            }
        }

        if (usbDevice == null) {
            tv.append("No Arduinos found. Reboot this Touchpad thang \n");
        }
    }
}
