package com.wcdolphin.barbeaux;

import java.util.HashMap;


public class DrinkDefinitions {
    public enum Ingredient{
    	COKE("Coke"),
    	SPRITE("Sprite"),
    	ORANGEJUICE("Orange Juice"),
    	TEQUILA("Orange Juice"),
    	RUM("Orange Juice"),
    	GINGGERALE("Gingerale"),
    	GIN("Gin");
    	
    	public String name;
    	Ingredient(String name){
    		this.name = name;
		}
    }
    public enum Drink{
    	COKE(R.drawable.coke,"Coke",new HashMap<Ingredient,Double>(){{put(Ingredient.COKE,1.0);}}),
    	SPRITE(R.drawable.sprite,"Sprite",new HashMap<Ingredient,Double>(){{
    		put(Ingredient.SPRITE,1.0);
		}}),
    	ORANGINA(R.drawable.orangina,"Orangina",new HashMap<Ingredient,Double>(){{
    		put(Ingredient.SPRITE,0.7);
    		put(Ingredient.ORANGEJUICE,0.3); 
		}}),
    	ORANGEJUICE(R.drawable.orange_juice,"Orange Juice",new HashMap<Ingredient,Double>(){{
    		put(Ingredient.ORANGEJUICE,1.0);
		}}),
    	LONGISLANDICETEA(R.drawable.long_island_ice_tea,"Long Island Iced Tea",new HashMap<Ingredient,Double>(){{
    		put(Ingredient.COKE,0.25);
    		put(Ingredient.RUM,0.25);
    		put(Ingredient.GIN,0.25);
    		put(Ingredient.TEQUILA,0.25);
    	}}),
    	ORANGEBLOSSOM(R.drawable.orange_blossom,"Orange Blossom",new HashMap<Ingredient,Double>(){{
    		put(Ingredient.SPRITE,0.33);
    		put(Ingredient.ORANGEJUICE,0.33);
    		put(Ingredient.GIN,0.33);
		}}),
    	RUMANDCOKE(R.drawable.rum_and_coke,"Rum and Coke",new HashMap<Ingredient,Double>(){{
    		put(Ingredient.COKE,0.5);
    		put(Ingredient.RUM,0.5);
		}}),
    	RUMFIZZ(R.drawable.rum_fizz,"Rum Fizz",new HashMap<Ingredient,Double>(){{
    		put(Ingredient.SPRITE,0.75);
    		put(Ingredient.RUM,0.25);
		}}),
    	GINFIZZ(R.drawable.gin_fizz,"Gin Fizz",new HashMap<Ingredient,Double>(){{
    		put(Ingredient.SPRITE,0.75);
    		put(Ingredient.GIN,0.25);
    	}}),
    	SCREWDRIVER(R.drawable.screwdriver,"Screwdriver",new HashMap<Ingredient,Double>(){{
    		put(Ingredient.ORANGEJUICE,0.75);
    		put(Ingredient.RUM,0.75);
		}}),
    	TEQUILASUNRISE(R.drawable.tequila_sunrise,"Tequila Sunrise",new HashMap<Ingredient,Double>(){{
    		put(Ingredient.SPRITE,0.50);
    		put(Ingredient.TEQUILA,0.25);
    		put(Ingredient.SPRITE,0.25);
		}}),
    	TEQUILAPOPPER(R.drawable.tequila_popper,"Tequila Popper",new HashMap<Ingredient,Double>(){{
    		put(Ingredient.SPRITE,0.75);
    		put(Ingredient.TEQUILA,0.25);
    		
    	}});
    	
    	public final Integer img;
    	public final String name;
    	public final HashMap<Ingredient,Double> items;
    	 Drink(Integer img, String name, HashMap<Ingredient,Double> items) {
            this.img = img;
            this.name = name;
            this.items = items;
        }
    };
}
