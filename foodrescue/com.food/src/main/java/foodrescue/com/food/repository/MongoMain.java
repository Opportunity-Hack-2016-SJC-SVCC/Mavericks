package foodrescue.com.food.repository;

import foodrescue.com.food.Restaurant; 

public class MongoMain {
	public void insertData (Restaurant restaurant) {
		MongoRestaurant mongoRestaurant=new MongoRestaurant();		
		mongoRestaurant.insertData(restaurant);
	}
	
	public boolean login (Restaurant restaurant) {
		MongoRestaurant mongoRestaurant=new MongoRestaurant();		
		Restaurant returnParameter=mongoRestaurant.login(restaurant.getPhone(),restaurant.getPassword());
		System.out.println("returnParameter-----"+returnParameter);
		if(returnParameter==null){
			return false; 
		}
		else{
			return true; 
		} 
	}
}
