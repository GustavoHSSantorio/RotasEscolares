package br.com.rotasescolar.ui.viewHolder;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.com.rotasescolar.R;

/**
 * Created by gustavosantorio on 14/08/17.
 */

public class StudentViewHolder extends RecyclerView.ViewHolder {

    public TextView tvTitle;
    public RecyclerView rvStudents;

    public StudentViewHolder(View itemView) {
        super(itemView);
        tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        rvStudents = (RecyclerView) itemView.findViewById(R.id.rv_students);
    }
}
