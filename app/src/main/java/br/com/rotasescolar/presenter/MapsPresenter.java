package br.com.rotasescolar.presenter;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Resources;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.List;

import br.com.rotasescolar.model.Route;
import br.com.rotasescolar.R;
import br.com.rotasescolar.receiver.BasicReceiver;
import br.com.rotasescolar.ui.adapter.RoutesMapAdapter;
import br.com.rotasescolar.ui.fragment.MapsFragment;
import br.com.rotasescolar.utils.Constants;
import br.com.rotasescolar.utils.EventUtils;
import br.com.rotasescolar.utils.GPSProvider;
import br.com.rotasescolar.viewModel.MapsViewModel;

import static com.google.android.gms.internal.zzs.TAG;

/**
 * Created by gustavosantorio on 06/07/17.
 */

public class MapsPresenter extends BasePresenter implements OnMapReadyCallback, GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks{

    private MapsViewModel model;
    private FragmentManager childFragmentManager;
    private GoogleMap googleMap;
    private MapsFragment mapsFragment;
    private RoutesMapAdapter adapter;
    private BasicReceiver receiver;
    private GPSProvider gpsProvider;
    private static GoogleApiClient client;


    public MapsPresenter(MapsFragment fragment){
        super((AppCompatActivity) fragment.getActivity(), MapsViewModel.class);
        model = getViewModel();
        childFragmentManager = fragment.getChildFragmentManager();
        this.mapsFragment = fragment;

        adapter = new RoutesMapAdapter(mapsFragment.getActivity(), null);
        receiver = new BasicReceiver();
        gpsProvider = new GPSProvider(fragment.getActivity(), receiver);
        registerSelectedRouteObserver();
        registerGetLatLngByRouteIdObserver();
        registerAllRoutesObserver();
        registerPermissionsReceiver();
        registerLocationObserver();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate(){
        if (client == null) {
            client = new GoogleApiClient.Builder(mapsFragment.getActivity())
                    .enableAutoManage(mapsFragment.getActivity(), 0, this)
                    .addApi(LocationServices.API)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .build();
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void onStart(){
        if(childFragmentManager.findFragmentByTag(Constants.MapsConstants.MAP_FRAGMENT_TAG) == null) {
            SupportMapFragment mapFragment = SupportMapFragment.newInstance();
            childFragmentManager.beginTransaction().add(R.id.fl_content_map, mapFragment, Constants.MapsConstants.MAP_FRAGMENT_TAG).commit();
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        getAllRoutes();
        mapsFragment.setAdapters(adapter);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        try {
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            mapsFragment.getActivity(), R.raw.style_json));

            if (!success) {
                Log.e(TAG, "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Can't find style. Error: ", e);
        }
    }

    private void setLatLngsMarker(List<LatLng> latLngs) {
        googleMap.clear();
        for (LatLng latLng : latLngs)
            createMarker(latLng.latitude,latLng.longitude, "teste", null, null);

        PolylineOptions lineOptions = new PolylineOptions();
        lineOptions.addAll(latLngs);
        lineOptions.width(10);
        lineOptions.color(Color.RED);
        googleMap.addPolyline(lineOptions);
    }

    private void addLocationMarker(LatLng latLng){
        googleMap.clear();
        googleMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_person_pin_circle_white_48dp)));
    }

    private void moveMapCamera(LatLng latLng){
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
    }

    private Marker createMarker(double latitude, double longitude, String title, String snippet, Integer iconResID) {

        return googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(latitude, longitude))
                .anchor(0.5f, 0.5f)
                .title(title));
    }

    public void updateGpsLocation(){
        gpsProvider.getGPSLocation();
    }

    private void getLatLngByRouteId(int routeId){
           model.updateLatLngByRouteId(routeId);
    }

    private void getAllRoutes(){
        model.updateRoutes();
    }

    private void registerLocationObserver(){
        gpsProvider.registerLacationObserver().observe(mapsFragment, new Observer<Location>() {
            @Override
            public void onChanged(@Nullable Location location) {
                LatLng latLng = GPSProvider.convertLocationToLatLatLng(location);
                moveMapCamera(latLng);
                addLocationMarker(latLng);
            }
        });
    }

    private void registerPermissionsReceiver(){
        receiver.getReceivedIntent().observe(mapsFragment, new Observer<Intent>() {
            @Override
            public void onChanged(@Nullable Intent intent) {
                updateGpsLocation();
            }
        });
    }

    private void registerAllRoutesObserver(){
        model.routes.observe(mapsFragment, new Observer<List<Route>>() {
            @Override
            public void onChanged(@Nullable List<Route> routes) {
                adapter.updateDataSet(routes);
                mapsFragment.setAdapters(adapter);
            }
        });
    }

    private void registerSelectedRouteObserver(){
        adapter.getSelectedRouteObserver().observe(mapsFragment, new Observer<Route>() {
            @Override
            public void onChanged(@Nullable Route route) {
                if(route != null)
                    getLatLngByRouteId(route.getId());
            }
        });
    }

    private void registerGetLatLngByRouteIdObserver(){
        model.latLngs.observe(mapsFragment, new Observer<List<LatLng>>() {
            @Override
            public void onChanged(@Nullable List<LatLng> latLngs) {
                setLatLngsMarker(latLngs);
                moveMapCamera(latLngs.get(0));
            }
        });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        final LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(30 * 1000);
        locationRequest.setFastestInterval(5 * 1000);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);

        builder.setAlwaysShow(true);
        PendingResult result = LocationServices.SettingsApi.checkLocationSettings(client, builder.build());

        if (result != null) {
            result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
                @Override
                public void onResult(LocationSettingsResult locationSettingsResult) {
                    final Status status = locationSettingsResult.getStatus();

                    switch (status.getStatusCode()) {
                        case LocationSettingsStatusCodes.SUCCESS:
                            updateGpsLocation();
                            break;
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                            // Location settings are not satisfied. But could be fixed by showing the user
                            // a optionsDialog.
                            try {
                                // Show the optionsDialog by calling startResolutionForResult(),
                                // and check the result in onActivityResult().
                                if (status.hasResolution()) {
                                    status.startResolutionForResult(mapsFragment.getActivity(), Constants.GPSProviderConstants.PROVIDER_REQUEST_CODE);
                                    EventUtils.registerReceiver(receiver, Constants.GPSProviderConstants.PROVIDER_INTENT_FILTER);
                                }
                            } catch (IntentSender.SendIntentException e) {
                                // Ignore the error.
                            }
                            break;
                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            // Location settings are not satisfied. However, we have no way to fix the
                            // settings so we won't show the optionsDialog.
                            break;
                    }
                }
            });
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}
