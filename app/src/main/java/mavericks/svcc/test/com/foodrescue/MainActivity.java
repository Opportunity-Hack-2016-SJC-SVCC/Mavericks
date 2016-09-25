package mavericks.svcc.test.com.foodrescue;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
 Button registerBtn;
    Button loginBtn;
    EditText phnTxt;
    EditText passwd;
    String phoneId;
    String password;
    SwitchCompat switchC;
    private final String TAG = "Login Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerBtn = (Button) findViewById(R.id.register);
        registerBtn.setOnClickListener(this);
        phnTxt = (EditText) findViewById(R.id.phone);
        passwd = (EditText) findViewById(R.id.password);
        switchC = (SwitchCompat)findViewById(R.id.switchBtn);
        switchC.setChecked(false);
        switchC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    switchC.setText("Donor");
                }else
                    switchC.setText("Consumer");

            }
        });

    }



    public void login(View view){
      //  Datentime fragment=new Datentime();
        phoneId = phnTxt.getText().toString();
        password = phnTxt.getText().toString();
        Intent intent;
        if(switchC.isChecked()){
            intent = new Intent(MainActivity.this, DonationActivity.class);
        }
         else{
            intent = new Intent(MainActivity.this, SuggestLocationsActivity.class);
        }

        startActivity(intent);
    }
    @Override
    public void onClick(View view) {
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
            jsonParams.put("name", "d");
            jsonParams.put("address", "dj");

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

}

