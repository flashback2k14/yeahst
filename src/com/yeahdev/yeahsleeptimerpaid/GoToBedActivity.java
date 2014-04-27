package com.yeahdev.yeahsleeptimerpaid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.yeahdev.yeahsleeptimerpaid.fragments.GoToBedFragment;

public class GoToBedActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_go_to_bed);
		getActionBar().setIcon(R.drawable.ic_launcher_ab);
		setTitle(getResources().getString(R.string.gtbTitle));

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction().add(R.id.rlGoToBedActivity, new GoToBedFragment()).commit();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.go_to_bed, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		//
		if (id == R.id.action_settingsGTB) {
			Intent i = new Intent(GoToBedActivity.this, PrefsActivity.class);
			startActivity(i);	
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}
}