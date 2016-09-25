package foodrescue.com.food.repository;

import java.util.ArrayList;
import java.util.List;

import foodrescue.com.food.Restaurant; 
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class MongoRestaurant {

	private MongoConnection connection = null;
	private MongoClient client = null;
	private DB db = null;
	private DBCollection col = null;;

	
	public MongoRestaurant(){
		getConnection();
	}
	
	
	public DB getDB() {
		this.db = client.getDB(Constants.dbName);
		return this.db;
	}

	public boolean insertData(Restaurant restaurant) {
		try {
			System.out.println("Going to insert data"); 
			BasicDBObject document = new BasicDBObject();
			document.put("name", restaurant.getName());
			document.put("address", restaurant.getAddress()); 
			document.put("city", restaurant.getCity()); 
			document.put("zip", restaurant.getZip()); 
			double[] locs = { Double.parseDouble(restaurant.getLatitude()),
					Double.parseDouble(restaurant.getLongitude()) };
			document.put("locs", locs);
			document.put("phone", restaurant.getPhone());
			document.put("password", restaurant.getPassword());
			System.out.println("Got data"); 
			this.col.insert(document);
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Restaurant login(String phone, String password) {
		try {
			BasicDBObject document = new BasicDBObject();
			Restaurant restaurant = new Restaurant();
			document.put("phone", phone);
			document.put("password", password);
			DBCursor cursor = this.col.find(document);
			if (cursor.size() > 1 || cursor.size() == 0) {
				System.out.println("HII");
				return null;
			} else {
				/*
				DBObject t = cursor.next();
				restaurant.setName(t.get("name").toString());
				restaurant.setPhone(t.get("phone").toString());
				restaurant.setPassword(t.get("password").toString());
				restaurant.setAddress(t.get("location").toString()); */ 
				return restaurant; 
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean updateData(Restaurant restaurant) {
		return false;
	}

	// Find Restaurants
	public List<Restaurant> retrieveData(String latitude, String longitude) {
		List<Restaurant> restaurants = new ArrayList<>();

		BasicDBObject myCmd = new BasicDBObject();
		myCmd.append("geoNear", "data");
		double[] loc = { Double.parseDouble(latitude), Double.parseDouble(longitude) };
		
		myCmd.append("near", loc);
		myCmd.append("spherical", true);
		myCmd.append("maxDistance", (double) 2500 / 6378137);
		myCmd.append("distanceMultiplier", 6378137);
		
		System.out.println(myCmd);

		DBCursor cursor = this.col.find(myCmd);
		
		Restaurant restaurant = null;
		while (cursor.hasNext()) {
			DBObject t = cursor.next();
			restaurant = new Restaurant();
			restaurant.setName(t.get("name").toString());
			restaurant.setAddress(t.get("address").toString());
			restaurants.add(restaurant);
		}

		return restaurants;

	}

	public boolean removeData(String restId) {
		
		return false;
	}

	public void closeConnection() {
		this.connection.closeConnection();
	}

	public void getConnection() {
		connection = new MongoConnection();
		client = connection.getConnection();
		this.db = getDB();
		this.col = this.db.getCollection("restaurants");
		
	}

}
