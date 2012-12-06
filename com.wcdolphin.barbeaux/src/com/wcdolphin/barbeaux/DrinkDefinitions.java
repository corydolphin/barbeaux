package com.wcdolphin.barbeaux;

import java.util.HashMap;


public class DrinkDefinitions {
    public enum Ingredient{
    	COKE(0, "Coke"),
    	SPRITE(1, "Sprite"),
    	ORANGEJUICE(2, "Orange Juice"),
    	TEQUILA(3, "Tequila"),
    	RUM(4, "Rum"),
    	GIN(5, "Gin");
    	
    	public String name;
    	public int pin;
    	Ingredient(int pin, String name){
    		this.name = name;
    		this.pin = pin;
		}
    }
    public enum Drink{
    	COKE(R.drawable.coke,"Coke","The classic cold, cocacine free Cola that got America through many a depression.",new HashMap<Ingredient,Double>(){{put(Ingredient.COKE,1.0);}}),
    	SPRITE(R.drawable.sprite,"Sprite","Are you an interesting person? Do you like flavor in your beverage? If you answered \"no\" to both of these questions, then this beverage is perfect for you",new HashMap<Ingredient,Double>(){{
    		put(Ingredient.SPRITE,1.0);
		}}),
    	ORANGINA(R.drawable.orangina,"Orangina","Looking to feel classy, blend in, but still stay sober? While water works, Orangina is somehow more exciting. And fattening. Seriously calorie count.",new HashMap<Ingredient,Double>(){{
    		put(Ingredient.SPRITE,0.7);
    		put(Ingredient.ORANGEJUICE,0.3); 
		}}),
    	ORANGEJUICE(R.drawable.orange_juice,"Orange Juice","Ahh Florida. Your grandmother probably lived there, and recently people from the state mattered in an election. Apparently.",new HashMap<Ingredient,Double>(){{
    		put(Ingredient.ORANGEJUICE,1.0);
		}}),
    	LONGISLANDICETEA(R.drawable.long_island_ice_tea,"Long Island Iced Tea","Much like its inventor, Eerik \"The Tiniest of Tim's\" Helmic, you will not be able to bench 300lbs after drinking this. This beverage is in fact three-quarters alcohol, and one quarter run off from the Long Island Railroad.",new HashMap<Ingredient,Double>(){{
    		put(Ingredient.COKE,0.25);
    		put(Ingredient.RUM,0.25);
    		put(Ingredient.GIN,0.25);
    		put(Ingredient.TEQUILA,0.25);
    	}}),
    	ORANGEBLOSSOM(R.drawable.orange_blossom,"Orange Blossom","This is a man's man's drink which comes in a suprisingly feminine container. ",new HashMap<Ingredient,Double>(){{
    		put(Ingredient.SPRITE,0.33);
    		put(Ingredient.ORANGEJUICE,0.33);
    		put(Ingredient.GIN,0.33);
		}}),
    	RUMANDCOKE(R.drawable.rum_and_coke,"Rum and Coke","Rum and Coke, the classic staple. It says: \"I'm not really into mixed drinks, but tonight's not a beer night\"",new HashMap<Ingredient,Double>(){{
    		put(Ingredient.COKE,0.75);
    		put(Ingredient.RUM,0.25);
		}}),
    	RUMFIZZ(R.drawable.rum_fizz,"Rum Fizz","First popularized by the popular TV comic, Archer, the Rum Fizz is well known for giving the natives of the island their gorgeous, fizzy hair, the Rum Fizz is a common beverage served on Hoûrè Island in New Guinea. Which is a real place.",new HashMap<Ingredient,Double>(){{
    		put(Ingredient.SPRITE,0.75);
    		put(Ingredient.RUM,0.25);
		}}),
    	GINFIZZ(R.drawable.gin_fizz,"Gin Fizz","When Theo Thompson was born, he was immediately thrown into a dumpster. The only thing he could survive off of was cigarette butts and the run off from the local gin factory. Before Theo died valiantly in the Civil War, in which he fought for both the Confederates, and the Union, he invented the Gin Fizz, made from Ye Olde Sprite, and water (Gin). He died a veteran of both the Civil War and the French and Indian War.",new HashMap<Ingredient,Double>(){{
    		put(Ingredient.SPRITE,0.75);
    		put(Ingredient.GIN,0.25);
    	}}),
    	SCREWDRIVER(R.drawable.screwdriver,"Screwdriver","The common household screwdriver comes in many different sizes and shapes. This is not one of them. Ironically, ordering a Screwdriver really sets you up to get hammered.",new HashMap<Ingredient,Double>(){{
    		put(Ingredient.ORANGEJUICE,0.75);
    		put(Ingredient.RUM,0.25);
		}}),
    	TEQUILASUNRISE(R.drawable.tequila_sunrise,"Tequila Sunrise","The Tequila Sunrise is known to cause snow blindness in Phoenix, Arizona. Wanted for murder in Vegas. Known to cause prostate cancer in women, and pregnancy in men. Rather than order this drink, you should probably call it a night, call a loved one and just let your presence be known.",new HashMap<Ingredient,Double>(){{
    		put(Ingredient.SPRITE,0.50);
    		put(Ingredient.TEQUILA,0.25);
    		put(Ingredient.RUM,0.25);
		}}),
    	TEQUILAPOPPER(R.drawable.tequila_popper,"Tequila Popper","Like a PartyPopper in your mouth, this drink explodes into a confetti of somehow smaller Tequila Poppers, creating an incredibly awkward Russian Doll Situation, rarely known to end.",new HashMap<Ingredient,Double>(){{
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
