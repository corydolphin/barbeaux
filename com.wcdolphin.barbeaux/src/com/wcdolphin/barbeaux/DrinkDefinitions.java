package com.wcdolphin.barbeaux;

import java.util.HashMap;


public class DrinkDefinitions {
    public enum Ingredient{
    	COKE(1, "Coke"),
    	SPRITE(2, "Sprite"),
    	ORANGEJUICE(3, "Orange Juice"),
    	TEQUILA(4, "Orange Juice"),
    	RUM(5, "Orange Juice"),
    	GIN(6, "Gin");
    	
    	public String name;
    	public int pin;
    	Ingredient(int pin, String name){
    		this.name = name;
    		this.pin = pin;
		}
    }
    public enum Drink{
    	COKE(R.drawable.coke,"Coke","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum",new HashMap<Ingredient,Double>(){{put(Ingredient.COKE,1.0);}}),
    	SPRITE(R.drawable.sprite,"Sprite","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum",new HashMap<Ingredient,Double>(){{
    		put(Ingredient.SPRITE,1.0);
		}}),
    	ORANGINA(R.drawable.orangina,"Orangina","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum",new HashMap<Ingredient,Double>(){{
    		put(Ingredient.SPRITE,0.7);
    		put(Ingredient.ORANGEJUICE,0.3); 
		}}),
    	ORANGEJUICE(R.drawable.orange_juice,"Orange Juice","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum",new HashMap<Ingredient,Double>(){{
    		put(Ingredient.ORANGEJUICE,1.0);
		}}),
    	LONGISLANDICETEA(R.drawable.long_island_ice_tea,"Long Island Iced Tea","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum",new HashMap<Ingredient,Double>(){{
    		put(Ingredient.COKE,0.25);
    		put(Ingredient.RUM,0.25);
    		put(Ingredient.GIN,0.25);
    		put(Ingredient.TEQUILA,0.25);
    	}}),
    	ORANGEBLOSSOM(R.drawable.orange_blossom,"Orange Blossom","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum",new HashMap<Ingredient,Double>(){{
    		put(Ingredient.SPRITE,0.33);
    		put(Ingredient.ORANGEJUICE,0.33);
    		put(Ingredient.GIN,0.33);
		}}),
    	RUMANDCOKE(R.drawable.rum_and_coke,"Rum and Coke","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum",new HashMap<Ingredient,Double>(){{
    		put(Ingredient.COKE,0.5);
    		put(Ingredient.RUM,0.5);
		}}),
    	RUMFIZZ(R.drawable.rum_fizz,"Rum Fizz","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum",new HashMap<Ingredient,Double>(){{
    		put(Ingredient.SPRITE,0.75);
    		put(Ingredient.RUM,0.25);
		}}),
    	GINFIZZ(R.drawable.gin_fizz,"Gin Fizz","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum",new HashMap<Ingredient,Double>(){{
    		put(Ingredient.SPRITE,0.75);
    		put(Ingredient.GIN,0.25);
    	}}),
    	SCREWDRIVER(R.drawable.screwdriver,"Screwdriver","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum",new HashMap<Ingredient,Double>(){{
    		put(Ingredient.ORANGEJUICE,0.75);
    		put(Ingredient.RUM,0.75);
		}}),
    	TEQUILASUNRISE(R.drawable.tequila_sunrise,"Tequila Sunrise","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum",new HashMap<Ingredient,Double>(){{
    		put(Ingredient.SPRITE,0.50);
    		put(Ingredient.TEQUILA,0.25);
    		put(Ingredient.RUM,0.25);
		}}),
    	TEQUILAPOPPER(R.drawable.tequila_popper,"Tequila Popper","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum",new HashMap<Ingredient,Double>(){{
    		put(Ingredient.SPRITE,0.75);
    		put(Ingredient.TEQUILA,0.25);
    		
    	}});
    	
    	public final Integer img;
    	public final String name;
    	public final String bio;
    	public final HashMap<Ingredient,Double> items;
    	 Drink(Integer img, String name,String bio, HashMap<Ingredient,Double> items) {
            this.img = img;
            this.name = name;
            this.items = items;
            this.bio = bio;
        }
    };
}
