package mavericks.svcc.test.com.foodrescue;

import android.app.DatePickerDialog;


import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Created by savani on 9/25/16.
 */
public class DonationActivity extends AppCompatActivity implements View.OnClickListener{
EditText cal ;

    private String format = "";

    //UI References
    private EditText fromDateEtxt;
    private EditText toDateEtxt;

    private TimePicker fromTimeExt;
    private TimePicker toTimeExt;

    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;

    private SimpleDateFormat dateFormatter;
    private String  fromDate;
    private String toDate;

    private String  fromTime;
    private String toTime;
    private Button btnSubmit;
    private final String  TAG = "Donation activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_view);
        fromDateEtxt = (EditText) findViewById(R.id.calendarFrom);
        toDateEtxt = (EditText) findViewById(R.id.calendarTo);

        fromTimeExt= (TimePicker) findViewById(R.id.fromTime);
        toTimeExt= (TimePicker) findViewById(R.id.toTime);
        //time = (TextView) findViewById(R.id.textView1);
        btnSubmit = (Button) findViewById(R.id.submit);
        btnSubmit.setOnClickListener(this);



        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        findViewsById();

        setDateTimeField();
        setfromTimeField();
        settoTimeField();



    }

    private void findViewsById() {
        fromDateEtxt = (EditText) findViewById(R.id.calendarFrom);
        fromDateEtxt.setInputType(InputType.TYPE_NULL);
        fromDateEtxt.requestFocus();

        toDateEtxt = (EditText) findViewById(R.id.calendarTo);
        toDateEtxt.setInputType(InputType.TYPE_NULL);
    }
private void setfromTimeField()
{

    fromTimeExt.setOnClickListener(this);

    Calendar  calendar = Calendar.getInstance();

    int hour = calendar.get(Calendar.HOUR_OF_DAY);
    int min = calendar.get(Calendar.MINUTE);
     fromTime= showTime(hour, min);

}

    private void settoTimeField(){

        toTimeExt.setOnClickListener(this);
//        Intent i = new Intent(this, Submit.class);
//        startActivity(i);

        Calendar  calendar = Calendar.getInstance();

        int hour1 = calendar.get(Calendar.HOUR_OF_DAY);
        int min1 = calendar.get(Calendar.MINUTE);
       toTime= showTime(hour1, min1);
    }

    public String showTime(int hour, int min) {
        if (hour == 0) {
            hour += 12;
            format = "AM";
        }
        else if (hour == 12) {
            format = "PM";
        } else if (hour > 12) {
            hour -= 12;
            format = "PM";
        } else {
            format = "AM";
        }

        return (Integer.toString(hour)+Integer.toString(min));
       // (new StringBuilder().append(hour).append(" : ").append(min)
           //    .append(" ").append(format));


    }



    private void setDateTimeField() {
        fromDateEtxt.setOnClickListener(this);
        toDateEtxt.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                fromDateEtxt.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        toDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                toDateEtxt.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

    @Override
    public void onClick(View view) {
        if(view == fromDateEtxt) {
            fromDatePickerDialog.show();
        } else if(view == toDateEtxt) {
            toDatePickerDialog.show();
        }
        else if(view.getId() == R.id.submit ){
            uploadData();

    }}
    void uploadData(){
        Log.d(TAG, "upload start");
        JSONObject jsonParams = new JSONObject();
//        37.37690249999999,
//                -121.92172265625
//        ],
        try {
            jsonParams.put("latitude"," 37.37690249999999,");
            jsonParams.put("longitude", "-121.92172265625");
            jsonParams.put("phone","45678909999");
            jsonParams.put("date","17/7/2016");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        StringEntity entity = null;
        try {
            entity = new StringEntity(jsonParams.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //Inform that you are  serving food.
        HttpCalls.post( this, "http://726317ae.ngrok.io/foodRescue/res/pushNotifications", entity, "application/json",
                new JsonHttpResponseHandler(){
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        Log.d(TAG, "faileddd "+statusCode);
                       //  Toast.makeText(getApplicationContext(), "Error. Please try again later.",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray responseString) {
                        Log.d(TAG, "successss "+statusCode + "response "+responseString);



                        //    Toast.makeText(getApplicationContext(), "",Toast.LENGTH_SHORT).show();
//                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
//                        startActivity(i);

                    }
                });
        Intent intent = new Intent(DonationActivity.this, Submit.class);
        startActivity(intent);

    }

}

