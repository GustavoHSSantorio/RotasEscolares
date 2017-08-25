package br.com.rotasescolar.ui.fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.rotasescolar.R;
import br.com.rotasescolar.presenter.StudentsPresenter;

/**
 * Created by gustavosantorio on 14/08/17.
 */

public class StudentsFragment extends BaseMainFragment {

    private RecyclerView rvStudentsContent;
    private StudentsPresenter presenter;

    public static StudentsFragment newInstance() {
        Bundle args = new Bundle();

        StudentsFragment fragment = new StudentsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new StudentsPresenter(this);
        getLifecycle().addObserver(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_students, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvStudentsContent = viewById(R.id.rv_student_content);
        rvStudentsContent.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvStudentsContent.setLayoutManager(linearLayoutManager);
    }

    public void setAdapter(RecyclerView.Adapter adapter){
        rvStudentsContent.setAdapter(adapter);
    }

    @Override
    public void onFloatingButtonClick() {

    }
}
