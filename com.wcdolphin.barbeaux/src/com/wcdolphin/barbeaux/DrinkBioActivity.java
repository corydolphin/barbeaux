package com.wcdolphin.barbeaux;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.wcdolphin.barbeaux.DrinkDefinitions.Drink;

public class DrinkBioActivity extends Activity {
	final static String DATA_EXTRA = "wcdolphin.DrinkBioActivity.Extra";
	
	private Integer drinkNumber = 0;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Bundle extras = getIntent().getExtras();
        setContentView(R.layout.drinkbio);
        
        final int dNumber = (Integer)extras.get(DATA_EXTRA);
        drinkNumber = dNumber;
        Drink drink = Drink.values()[dNumber];
		TextView textView = (TextView)findViewById(R.id.grid_item_label);
		textView.setText(drink.bio);
		
		// set image based on selected text
		ImageView imageView = (ImageView)findViewById(R.id.grid_item_image);
		imageView.setImageResource(drink.img);

		final Button cancelButton = (Button)findViewById(R.id.drink_bio_cancel);
		
		cancelButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
		final Button orderButton = (Button)findViewById(R.id.drink_bio_order);
		
		orderButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent dIntent = new Intent(ArduinoCommunicatorActivity.DRINK_ORDER_INTENT);
                dIntent.putExtra(DrinkOrderActivity.DATA_EXTRA, drinkNumber);
                sendBroadcast(dIntent);
                finish();
            }
        });
		
    }
}
