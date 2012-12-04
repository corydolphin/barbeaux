package com.wcdolphin.barbeaux;

import java.util.ArrayList;
import com.wcdolphin.barbeaux.DrinkDefinitions.Drink;
public class DrinkQueue extends ArrayList<Drink> {

	/**
	 * @param args
	 */

	public Drink dequeue(){
		if(this.size() <= 0){
			return null;
		}
		return this.remove(0);
	}

	public boolean enqueue(Drink d){
		return this.add(d);
	}
	
	public Drink peek(){
		if(this.size() <= 0){
			return null;
		}
		return this.get(0);
	}
}
