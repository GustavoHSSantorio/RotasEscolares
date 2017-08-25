package br.com.rotasescolar.utils;


import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

import java.io.Serializable;

import br.com.rotasescolar.application.RotasApplication;

/**
 * Created by gustavosantorio on 13/07/17.
 */

public class EventUtils {
    public static final  String CONTENT = "CONTENT";

    public static void sendEvent(Intent it) {
        LocalBroadcastManager.getInstance(RotasApplication.getInstance()).sendBroadcast(it);
    }

    public static void sendEvent(String filter, Serializable content) {
        Intent it = new Intent(filter);
        it.putExtra(EventUtils.CONTENT, content);
        sendEvent(it);
    }

    public static void sendEvent(String eventSignature) {
        LocalBroadcastManager.getInstance(RotasApplication.getInstance()).sendBroadcast(new Intent(eventSignature));
    }

    public static void registerReceiver(BroadcastReceiver receiver, String... filters) {
        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(RotasApplication.getInstance());
        for(String filter: filters) {
            manager.registerReceiver(receiver, new IntentFilter(filter));
        }
    }

    public static void unregisterReceivers(BroadcastReceiver... receivers) {
        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(RotasApplication.getInstance());
        for(BroadcastReceiver receiver: receivers) {
            manager.unregisterReceiver(receiver);
        }
    }

    public static <E> E getContent(Intent it) {
        return (E) it.getSerializableExtra(CONTENT);
    }
}