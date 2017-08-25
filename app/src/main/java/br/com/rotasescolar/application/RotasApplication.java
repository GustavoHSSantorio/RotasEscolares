package br.com.rotasescolar.application;

import android.app.Application;

/**
 * Created by gustavosantorio on 17/07/17.
 */

public class RotasApplication extends Application {

    private static RotasApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static RotasApplication getInstance(){
        return instance;
    }
}
