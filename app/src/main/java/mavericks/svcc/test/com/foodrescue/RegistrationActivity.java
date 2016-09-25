package mavericks.svcc.test.com.foodrescue;

import android.app.Activity;
import android.content.Intent;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener  {
    GoogleApiClient mGoogleApiClient;
    FloatingActionButton btnLocation;
    EditText userNameTxt;
    EditText phoneNumberTxt;
    Button btnSubmit;
    String phone = "1234567890";
    String userName ="savvy";
    String password ;
    String address;
    String city;
    String zip;
    String confirmPassword;
    EditText passwordTxt;
    EditText confirmPasswordTxt;
    String androidId;
    EditText addressTxt;
    EditText zipTxt;
    EditText cityTxt;
    UserProfile userProfile = null;
    String latitude;
    String longitude;

    private static final int PLACE_PICKER_REQUEST = 1;
    private static final String TAG = "Registration Activity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        btnLocation = (FloatingActionButton) findViewById(R.id.locationSelect);
        userNameTxt = (EditText) findViewById(R.id.userName);
        phoneNumberTxt = (EditText) findViewById(R.id.phoneNumber);
        btnSubmit = (Button) findViewById(R.id.submit);
        passwordTxt = (EditText)findViewById(R.id.password);
        confirmPasswordTxt = (EditText)findViewById(R.id.confirmPassword);
        addressTxt = (EditText)findViewById(R.id.address);
        zipTxt = (EditText)findViewById(R.id.zipcode);
        cityTxt = (EditText)findViewById(R.id.city);
        btnLocation.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
//       ActionBar actionBar = getActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);

       androidId =
                Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
        Log.i(TAG, androidId +"android id");



    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.locationSelect: // location picker
                Log.i(TAG, "Location ");
                mGoogleApiClient = new GoogleApiClient
                        .Builder(this)
                        .addApi(Places.GEO_DATA_API)
                        .addApi(Places.PLACE_DETECTION_API)
                        .build();

                mGoogleApiClient.connect();
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

                try {
                    startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
                    System.out.println( this + "  2 ");

                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                } catch (Exception e){
                    System.out.println("Error");
                    e.printStackTrace();
                }

                break;
            case R.id.submit: //save the form
                if(userNameTxt.getText() != null)
                    userName = userNameTxt.getText().toString();

                phone = phoneNumberTxt.getText().toString();
                password = passwordTxt.getText().toString();

                confirmPassword = confirmPasswordTxt.getText().toString();
                if(!password.equals(confirmPassword)){
                    confirmPasswordTxt.setError("Passwords should be same");

                }
//                RegisterAsyncTask rat = new RegisterAsyncTask();
//                rat.execute(userProfile);
                uploadData();

                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                Place place = PlacePicker.getPlace(this, data);
                Log.i(TAG, "address "+place.getAddress());
                String lAddress = place.getAddress().toString();
                String [] lAddressArr = lAddress.split(",");
                if(lAddressArr.length > 0){
                    address = lAddressArr[0];
                    addressTxt.setText(address);
                    city = lAddressArr[1];
                    cityTxt.setText(city);
                    String [] lzipArr = lAddressArr[2].split(" ");
                   if( lzipArr.length > 0){
                       zip = lzipArr[1];
                       zipTxt.setText(zip);
                   }

                }
                LatLng latLng = place.getLatLng();
                latitude = Double.toString(latLng.latitude);
                longitude = Double.toString(latLng.longitude);


                Log.i(TAG,"Latitude"+latLng);

                String urlEncoded= "";
                try {
                    Log.i("Address",place.getAddress().toString());
                    urlEncoded = URLEncoder.encode(place.getAddress().toString(), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                String latLngStr =  latLng.toString().substring(10).replace(")", "");

            }
        }
    }
    void uploadData(){
        Log.d(TAG, "upload start");
        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("name", userName);
            jsonParams.put("address", address);
            jsonParams.put("city",city);
            jsonParams.put("zip",zip);
            jsonParams.put("password",password);
            jsonParams.put("phone",phone);
            jsonParams.put("latitude",latitude);
            jsonParams.put("longitude",longitude);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        StringEntity entity = null;
        try {
            entity = new StringEntity(jsonParams.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HttpCalls.post( this, "http://192.168.90.249:8080/foodRescue/res/create", entity, "application/json",
                new TextHttpResponseHandler(){
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        Log.d(TAG, "faileddd "+statusCode);
                        Toast.makeText(getApplicationContext(), "Error. Please try again later.",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        Log.d(TAG, "successss "+statusCode);
                        Toast.makeText(getApplicationContext(), "Account created successfully",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                    }
                });

    }

    void uploadDataN(){
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "http://192.168.90.249:8080/foodRescue/res/create", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "response is"+response.toString());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("user","sv");

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/json");
                return params;
            }

        };
        queue.add(stringRequest);
    }
    public interface RegsitrationResponseListener {
        public void requestStarted();
        public void requestCompleted();
        public void requestEndedWithError(VolleyError error);
    }
}
