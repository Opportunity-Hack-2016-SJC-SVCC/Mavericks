package foodrescue.com.food;

import java.io.IOException;

import com.google.android.gcm.server.InvalidRequestException;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender; 

public class SendPush {
		void sendNotification() {
		final String GCM_API_KEY = "AIzaSyCbD0iUHSOmilZefKbzadNjwpcbJoYKUZk";
	    final int retries = 3;
	    final String notificationToken = "APA91bHI1hafQ4u_XUkn7tDJqvkXkgyewlBHkD6ES6FXubfvf3C2Se-c_eaHO1YARvr43nVaqP0rt5Lk7A2o06TCafoLbBxshzTlUCspj5VEHsGCeyXxk15XRhDMg9fJG3NFvGKn9agI";
	    Sender sender = new Sender(GCM_API_KEY);	   
	    Message msg = new Message.Builder().build(); 
	    try {
            Result result = sender.send(msg, notificationToken, retries);
            if (!(result.getErrorCodeName().equals(""))) {
               System.out.println("GCM Notification is sent successfully");
               // return true;
            }
            System.out.println("Error occurred while sending push notification :" + result.getErrorCodeName());
	    } catch (InvalidRequestException e) {
	    	 System.out.println("Invalid Request"+e);
	    	 e.printStackTrace();
	    } catch (IOException e) {
	    	 System.out.println("IO Exception"+e);
	    	 e.printStackTrace();
	    }
	    //return false;

	}

}
