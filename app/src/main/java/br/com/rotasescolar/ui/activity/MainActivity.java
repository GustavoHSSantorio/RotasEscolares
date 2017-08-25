package br.com.rotasescolar.ui.activity;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import br.com.rotasescolar.presenter.MainPresenter;
import br.com.rotasescolar.R;

public class MainActivity extends BaseActivity implements LifecycleRegistryOwner, BottomNavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    public FloatingActionButton fabMain;
    public BottomNavigationView bottomNavigationMenu;

    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationMenu = viewById(R.id.bnv_navigation);
        this.fabMain = viewById(R.id.fab_main);
        fabMain.setOnClickListener(this);

        presenter = new MainPresenter(this);

        bottomNavigationMenu.setOnNavigationItemSelectedListener(this);
        getLifecycle().addObserver(presenter);
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        presenter.navigate(item.getItemId());
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        presenter.onPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab_main:
                presenter.onFloatingButtonClick();
                break;
        }
    }
}
