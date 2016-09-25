package foodrescue.com.food;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import foodrescue.com.food.repository.MongoMain;
import foodrescue.com.food.repository.MongoUser;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;

@Path("/res")
public class Controller {
	@POST 
	@Path("/create")	
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.APPLICATION_JSON) 
	public Response createRestaurantInJSON(Restaurant e) throws Exception {
		System.out.println("Got Request....."+e.getName());
		MongoMain m=new MongoMain();
		m.insertData(e); 
		return Response.ok().entity("").build(); 
	}
	
	@POST
	@Path("/login")	
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response loginInJSON(Restaurant e) throws Exception {
		MongoMain m=new MongoMain();
		boolean access=m.login(e); 
		if(access)
			return Response.ok().entity("").build();
		else
		 return Response.serverError().entity("").build();  
	} 
	
	@POST
	@Path("/notify")	
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response notifyInJSON(Restaurant e) throws Exception { 
		return Response.ok().entity("").build(); 
	}
	
	// User
		@POST
		@Path("/userCreate")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response createUserInJSON(User u) throws Exception {
			System.out.println("Helloooo user");
			MongoMain m = new MongoMain();
			m.insertData(u);
			return Response.ok().entity("").build();
		}

		@POST
		@Path("/userLogin")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response loginInJSON(User u) throws Exception {
			MongoUser m = new MongoUser();
			User access = m.login(u.getPhone(),u.getPassword());  
			if (access!=null)
				return Response.ok().entity("").build();
			else
				return Response.serverError().entity("").build();
		}
 
		@POST
		@Path("/restaurantList")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response getRestaurants(Location l) throws Exception {
			MongoMain mongoMain = new MongoMain();
			List<Restaurant> restaurants = mongoMain.getRestaurantData(l.getLatitude(), l.getLongitude());
			System.out.println("restaurants"+restaurants.size());
			if (restaurants.size()> 0) { 
				return Response.ok().entity(restaurants).build();  
			} else {
				return Response.serverError().entity("").build();
			}
		}
		
		@POST
		@Path("/pushNotifications")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response sendPushNotification(Location l) throws Exception {
			MongoMain mongoMain = new MongoMain();
			List<String> users = mongoMain.getUserData(l.getLatitude(),l.getLongitude()); 
			mongoMain.updateRestaurantData(l.getPhone(), l.getDate()); 
			
			//push notification			
			SmsSender notification=new SmsSender();
			notification.sendSmsNotification();
			
			if (users.size()> 0) { 
				return Response.ok().entity(users).build();
			} else {
				return Response.serverError().entity("").build(); 
			}

		}

	
}
