package com.yeahdev.yeahsleeptimerpaid;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.yeahdev.yeahsleeptimerpaid.fragments.WakeUpTimeFragment;

public class WakeUpTimeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_wake_up_time);
		getActionBar().setIcon(R.drawable.ic_launcher_ab);
		setTitle(getResources().getString(R.string.wutTitle));

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction().add(R.id.rlWakeUpTimeActivity, new WakeUpTimeFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.wake_up_time, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		/*
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}*/
		return super.onOptionsItemSelected(item);
		
	}
}