package foodrescue.com.food;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import foodrescue.com.food.repository.MongoMain;

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
	
	
}
