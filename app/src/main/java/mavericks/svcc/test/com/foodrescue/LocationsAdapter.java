package mavericks.svcc.test.com.foodrescue;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by savani on 9/25/16.
 */
public class LocationsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<LocationModel> locations ;
    LocationsAdapter(List<LocationModel> list){
       // locations = new ArrayList<>();
        locations = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == 1){
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.foodrow, parent, false);
            return new LocationsViewHolder(v);
        }
        return null;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List<Object> payloads) {

    }
    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

}
class  LocationsViewHolder extends RecyclerView.ViewHolder {
   protected TextView addressView;
    protected TextView phoneView;

    LocationsViewHolder(View itemView){
        super(itemView);
        addressView = (TextView) itemView.findViewById(R.id.address);
        phoneView = (TextView) itemView.findViewById(R.id.phone);

    }
}
