package mavericks.svcc.test.com.foodrescue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by savani on 9/25/16.
 */
public class LocationsSingleton {
    public static LocationsSingleton mInstance;
    private List<LocationModel> locations;
    private LocationsSingleton(){
        locations = new ArrayList<>();

    }
    public static LocationsSingleton getInstance(){
        if(mInstance == null){
            mInstance = new LocationsSingleton();
        }
        return mInstance;
    }

    public List<LocationModel> getLocations(){
        return locations;
    }

}
