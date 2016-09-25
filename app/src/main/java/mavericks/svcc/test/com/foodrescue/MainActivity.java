package mavericks.svcc.test.com.foodrescue;

import android.content.Context;
import android.content.Intent;


import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView registerBtn;
   // Button loginBtn;
    AppCompatButton loginBtn;
    EditText phnTxt;
    EditText passwd;
    String phoneId;
    String password;
    SwitchCompat switchC;
    private final String TAG = "Login Activity";
    Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerBtn = (TextView) findViewById(R.id.register);
        registerBtn.setOnClickListener(this);
        phnTxt = (EditText) findViewById(R.id.phone);
        passwd = (EditText) findViewById(R.id.password);
        switchC = (SwitchCompat) findViewById(R.id.switchBtn);
        switchC.setChecked(false);
        switchC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    switchC.setText("Donor");
                } else
                    switchC.setText("Consumer");

            }
        });

    }


    public void login(View view) {
        //  Datentime fragment=new Datentime();
        phoneId = phnTxt.getText().toString();
        password = phnTxt.getText().toString();
        Intent intent;
        if (switchC.isChecked()) {
            intent = new Intent(MainActivity.this, DonationActivity.class);
        } else {
            LocationManager locationManager = (LocationManager) getApplicationContext()
                    .getSystemService(Context.LOCATION_SERVICE);
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                //return;
            }
             location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            Log.d(TAG, Double.toString(location.getLatitude())+"  longtiude is "+location.getLongitude());
            uploadData();
            return;
        }

        startActivity(intent);
    }
    @Override
    public void onClick(View view) {
       // String android_id = Settings.Secure.getString(this.getContentResolver(),
        //        Settings.Secure.ANDROID_ID);
       //  JSONObject isData=
       // Log.d("Android","Android ID : "+android_id);
       switch (view.getId()){
           case R.id.register:
            Intent i = new Intent(this, RegistrationActivity.class);
               startActivity(i);
               break;
        }
    }

    void uploadData(){
        Log.d(TAG, "upload start");
        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("latitude",Double.toString(location.getLatitude()));
            jsonParams.put("longitude", Double.toString(location.getLongitude()));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        StringEntity entity = null;
        try {
            entity = new StringEntity(jsonParams.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //Recieve list of people serving food.
        HttpCalls.post( this, "http://726317ae.ngrok.io/foodRescue/res/restaurantList", entity, "application/json",
                new JsonHttpResponseHandler(){
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        Log.d(TAG, "faileddd "+statusCode);
                       // Toast.makeText(getApplicationContext(), "Error. Please try again later.",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray responseString) {
                        Log.d(TAG, "successss "+statusCode + "response "+responseString);
                        for(int i = 0; i< responseString.length(); i++){
                            JSONObject object = null;
                            try {
                                object = responseString.getJSONObject(i);
                                LocationModel lm = new LocationModel();
                             //  Log.d(TAG, "phone is "+ object.get("phone"));
                               // if(object)
                                lm.setAddress(object.get("address").toString());
                                lm.setName(object.get("name").toString());

                                lm.setPhone(object.get("phone").toString());
                                LocationsSingleton.getInstance().setLocation(lm);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    //    Toast.makeText(getApplicationContext(), "",Toast.LENGTH_SHORT).show();
//                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
//                        startActivity(i);
                       Intent intent = new Intent(MainActivity.this, SuggestLocationsActivity.class);
                        startActivity(intent);
                    }
                });

    }

}

