package br.com.rotasescolar.receiver;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by gustavosantorio on 17/07/17.
 */

public class BasicReceiver extends BroadcastReceiver {

    private MutableLiveData<Intent> receivedIntent = new MutableLiveData<>();

    @Override
    public void onReceive(Context context, Intent intent) {
        receivedIntent.setValue(intent);
    }

    public LiveData<Intent> getReceivedIntent(){
        return receivedIntent;
    }

}
