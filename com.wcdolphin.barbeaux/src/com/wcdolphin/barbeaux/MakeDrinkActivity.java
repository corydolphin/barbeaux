package com.wcdolphin.barbeaux;

import com.wcdolphin.barbeaux.DrinkDefinitions.Drink;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MakeDrinkActivity extends Activity {
	final static String DATA_EXTRA = "wcdolphin.MakeDrinkActivity.Extra";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        setContentView(R.layout.drinkbio);
        
        int dNumber = (Integer)extras.get(DATA_EXTRA);
        Drink drink = Drink.values()[dNumber];
		TextView textView = (TextView)findViewById(R.id.grid_item_label);
		textView.setText(drink.name);
		
		// set image based on selected text
		ImageView imageView = (ImageView)findViewById(R.id.grid_item_image);
		imageView.setImageResource(drink.img);


    }
}
