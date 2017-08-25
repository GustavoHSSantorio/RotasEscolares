package br.com.rotasescolar.ui.activity;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import br.com.rotasescolar.model.Route;
import br.com.rotasescolar.model.Student;
import br.com.rotasescolar.R;
import br.com.rotasescolar.presenter.RegisterRoutePresenter;
import br.com.rotasescolar.ui.adapter.RoutesStudentPreviewAdapter;
import br.com.rotasescolar.utils.Constants;

/**
 * Created by gustavosantorio on 18/07/17.
 */

public class RegisterRouteActivity extends BaseActivity implements View.OnClickListener {

    private LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);
    private RecyclerView rvStudentContent;
    private CardView cvStudents;
    private FloatingActionButton fabSave;

    public EditText etRouteName;
    public EditText etOutTime;

    private RegisterRoutePresenter presenter;

    public Integer routeId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_route);
        inflateView();
        configureRecycleView();
        fabSave.setOnClickListener(this);

        if(getIntent().getExtras() != null)
            routeId = getIntent().getExtras().getInt(Constants.RoutesConstants.ROUTE_ID);

        presenter =  new RegisterRoutePresenter(this);
        getLifecycle().addObserver(presenter);
    }

    private void inflateView(){
        rvStudentContent = viewById(R.id.rv_student_content);
        cvStudents = viewById(R.id.cv_students);
        fabSave = viewById(R.id.fab_save);
        etRouteName = viewById(R.id.et_route_name);
        etOutTime = viewById(R.id.et_out_time);
    }

    private void configureRecycleView(){
        rvStudentContent.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvStudentContent.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void setAdapters(RecyclerView.Adapter... args){
        rvStudentContent.setAdapter(args[0]);
        cvStudents.setVisibility(View.VISIBLE);
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab_save:
                presenter.save();
                break;
        }
    }
}
