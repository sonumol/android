package com.example.pink_police;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.pink_police.Emergency_request.viewemergency;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

public class View_Complaint extends Activity {

	 JSONParser jParser = new JSONParser();
	    public static String ur="";
	    ListView lv;
		SharedPreferences  sp;
		public static ArrayList<String> name,complaint,date ; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view__complaint);
		sp=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		String ip=sp.getString("ip", "");
		  lv = (ListView) findViewById(R.id.listView1);
		ur="http://"+ip+":8080/Pink_Police/Emergency";
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


		
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view__complaint, menu);
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
               complaint =new ArrayList<String>();
               date =new ArrayList<String>();
               
               
                
                for (int i = 0; i < ar.length(); i++) {
                        JSONObject c = ar.getJSONObject(i);

                        name.add(c.getString("name"));
                        complaint.add(c.getString("complaint"));
                        date.add(c.getString("date"));
                       
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
                	lv.setAdapter(new Verifysafe(getApplicationContext(),name,complaint,date));


                }
            });

        }





    }


}



