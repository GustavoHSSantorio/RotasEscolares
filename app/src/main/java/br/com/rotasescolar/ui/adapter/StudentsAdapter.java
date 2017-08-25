package br.com.rotasescolar.ui.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.rotasescolar.R;
import br.com.rotasescolar.model.Student;
import br.com.rotasescolar.model.StudentGroup;
import br.com.rotasescolar.ui.viewHolder.StudentViewHolder;

/**
 * Created by gustavosantorio on 14/08/17.
 */

public class StudentsAdapter extends RecyclerView.Adapter  {

    private Context context;
    private List<StudentGroup> studentGroup;

    public StudentsAdapter(Context context, List<StudentGroup> studentGroup){
        this.context = context;
        this.studentGroup = studentGroup;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new StudentViewHolder(LayoutInflater.from(context).inflate(R.layout.student_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        ((StudentViewHolder) holder).rvStudents.setHasFixedSize(true);
        ((StudentViewHolder) holder).rvStudents.setLayoutManager(linearLayoutManager);
        ((StudentViewHolder) holder).rvStudents.setAdapter(new RoutesStudentPreviewAdapter(context, studentGroup.get(position).students));
        ((StudentViewHolder) holder).tvTitle.setText(studentGroup.get(position).name);
    }

    @Override
    public int getItemCount() {
        return studentGroup.size();
    }
}
