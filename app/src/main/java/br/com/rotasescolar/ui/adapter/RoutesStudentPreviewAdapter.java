package br.com.rotasescolar.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.rotasescolar.model.Student;
import br.com.rotasescolar.R;
import br.com.rotasescolar.ui.activity.RegisterStudentActivity;
import br.com.rotasescolar.ui.viewHolder.RoutesStudentPreviewViewHolder;
import br.com.rotasescolar.utils.Constants;

/**
 * Created by gustavosantorio on 18/07/17.
 */

public class RoutesStudentPreviewAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<Student> students;

    public RoutesStudentPreviewAdapter(@NonNull Context context, @NonNull List<Student> students){
        this.context = context;
        this.students = students;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RoutesStudentPreviewViewHolder(LayoutInflater.from(context).inflate(R.layout.routes_student_preview_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        ((RoutesStudentPreviewViewHolder) holder).tvName.setText(students.get(position).getName());
        ((RoutesStudentPreviewViewHolder) holder).tvAddress.setText(students.get(position).getAddress());
        ((RoutesStudentPreviewViewHolder) holder).ibEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RegisterStudentActivity.class);
                intent.putExtra(Constants.RegisterStudentConstants.STUDENT_ID, students.get(holder.getAdapterPosition()).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return students.size();
    }
}
