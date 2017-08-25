package br.com.rotasescolar.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import br.com.rotasescolar.model.Route;
import br.com.rotasescolar.R;
import br.com.rotasescolar.ui.viewHolder.RoutesMapViewHolder;

/**
 * Created by gustavosantorio on 17/07/17.
 */

public class RoutesAdapter extends BaseAdapter {

    private Context context;
    private List<Route> routes;

    public RoutesAdapter(Context context, List<Route> routes){
        this.context = context;
        this.routes = routes;
    }

    @Override
    public int getCount() {
        return routes.size();
    }

    @Override
    public Object getItem(int position) {
        return routes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return routes.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.route_item, parent, false);
        RoutesMapViewHolder holder = new RoutesMapViewHolder(view);
        Route route = routes.get(position);
        holder.name.setText(route.getName());
        return view;
    }

    public void updateDataSet(List<Route> routes){
        this.routes = routes;
        notifyDataSetChanged();
    }

    public List<Route> getRoutes(){
        return this.routes;
    }

}
