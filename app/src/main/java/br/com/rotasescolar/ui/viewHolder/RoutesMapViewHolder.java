package br.com.rotasescolar.ui.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.rotasescolar.R;

/**
 * Created by gustavosantorio on 12/07/17.
 */

public class RoutesMapViewHolder extends RecyclerView.ViewHolder {

    public TextView name;
    public ImageView image;

    public RoutesMapViewHolder(View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.tv_name);
        image = (ImageView) itemView.findViewById(R.id.iv_image);
    }
}
