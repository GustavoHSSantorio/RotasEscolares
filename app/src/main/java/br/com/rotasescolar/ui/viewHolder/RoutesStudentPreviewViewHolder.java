package br.com.rotasescolar.ui.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.rotasescolar.R;

/**
 * Created by gustavosantorio on 18/07/17.
 */

public class RoutesStudentPreviewViewHolder extends RecyclerView.ViewHolder {

    public ImageView ivImage;
    public TextView tvName;
    public TextView tvAddress;
    public ImageButton ibEdit;

    public RoutesStudentPreviewViewHolder(View itemView) {
        super(itemView);
        ivImage = (ImageView) itemView.findViewById(R.id.iv_image);
        tvName = (TextView) itemView.findViewById(R.id.tv_name);
        tvAddress = (TextView) itemView.findViewById(R.id.tv_address);
        ibEdit = (ImageButton) itemView.findViewById(R.id.ib_edit);
    }

}
