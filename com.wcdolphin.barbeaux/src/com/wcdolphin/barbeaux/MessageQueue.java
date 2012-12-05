package com.wcdolphin.barbeaux;

import java.util.ArrayList;
import com.wcdolphin.barbeaux.DrinkDefinitions.Drink;
public class MessageQueue extends ArrayList<String> {

	/**
	 * @param args
	 */

	public String dequeue(){
		if(this.size() <= 0){
			return null;
		}
		return this.remove(0);
	}

	public boolean enqueue(String d){
		return this.add(d);
	}
	
	public String peek(){
		if(this.size() <= 0){
			return null;
		}
		return this.get(0);
	}
}
