package mavericks.svcc.test.com.foodrescue;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Created by savani on 9/25/16.
 */
public class SuggestLocationsActivity extends AppCompatActivity {
    private static String TAG = "suggest locations";
    LocationsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nearbyfoodlist);
        RecyclerView recyclerView = (RecyclerView)  findViewById(R.id.foodView);
         adapter = new LocationsAdapter(LocationsSingleton.getInstance().getLocations());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        adapter.notifyDataSetChanged();


    }

//    void uploadData(){
//        Log.d(TAG, "upload start");
//        JSONObject jsonParams = new JSONObject();
//        try {
//            jsonParams.put("name", "dh");
////            jsonParams.put("address", address);
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        StringEntity entity = null;
//        try {
//            entity = new StringEntity(jsonParams.toString());
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        HttpCalls.post( this, "http://192.168.90.249:8080/foodRescue/res/create", entity, "application/json",
//                new JsonHttpResponseHandler(){
//                    @Override
//                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                        Log.d(TAG, "failed to get locations "+statusCode);
//                        Toast.makeText(getApplicationContext(), "Error. Please try again later.",Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
//                        Log.d(TAG, "success locations "+statusCode);
//                        adapter.notifyDataSetChanged();
//
//
//                        Toast.makeText(getApplicationContext(), "Account created successfully",Toast.LENGTH_SHORT).show();
//                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
//                        startActivity(i);
//                    }
//                });
//
//    }
}
