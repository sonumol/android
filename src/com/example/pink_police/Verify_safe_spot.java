package com.example.pink_police;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.pink_police.Verify_danger_spot.viewemergency;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Verify_safe_spot extends Activity implements OnItemClickListener {
	
	JSONParser jParser = new JSONParser();
    public static String ur="";
    ListView lv;
	SharedPreferences  sp;
	AlertDialog alertDialog;
	String id="";
	String ip="";
	public static ArrayList<String> name,spot,sid,type; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_verify_safe_spot);
		
		sp=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		ip=sp.getString("ip", "");
		  lv = (ListView) findViewById(R.id.listView1);
		  alertDialog = new AlertDialog.Builder(this).create();
		ur="http://"+ip+":8080/Pink_Police/verify_safe_spot";
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
		new viewemergency().execute();

lv.setOnItemClickListener(this);
		
	}





	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.verify_safe_spot, menu);
		return true;
	}
	class viewemergency extends AsyncTask<String,String,String>
    {

        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... strings) {

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            JSONObject json = jParser.makeHttpRequest(ur, "GET", params);
            Log.d("Reultttttt=====---------",json+"");
            try
            {
                //int success = json.getInt(TAG_SUCCESS);
                JSONArray ar=new JSONArray();
                ar=json.getJSONArray("product");
               // Log.d("+++++++++++",ar+"");
               name=new ArrayList<String>();
               spot =new ArrayList<String>();
               sid=new ArrayList<String>();
               type =new ArrayList<String>();
           
              
               
                
                for (int i = 0; i < ar.length(); i++) {
                        JSONObject c = ar.getJSONObject(i);

                        name.add(c.getString("user"));
                        spot.add(c.getString("spot"));
                        type.add(c.getString("type"));
                        sid.add(c.getString("sid"));
                        
                      
                       
                         Log.d("+++++++++++",c+"");


                    }


            }
            catch(JSONException e)
            {
                 Log.d("err====",e.getMessage());
            }

            return null;
        }
        protected void onPostExecute(String file_url) {

            runOnUiThread(new Runnable() {
                public void run() {

//                    ArrayAdapter<String>ad=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,name);
//                    lv.setAdapter(ad);
                	lv.setAdapter(new Verifysafe(getApplicationContext(), name,spot,type));


                }
            });

        }





    }
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		id=sid.get(arg2);
	   alertDialog.setTitle("Confirmation");
       // alertDialog.setMessage("");
        alertDialog.setButton("Verify", new DialogInterface.OnClickListener() {
           public void onClick(DialogInterface dialog, int which) {
        	   
       	
       		
       		  List<NameValuePair> params = new ArrayList<NameValuePair>();
                   params.add(new BasicNameValuePair("sid", id));
                 
                   ur="http://"+ip+":8080/Pink_Police/verified";
                   Log.d("ins===",ur);
                   JSONObject json=jParser.makeHttpRequest(ur,"GET", params);
                   String s = null;

                   try {
                   	s=json.getString("info");
                       Log.d("Msg+++++++++++++++++++++++++++++++++++++++++++++++", s);
                       if(s.equalsIgnoreCase("success"))
                       {
                       
                    		Toast.makeText(getApplicationContext(), "Spot Verified ", Toast.LENGTH_LONG).show();
                       	Intent i1=new Intent(getApplicationContext(),Police_home.class);
                           startActivity(i1);
                       }
                       else
                       {
                    		Toast.makeText(getApplicationContext(), "Not Verified ", Toast.LENGTH_LONG).show();

                       }
                   }
                   catch(Exception e)
                   {
                   	Toast.makeText(getApplicationContext(), "Error"+e, Toast.LENGTH_LONG).show();
                   }

}
});
alertDialog.setButton2("Cancel",new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            Toast.makeText(getApplicationContext(), "you have pressed cancel", 1).show();  	
        }
    });
    // Set the Icon for the Dialog
    alertDialog.setIcon(R.drawable.ic_launcher);
    alertDialog.show();

			}
	
}








