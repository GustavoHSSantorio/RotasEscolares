package br.com.rotasescolar.ui.adapter;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.rotasescolar.model.Route;
import br.com.rotasescolar.R;
import br.com.rotasescolar.ui.viewHolder.RoutesMapViewHolder;

/**
 * Created by gustavosantorio on 12/07/17.
 */

public class RoutesMapAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<Route> routes;

    private MutableLiveData<Route> selectedRoute = new MutableLiveData<>();

    public RoutesMapAdapter(Context context, List<Route> routes){
        this.context = context;
        this.routes = routes == null ? new ArrayList<Route>() : routes;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RoutesMapViewHolder(LayoutInflater.from(this.context).inflate(R.layout.route_map_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        ((RoutesMapViewHolder) holder).name.setText(routes.get(position).getName());
        if(routes.get(position).isSelected())
            ((RoutesMapViewHolder) holder).image.setColorFilter(ContextCompat.getColor(context, R.color.colorGreen));
        else
            ((RoutesMapViewHolder) holder).image.setColorFilter(ContextCompat.getColor(context, R.color.colorPrimaryDark));

        ((RoutesMapViewHolder) holder).image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Route route = routes.get(holder.getAdapterPosition());
                selectOne(route);
                selectedRoute.setValue(route);
            }
        });
    }

    @Override
    public int getItemCount() {
        return routes.size();
    }


    private void selectOne(Route routeSelected){
        for (Route route: routes)
            if(route.isSelected())
                if(!route.equals(routeSelected))
                    route.setSelected(false);

        routeSelected.setSelected(true);
        notifyDataSetChanged();
    }

    public void updateDataSet(@NonNull  List<Route> routes){
        this.routes = routes;
        notifyDataSetChanged();
    }

    public LiveData<Route> getSelectedRouteObserver(){
        return selectedRoute;
    }
}