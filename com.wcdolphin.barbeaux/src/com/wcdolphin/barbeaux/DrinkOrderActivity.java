package com.wcdolphin.barbeaux;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.wcdolphin.barbeaux.DrinkDefinitions.Drink;
import com.wcdolphin.barbeaux.DrinkDefinitions.Ingredient;

public class DrinkOrderActivity extends Activity {
	final static String DATA_EXTRA = "wcdolphin.DrinkOrderActivity.Extra.Extra";
	final static String DATA_EXTRA_INGRED = "wcdolphin.DrinkOrderActivity.Extra.Ingred";
	final static String DATA_EXTRA_ORDER = "wcdolphin.DrinkOrderActivity.Extra.Ord";
	final static String INGREDIENT_INTENT = "wcdolphin.DrinkOrderActivity.Intent";
	private Integer drinkNumber = 0;

	
	private void sleep(int ms){
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        setContentView(R.layout.makedrink);
        IntentFilter filter = new IntentFilter();
        filter.addAction(DrinkOrderActivity.INGREDIENT_INTENT); //bind to service intents

        registerReceiver(doaReceiver, filter);
        
        int dNumber = (Integer)extras.get(DrinkOrderActivity.DATA_EXTRA);
        drinkNumber = dNumber;
		final Button cancelButton = (Button)findViewById(R.id.orderdrink_cancel_button);
		cancelButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	unregisterReceiver(doaReceiver);
                finish();
            }
        });
		final Button orderButton = (Button)findViewById(R.id.orderdrink_done_button);
		
		orderButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	
                Thread splashThread = new Thread() {

                    @Override
                    public void run() {
                    	Drink drink = Drink.values()[drinkNumber];
                    	int count = 1; //start offset, intentionally
                    	for(Ingredient ing : drink.items.keySet())
                    	{
                    		
                        	String message = "(" + ing.pin +"," + (int)(drink.items.get(ing)*200)  +")";
                            Log.d("DRINKORDER","pouring:" + ing.name);
                            sendString(message);
                            while(ArduinoCommunicatorActivity.messageQueue.peek() == null)
                        	{
                        		try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
                                Log.d("DRINKORDER","finished" + ing.name);
                        	}
                            notifyOfPour(count,ing.pin);
                            count+=1;
                    		Log.d("DRINKORDER", "messagePumped:" + ArduinoCommunicatorActivity.messageQueue.dequeue() );
                    	}
                           }
                        };
                        splashThread.start();
            	
            	


            }
        });
    }
    public void sendString(String s ){
		Intent i = new Intent("primavera.arduino.intent.action.SEND_DATA");
        i.putExtra("primavera.arduino.intent.extra.DATA", (s + "\n").getBytes());
        sendBroadcast(i);
    }
    
    
    BroadcastReceiver doaReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();

           if(INGREDIENT_INTENT.equals(action)){
        	   int ingredientId = intent.getIntExtra(DrinkOrderActivity.DATA_EXTRA_INGRED, 0);
        	   int ingredientCount = intent.getIntExtra(DrinkOrderActivity.DATA_EXTRA_ORDER, 0);
        	   LinearLayout step = (LinearLayout)findViewById(getIdHack(ingredientCount));
        	   step.setVisibility(View.VISIBLE);
               Log.d("DRINKORDER","Finished" + ingredientCount);

            }
        }
    };
    
    public void notifyOfPour(int count,int ingredientId){
		Intent i = new Intent(INGREDIENT_INTENT);
        i.putExtra(DrinkOrderActivity.DATA_EXTRA_INGRED, ingredientId);
        i.putExtra(DrinkOrderActivity.DATA_EXTRA_ORDER, count);
        sendBroadcast(i);
    }
    
    public int getIdHack(int i){
    	switch(i){
    		case 1:
    			return R.id.step_2_view;
    		case 2:
    			return R.id.step_3_view;
    		case 3:
    			return R.id.step_4_view;
    		case 4:
    			return R.id.step_5_view;
    		default:
    			return 1;
    	}
    }
}
