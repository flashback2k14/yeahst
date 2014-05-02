package com.yeahdev.yeahsleeptimerpaid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.yeahdev.yeahsleeptimerpaid.fragments.MainFragment;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
		//
		getActionBar().setIcon(R.drawable.ic_launcher_ab);
		setTitle(getResources().getString(R.string.startTitle));
		//
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction().add(R.id.rlMainActivity, new MainFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		//
		if (id == R.id.menu_item_share) {
			openShareMenu();
			return true;
		}
		
		if (id == R.id.action_settings) {
			Intent i = new Intent(MainActivity.this, PrefsActivity.class);
			startActivity(i);	
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	public void openShareMenu() {
		Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND); 
	    //
		sharingIntent.setType("text/plain");
	    //
		String shareBody = "I'm using Yeah! SleepTimer. Get it while it's hot! \n\n\nhttps://play.google.com/store/apps/details?id=com.yeahdev.yeahsleeptimerpaid";
	    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Yeah! SleepTimer");
	    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
	    //
	    startActivity(Intent.createChooser(sharingIntent, "Share via"));
	}
}