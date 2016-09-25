package foodrescue.com.food;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.resource.instance.Message;

public class SmsSender {
	 /* Find your sid and token at twilio.com/user/account */
    public static final String ACCOUNT_SID = "AC79ab339b4e49f76c3b2328d181a3da40";
    public static final String AUTH_TOKEN = "1bd35ff6def45f58221c790a54c8c41f";

    public void sendSmsNotification() throws TwilioRestException {

        TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);

        Account account = client.getAccount();
        MessageFactory messageFactory = account.getMessageFactory();
        List<NameValuePair> params = new ArrayList<NameValuePair>();  
        params.add(new BasicNameValuePair("To", "+14087727604")); // Replace with a valid phone number for your account.
        params.add(new BasicNameValuePair("From", "+16283333143")); // Replace with a valid phone number for your account.
        params.add(new BasicNameValuePair("Body", "Paypal inc, 2161 North first street, San Jose,CA-95131,+1234455666"));
        Message sms = messageFactory.create(params);
    } 
}
