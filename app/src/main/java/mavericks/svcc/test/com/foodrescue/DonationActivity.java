package mavericks.svcc.test.com.foodrescue;

import android.app.DatePickerDialog;


import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_view);
        fromDateEtxt = (EditText) findViewById(R.id.calendarFrom);
        toDateEtxt = (EditText) findViewById(R.id.calendarTo);

        fromTimeExt= (TimePicker) findViewById(R.id.fromTime);
        toTimeExt= (TimePicker) findViewById(R.id.toTime);
        //time = (TextView) findViewById(R.id.textView1);



        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        findViewsById();

        setDateTimeField();
        setfromTimeField();
        settoTimeField();


        Intent i = new Intent(getApplicationContext(), Submit.class);
        startActivity(i);
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
    }
}
