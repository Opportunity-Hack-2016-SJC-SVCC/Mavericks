package foodrescue.com.food.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import foodrescue.com.food.Restaurant;
import foodrescue.com.food.User; 

public class MongoMain {

	
	public boolean login(User user) {
		MongoUser mongoUser = null;
		try {
			mongoUser = new MongoUser();
			User returnParameter = mongoUser.login(user.getPhone(), user.getPassword());
			System.out.println("returnParameter-----" + returnParameter);
			if (returnParameter == null) {
				return false;
			} else {
				return true;

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			mongoUser.closeConnection();
		}
		return true;
	}

	public boolean login(Restaurant rest) {
		MongoRestaurant mongoRestaurant = null;
		try {
			mongoRestaurant = new MongoRestaurant();
			Restaurant returnParameter = mongoRestaurant.login(rest.getPhone(), rest.getPassword());
			
			System.out.println("returnParameter-----" + returnParameter);

			if (returnParameter == null) {
				return false;
			} else {
				return true;

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			mongoRestaurant.closeConnection();
		}
		return true;
	}

	public boolean insertData(Restaurant restaurant) {
		System.out.println("hello");
		MongoRestaurant mongoRest = null;
		try {
			mongoRest = new MongoRestaurant();
			if (mongoRest.insertData(restaurant)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			mongoRest.closeConnection();
		}
		return false;
	}

	public void insertData(User user) {
		System.out.println("hello");
		MongoUser mongoUser = null;
		try {
			mongoUser = new MongoUser();
			mongoUser.insertData(user);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			mongoUser.closeConnection();
		}
	}

	public void updateData(User user) {
		MongoUser mongoUser = null;
		try {
			mongoUser = new MongoUser();
			mongoUser.updateData(user);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			mongoUser.closeConnection();
		}
	}

	public void updateRestaurantData(String phone, String date) {
		MongoRestaurant mongoRestaurant = null;
		try {
			mongoRestaurant = new MongoRestaurant();
			Restaurant rest = new Restaurant();
			rest.setDate(date);
			mongoRestaurant.updateData(rest);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			mongoRestaurant.closeConnection();
		}

	}

	public Date convertDateToString(String dateStr) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");

		try {

			Date date = formatter.parse(dateStr);
			System.out.println(date);
			System.out.println(formatter.format(date));
			return date;

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Retrieve Restaurant List
	public List<Restaurant> getRestaurantData(String latitude, String longitude) {
		MongoRestaurant restaurant = null;
		List<Restaurant> rests = new ArrayList<>();
		try {
			restaurant = new MongoRestaurant();
			rests = restaurant.retrieveData(latitude, longitude);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			restaurant.closeConnection();
		}

		return rests;
	}

	// Retrieve User List
	public List<String> getUserData(String latitude, String longitude) {
		MongoUser mongoUser = null;
		List<String> users = new ArrayList<>();
		try {
			mongoUser = new MongoUser();
			users = mongoUser.retrieveData(latitude, longitude);
		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			mongoUser.closeConnection();
		}
		return users;
	} 
	
}
