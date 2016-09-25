package mavericks.svcc.test.com.foodrescue;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
 Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerBtn = (Button) findViewById(R.id.register);
        registerBtn.setOnClickListener(this);

    }

    public void login(View view){
      //  Datentime fragment=new Datentime();
        Intent intent = new Intent(MainActivity.this, DonationActivity.class);

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

}

