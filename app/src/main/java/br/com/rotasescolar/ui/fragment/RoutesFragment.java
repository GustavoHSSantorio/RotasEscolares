package br.com.rotasescolar.ui.fragment;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import br.com.rotasescolar.presenter.RoutesPresenter;
import br.com.rotasescolar.R;

/**
 * Created by gustavosantorio on 17/07/17.
 */

public class RoutesFragment extends BaseMainFragment implements AdapterView.OnItemClickListener{

    private ListView lvRoutes;

    private RoutesPresenter presenter;

    public static RoutesFragment newInstance() {
        Bundle args = new Bundle();
        RoutesFragment fragment = new RoutesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new RoutesPresenter(this);
        getLifecycle().addObserver(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_routes, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.lvRoutes = viewById(R.id.lv_routes);
        setListeners();
    }

    private void setListeners(){
        lvRoutes.setOnItemClickListener(this);
    }

    public void setAdapter(BaseAdapter adapter){
        lvRoutes.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        presenter.onItemRouteClick(position);
    }

    @Override
    public void onFloatingButtonClick() {
        presenter.onClickAdd();
    }
}
