package br.com.rotasescolar.ui.fragment;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.rotasescolar.presenter.MapsPresenter;
import br.com.rotasescolar.R;

/**
 * Created by gustavosantorio on 06/07/17.
 */

public class MapsFragment extends BaseMainFragment{

    private MapsPresenter presenter;

    private RecyclerView horizontalListView;

    public static MapsFragment newInstance() {
        Bundle args = new Bundle();
        MapsFragment fragment = new MapsFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new MapsPresenter(this);
        getLifecycle().addObserver(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps_raw, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.horizontalListView = viewById(R.id.lvItems);
        horizontalListView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        horizontalListView.setLayoutManager(linearLayoutManager);
    }

    public void setAdapters(RecyclerView.Adapter... args){
        horizontalListView.setAdapter(args[0]);
    }

    @Override
    public void onFloatingButtonClick() {
        presenter.updateGpsLocation();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE){
            horizontalListView.setVisibility(View.INVISIBLE);
        }else{
            horizontalListView.setVisibility(View.VISIBLE);
        }
    }
}
