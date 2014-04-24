package com.yeahdev.yeahsleeptimerpaid;

import android.app.Activity;
import android.os.Bundle;

import com.yeahdev.yeahsleeptimerpaid.fragments.PrefsFragment;

public class PrefsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_prefs);
		getActionBar().setIcon(R.drawable.ic_launcher_ab);
		setTitle(getResources().getString(R.string.action_settings));

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction().add(R.id.containerSettings, new PrefsFragment()).commit();
		}
	}
}