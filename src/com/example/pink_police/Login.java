package com.example.pink_police;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity {

Button b1;
EditText e1,e2;
SharedPreferences  sp;

    JSONParser jsonParser = new JSONParser();
    public static String ur="http://192.168.42.45:8080/Pink_Police/login_servlet";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		e1=(EditText)findViewById(R.id.editText1);
		e2=(EditText)findViewById(R.id.editText2);
		b1=(Button)findViewById(R.id.button1);
		sp=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		String ip=sp.getString("ip", "");
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
		
		b1.setOnClickListener(new View.OnClickListener() {
		
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String un=e1.getText().toString();
				String ps=e2.getText().toString();
				
				  List<NameValuePair> params = new ArrayList<NameValuePair>();
		            params.add(new BasicNameValuePair("uname", un));
		            params.add(new BasicNameValuePair("pswd", ps));
		           
		            Log.d("ins===",ur);
		            JSONObject json=jsonParser.makeHttpRequest(ur,"GET", params);
		            String s = null;

		            try {
		            	s=json.getString("info");
		                Log.d("Msg+++++++++++++++++++++++++++++++++++++++++++++++", s);
		                if(!s.equals("empty"))
		                {
		                	Editor ed = sp.edit();
		                	ed.putString("lid", s);
		                	ed.commit();
		                    
		                	Intent i1=new Intent(getApplicationContext(),Police_home.class);
		                    startActivity(i1);
		                }
		                else
		                {
		                	Toast.makeText(getApplicationContext(), "Invalid Username or Password", Toast.LENGTH_LONG).show();
		
		                }
		            }
		            catch(Exception e)
		            {
		            	Toast.makeText(getApplicationContext(), "Error"+e, Toast.LENGTH_LONG).show();
		            }
	
				
			}
		});
	   

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
