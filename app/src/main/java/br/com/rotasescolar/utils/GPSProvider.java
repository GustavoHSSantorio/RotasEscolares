package br.com.rotasescolar.utils;

import android.Manifest;
import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import br.com.rotasescolar.receiver.BasicReceiver;

/**
 * Created by gustavosantorio on 13/07/17.
 */

public class GPSProvider implements LocationListener {

    public final String[] REQUIRED_PERMISSIONS = new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION};

    private Activity context;
    private BasicReceiver receiver;
    private MutableLiveData<Location> location = new MutableLiveData<>();

    public GPSProvider(Activity context, BasicReceiver receiver){
        this.context = context;
        this.receiver = receiver;
    }

    public void getGPSLocation(){
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(context, REQUIRED_PERMISSIONS, Constants.GPSProviderConstants.GPS_REQUEST_CODE);
            EventUtils.registerReceiver(receiver, Constants.GPSProviderConstants.GPS_INTENT_FILTER);
        }else {
            this.location.setValue(locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER));
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        this.location.setValue(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("","");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("","");
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("","");
    }


    public LiveData<Location> registerLacationObserver(){
        return location;
    }


    public static LatLng convertLocationToLatLatLng(Location location){
        if(location != null)
            return new LatLng(location.getLatitude(), location.getLongitude());
        else
            return new LatLng(0,0);
    }
}
