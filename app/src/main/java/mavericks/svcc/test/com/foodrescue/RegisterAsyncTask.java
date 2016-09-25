package mavericks.svcc.test.com.foodrescue;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by savani on 9/24/16.
 */
public class RegisterAsyncTask extends AsyncTask<UserProfile, Void, Boolean> {
 private final String TAG= "Async task";
    @Override
    protected Boolean doInBackground(UserProfile... params) {
       // HttpURLConnection
        try {
            URL url  = new URL("http://192.168.90.249:8080/foodRescue/res/create");
            HttpURLConnection client = (HttpURLConnection) url.openConnection();
            client.setRequestMethod("POST");
            //client.
            client.setRequestProperty("name","savvy");
            client.setDoOutput(true);
            OutputStream outputPost = client.getOutputStream();   //new BufferedOutputStream(client.getOutputStream());
            BufferedWriter writeStream = new BufferedWriter(
                    new OutputStreamWriter(outputPost, "UTF-8"));
           // writeStream(outputPost);
            int responseCode = client.getResponseCode();
            Log.d(TAG, "response  "+responseCode);
            writeStream.flush();
            writeStream.close();
            //outputPost.flush();
            //outputPost.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        Log.i(TAG, "aBoolean" );
    }
}
