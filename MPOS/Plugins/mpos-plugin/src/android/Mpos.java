package com.mpos.plugin;

import org.apache.cordova.*;
/*
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;*/

import org.json.JSONArray;
import org.json.JSONException;

import java.net.URI;
import java.util.HashMap;

import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Context;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.ComponentName;
import android.content.Intent;

/**
 * Created by ALONSO UCHIDA on 7/03/2016.
 */
public class Mpos extends CordovaPlugin {

    private static final String MODULO = "CAMBIO_PIN";
	private CallbackContext _callbackContext = null;
    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {

        if (action.equals("callmpos")) {
            _callbackContext = callbackContext;
            try {
                String requestCode = data.getString(0);
                String email = data.getString(1);
                String moneda = data.getString(2);
				String monto = data.getString(3);
                String comercio = data.getString(4);
                Alert("Field:", requestCode + " " + email + " " + moneda + " " + monto + " " + comercio);
                
                Intent intent = new Intent("android.intent.action.MAIN");
				intent.addCategory("android.intent.category.LAUNCHER");
				intent.setAction(Intent.ACTION_SEND);
				intent.setComponent( 
                    new ComponentName("com.procesos.mc", "com.procesos.mc.Mediospago00mpos")
				);
                intent.putExtra("MODULO", MODULO);
				intent.putExtra("REQUESTCODE", requestCode);
				intent.putExtra("EMAIL", email);                
				intent.putExtra("MONEDA", moneda); 
                //intent.putExtra("COMERCIO", comercio);
                this.cordova.startActivityForResult(this, intent, 3579);
                
            } catch (Exception e) {
                Alert("Error:", e.getMessage());
            }
            return true;
        }      
        return false;
    }

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if (requestCode == 3579){
			//if(resultCode == Activity.RESULT_OK && data != null) {
            try{
 
				int req= data.getIntExtra("REQUESTCODE", 0);
				int resp = data.getIntExtra("RESPONSECODE", 0);
			    String msg = data.getStringExtra("MENSAJE");
				String ref = data.getStringExtra("REFERENCIAPMP");
				String apr = data.getStringExtra("APR");
				String voucher = data.getStringExtra("VOUCHER");
                
            	/*Alert("REQUESTCODE", Integer.toString(req));
            	Alert("RESPONSECODE", Integer.toString(resp));
            	Alert("MENSAJE", msg);
            	Alert("REFERENCIAPMP", ref);
            	Alert("APR", apr);
            	Alert("VOUCHER", voucher);*/
            
				JSONObject parameter = new JSONObject();
            	parameter.put("req", Integer.toString(req));
              	parameter.put("resp", Integer.toString(resp));
                parameter.put("msg", msg);
                parameter.put("ref", ref);
                parameter.put("apr", apr);
                parameter.put("voucher", voucher);
       			_callbackContext.success(parameter);        
                return;
            }catch (Exception e) {
                Alert("Error on Result:", e.getMessage());
            }
			//}
			//if(resultCode == Activity.RESULT_CANCELED ) {}
	    }
	}

    
     public void Alert(String title, String message){
          //Alert
                new AlertDialog.Builder(this.cordova.getActivity())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) { 
                        // continue with delete
                    }
                 })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) { 
                        // do nothing
                    }
                 })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }



}

