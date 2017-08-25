package br.com.rotasescolar.presenter;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;


import br.com.rotasescolar.R;
import br.com.rotasescolar.ui.activity.MainActivity;
import br.com.rotasescolar.ui.fragment.BaseMainFragment;
import br.com.rotasescolar.ui.fragment.MapsFragment;
import br.com.rotasescolar.ui.fragment.RoutesFragment;
import br.com.rotasescolar.ui.fragment.StudentsFragment;
import br.com.rotasescolar.utils.Constants;
import br.com.rotasescolar.viewModel.MainViewModel;

/**
 * Created by gustavosantorio on 07/07/17.
 */

public class MainPresenter extends BasePresenter {

    private MainActivity mainActivity;
    private FragmentManager fragmentManager;
    private MainViewModel model;

    public MainPresenter(MainActivity mainActivity) {
        super(mainActivity, MainViewModel.class);
        this.mainActivity = mainActivity;
        this.fragmentManager = mainActivity.getSupportFragmentManager();
        model = getViewModel();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
        mainActivity.bottomNavigationMenu.setSelectedItemId(R.id.menu_maps);
    }

    private Fragment getFragmentById(int id){
        fragmentManager.executePendingTransactions();
        Fragment fragment = null;

        switch (id){
            case R.id.menu_routes:
                fragment = RoutesFragment.newInstance();
                break;
            case R.id.menu_maps:
                fragment = MapsFragment.newInstance();
                break;
            case R.id.menu_student:
                fragment = StudentsFragment.newInstance();
                break;
        }

        return fragment;
    }

    private String getFragmentTagById(int id){
        fragmentManager.executePendingTransactions();
        String fragment = null;

        switch (id){
            case R.id.menu_routes:
                fragment = Constants.MainConstants.ROUTES_NAVIGATION_TAG;
                break;
            case R.id.menu_maps:
                fragment = Constants.MainConstants.MAPS_NAVIGATION_TAG;
                break;
            case R.id.menu_student:
                fragment = Constants.MainConstants.STUDENT_NAVIGATION_TAG;
                break;
        }

        return fragment;
    }

    private boolean isFragmentInBackstack(final String fragmentTagName) {
        for (int entry = 0; entry < fragmentManager.getBackStackEntryCount(); entry++) {
            if (fragmentTagName.equals(fragmentManager.getBackStackEntryAt(entry).getName())) {
                return true;
            }
        }
        return false;
    }

    public void navigate(int id){
        String tag = getFragmentTagById(id);
        if(isFragmentInBackstack(tag)) {
            fragmentManager.popBackStackImmediate(tag, 0);
        }else {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.fl_content, getFragmentById(id), tag);
            transaction.addToBackStack(tag);
            transaction.commit();
        }
        changeButtonSrc(tag);
    }

    private void changeButtonSrc(String tag){

        switch (tag){
            case Constants.MainConstants.ROUTES_NAVIGATION_TAG:
                mainActivity.fabMain.setImageDrawable(mainActivity.getResources().getDrawable(android.R.drawable.ic_input_add));
                break;
            case Constants.MainConstants.MAPS_NAVIGATION_TAG:
                mainActivity.fabMain.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.ic_gps_fixed_black_24dp));
                break;
            case Constants.MainConstants.STUDENT_NAVIGATION_TAG:
                mainActivity.fabMain.setImageDrawable(mainActivity.getResources().getDrawable(android.R.drawable.ic_input_add));
                break;
        }
    }

    public void onFloatingButtonClick(){
        ((BaseMainFragment)fragmentManager.findFragmentByTag(getFragmentTagById(mainActivity.bottomNavigationMenu.getSelectedItemId()))).onFloatingButtonClick();
    }

    public void onPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        model.onPermissionsResult(requestCode, permissions, grantResults);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        model.onActivityResult(requestCode, resultCode, data);
    }
}
