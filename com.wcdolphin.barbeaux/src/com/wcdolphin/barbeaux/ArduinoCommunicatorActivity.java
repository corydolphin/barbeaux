package com.wcdolphin.barbeaux;

import java.util.HashMap;
import java.util.Iterator;

import com.wcdolphin.barbeaux.DrinkDefinitions.Drink;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

public class ArduinoCommunicatorActivity extends Activity {

    public static final int ARDUINO_USB_VENDOR_ID = 0x2341;
    public static final int ARDUINO_UNO_USB_PRODUCT_ID = 0x01;
    public static final int ARDUINO_MEGA_2560_USB_PRODUCT_ID = 0x10;
    public static final int ARDUINO_MEGA_2560_R3_USB_PRODUCT_ID = 0x42;
    public static final int ARDUINO_UNO_R3_USB_PRODUCT_ID = 0x43;


    final static String DRINK_BIO_INTENT = "com.wcdolphin.DRINK_BIO";
    final static String DRINK_ORDER_INTENT = "com.wcdolphin.DRINK_ORDER";
    final static String DRINK_MAKE_INTENT = "com.wcdolphin.DRINK_MAKE";
    private final static String TAG = "ArduinoCommunicatorActivity";
    private final static boolean DEBUG = true;
    private Boolean mIsReceiving;
    private String inMessageBuffer = new String();
    public static MessageQueue messageQueue = new MessageQueue();
    
    private void findDevice() {
        UsbManager usbManager = (UsbManager) getSystemService(Context.USB_SERVICE);
        UsbDevice usbDevice = null;
        HashMap<String, UsbDevice> usbDeviceList = usbManager.getDeviceList();
        if (DEBUG)
        {
        	Log.d(TAG, "length: " + usbDeviceList.size());
        }
        Iterator<UsbDevice> deviceIterator = usbDeviceList.values().iterator();
        if (deviceIterator.hasNext()) {
            UsbDevice tempUsbDevice = deviceIterator.next();

            // Print device information. If you think your device should be able
            // to communicate with this app, add it to accepted products below. test
            if (DEBUG) Log.d(TAG, "VendorId: " + tempUsbDevice.getVendorId());
            if (DEBUG) Log.d(TAG, "ProductId: " + tempUsbDevice.getProductId());
            if (DEBUG) Log.d(TAG, "DeviceName: " + tempUsbDevice.getDeviceName());
            if (DEBUG) Log.d(TAG, "DeviceId: " + tempUsbDevice.getDeviceId());
            if (DEBUG) Log.d(TAG, "DeviceClass: " + tempUsbDevice.getDeviceClass());
            if (DEBUG) Log.d(TAG, "DeviceSubclass: " + tempUsbDevice.getDeviceSubclass());
            if (DEBUG) Log.d(TAG, "InterfaceCount: " + tempUsbDevice.getInterfaceCount());
            if (DEBUG) Log.d(TAG, "DeviceProtocol: " + tempUsbDevice.getDeviceProtocol());

            if (tempUsbDevice.getVendorId() == ARDUINO_USB_VENDOR_ID) {
                if (DEBUG) Log.i(TAG, "Arduino device found!");

                switch (tempUsbDevice.getProductId()) {
                case ARDUINO_UNO_USB_PRODUCT_ID:
                    Toast.makeText(getBaseContext(), "Arduino Uno " + getString(R.string.found), Toast.LENGTH_SHORT).show();
                    usbDevice = tempUsbDevice;
                    break;
                case ARDUINO_MEGA_2560_USB_PRODUCT_ID:
                    Toast.makeText(getBaseContext(), "Arduino Mega 2560 " + getString(R.string.found), Toast.LENGTH_SHORT).show();
                    usbDevice = tempUsbDevice;
                    break;
                case ARDUINO_MEGA_2560_R3_USB_PRODUCT_ID:
                    Toast.makeText(getBaseContext(), "Arduino Mega 2560 R3 " + getString(R.string.found), Toast.LENGTH_SHORT).show();
                    usbDevice = tempUsbDevice;
                    break;
                case ARDUINO_UNO_R3_USB_PRODUCT_ID:
                    Toast.makeText(getBaseContext(), "Arduino Uno R3 " + getString(R.string.found), Toast.LENGTH_SHORT).show();
                    usbDevice = tempUsbDevice;
                    break;
                }
            }
        }

        if (usbDevice == null) {
            if (DEBUG) Log.i(TAG, "No device found!");
            Toast.makeText(getBaseContext(), getString(R.string.no_device_found), Toast.LENGTH_LONG).show();
        } else {
            if (DEBUG) Log.i(TAG, "Device found!");
            Intent startIntent = new Intent(getApplicationContext(), ArduinoCommunicatorService.class);
            PendingIntent pendingIntent = PendingIntent.getService(getApplicationContext(), 0, startIntent, 0);
            usbManager.requestPermission(usbDevice, pendingIntent);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (DEBUG) Log.d(TAG, "onCreate()");
        setContentView(R.layout.debuggingv);
        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
    			Drink drink = Drink.values()[position];
                Intent dIntent = new Intent(DRINK_BIO_INTENT);
                dIntent.putExtra(DrinkBioActivity.DATA_EXTRA, position);              
                sendBroadcast(dIntent);
            }
        });
        
        IntentFilter filter = new IntentFilter();
        filter.addAction(ArduinoCommunicatorService.DATA_RECEIVED_INTENT); //bind to service intents
        filter.addAction(ArduinoCommunicatorService.DATA_SENT_INTERNAL_INTENT); //bind to service intent
        filter.addAction(DRINK_BIO_INTENT); //bind to service intent
        filter.addAction(DRINK_ORDER_INTENT);
        registerReceiver(mReceiver, filter);
        findDevice();
    }


    @Override
    protected void onNewIntent(Intent intent) {
        if (DEBUG) Log.d(TAG, "onNewIntent() " + intent);
        super.onNewIntent(intent);

        if (UsbManager.ACTION_USB_DEVICE_ATTACHED.contains(intent.getAction())) {
            if (DEBUG) Log.d(TAG, "onNewIntent() " + intent);
            findDevice();
        }
    }

    @Override
    protected void onDestroy() {
        if (DEBUG) Log.d(TAG, "onDestroy()");
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.help:
            startActivity(new Intent(this, Help.class));
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }

    BroadcastReceiver mReceiver = new BroadcastReceiver() {

        private void handleTransferedData(Intent intent, boolean receiving) {
            if (mIsReceiving == null || mIsReceiving != receiving) {
                mIsReceiving = receiving;
            }

            final byte[] newTransferedData = intent.getByteArrayExtra(ArduinoCommunicatorService.DATA_EXTRA);
           
        	String stBuild = "";
        	for( byte b : newTransferedData){
        		stBuild += (char)b;
        	}
        	if( receiving){
	        	inMessageBuffer += stBuild;
	        	if( inMessageBuffer.contains("\n"))
	        	{
	        		String newestMessage = inMessageBuffer.substring(0, inMessageBuffer.indexOf("\n"));
	        		inMessageBuffer = inMessageBuffer.substring(inMessageBuffer.indexOf("\n")+1);
	        		
	        		messageQueue.add(newestMessage.replace("\n", "\\n"));
	        		Log.i(TAG, "message: " + "\"" + newestMessage.replace("\n", "\\n") + "\"");
	        		//outputView.setText(outputView.getText().toString() +dateFormat.format(new Date()) + newestMessage + "\n");
	        	}
        	}
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (DEBUG) Log.d(TAG, "onReceive() " + action);

            if (ArduinoCommunicatorService.DATA_RECEIVED_INTENT.equals(action)) {
                handleTransferedData(intent, true);
            } else if (ArduinoCommunicatorService.DATA_SENT_INTERNAL_INTENT.equals(action)) {
                handleTransferedData(intent, false);
            }
            else if(ArduinoCommunicatorActivity.DRINK_BIO_INTENT.equals(action)){
                Intent dbio = new Intent(context, DrinkBioActivity.class);
                dbio.putExtra(DrinkBioActivity.DATA_EXTRA, intent.getIntExtra(DrinkBioActivity.DATA_EXTRA, 0));
            	startActivity(dbio);
            }
            else if(ArduinoCommunicatorActivity.DRINK_ORDER_INTENT.equals(action)){
                Intent dOrder = new Intent(context, DrinkOrderActivity.class);
                dOrder.putExtra(DrinkOrderActivity.DATA_EXTRA, intent.getIntExtra(DrinkOrderActivity.DATA_EXTRA, 0));
            	startActivity(dOrder);
            }
        }
    };
    

}
