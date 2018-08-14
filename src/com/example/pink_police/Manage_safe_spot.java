package com.example.pink_police;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Manage_safe_spot extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manage_safe_spot);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.manage_safe_spot, menu);
		return true;
	}

}
