package mavericks.svcc.test.com.foodrescue;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Created by savani on 9/24/16.
 */
public class HttpCalls {

    private static final String BASE_URL = "http://thunder.goadvanz.com/android/";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
        //client.post()
    }

    /*
    TODO: write static method to retrieve pictures
     */
//    public static void getPicture()

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

    public static void post(Context context, String url, StringEntity entity, String contentType, AsyncHttpResponseHandler responseHandler) {
        client.post(context, url, entity,contentType , responseHandler);
        //client.post()
    }

}