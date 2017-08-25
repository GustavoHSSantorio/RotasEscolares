package br.com.rotasescolar.viewModel;

import android.app.Activity;
import android.arch.lifecycle.ViewModel;
import android.content.Intent;
import android.support.annotation.NonNull;

import br.com.rotasescolar.utils.Constants;
import br.com.rotasescolar.utils.EventUtils;

/**
 * Created by gustavosantorio on 17/07/17.
 */

public class MainViewModel extends BaseViewModel {

    public void onPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        if(requestCode == Constants.GPSProviderConstants.GPS_REQUEST_CODE){
            Intent intent= new Intent(Constants.GPSProviderConstants.GPS_INTENT_FILTER);
            intent.putExtra(Constants.MainConstants.PERMISSIONS_REQUEST_CODE, requestCode);
            intent.putExtra(Constants.MainConstants.PERMISSIONS_REQUEST, permissions);
            intent.putExtra(Constants.MainConstants.PERMISSIONS_REQUEST_RESULT, grantResults);
            sendEnvent(intent);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if ((resultCode == Activity.RESULT_OK) && (requestCode == Constants.GPSProviderConstants.PROVIDER_REQUEST_CODE)){
            sendEnvent(Constants.GPSProviderConstants.PROVIDER_INTENT_FILTER);
        }
    }

    private void sendEnvent(Intent intent){
        EventUtils.sendEvent(intent);
    }
    private void sendEnvent(String intentFilter){
        EventUtils.sendEvent(intentFilter);
    }

}
