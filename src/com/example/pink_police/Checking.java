package com.example.pink_police;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

public class Checking extends Activity {

	JSONParser jsonParser = new JSONParser();
    public static String ur="";
    Handler hd=new Handler();
    String imei="";
    SharedPreferences sp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_checking);
		TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		imei=telephonyManager.getDeviceId();
		sp=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		Editor ed=sp.edit();
		ed.putString("ip","192.168.43.170");
		ed.commit();
		String ip=sp.getString("ip", "");
		ur="http://"+ip+":8080/Pink_Police/checking";
		Toast.makeText(getApplicationContext(), ip, Toast.LENGTH_LONG).show();
		try
	    {
	    	if(android.os.Build.VERSION.SDK_INT > 9)
	    	{
	    		StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    		StrictMode.setThreadPolicy(policy);
	    	}
	    }
	    catch(Exception e)
	    {
	    	
	    }
		
		hd.post(r);
		
		
	}
	
	Runnable r=new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			  List<NameValuePair> params = new ArrayList<NameValuePair>();
	            params.add(new BasicNameValuePair("imei", imei));
	          
	           
	            Log.d("ins===",ur);
	            JSONObject json=jsonParser.makeHttpRequest(ur,"GET", params);
	            String s = null;

	            try {
	            	s=json.getString("info");
	                Log.d("Msg+++++++++++++++++++++++++++++++++++++++++++++++", s);
	                if(!s.equals("empty"))
	                {
	                	Editor ed = sp.edit();
	                	ed.putString("imei", s);
	                	ed.commit();
	                    
	                	Intent i1=new Intent(getApplicationContext(),Police_home.class);
	                    startActivity(i1);
	                }
	                else
	                {
	                	Toast.makeText(getApplicationContext(), "Invalid device", Toast.LENGTH_LONG).show();
	
	                }
	            }
	            catch(Exception e)
	            {
	            	Toast.makeText(getApplicationContext(), "Error"+e, Toast.LENGTH_LONG).show();
	            }

			
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.checking, menu);
		return true;
	}

}
