package com.yeahdev.yeahsleeptimerpaid;

import android.app.Activity;
import android.os.Bundle;

import com.yeahdev.yeahsleeptimerpaid.fragments.GoToBedNowFragment;

public class GoToBedNowActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_go_to_bed_now);
		getActionBar().setIcon(R.drawable.ic_launcher_ab);
		setTitle(getResources().getString(R.string.gtbnTitle));

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction().add(R.id.rlGoToBedNowActivity, new GoToBedNowFragment()).commit();
		}
	}
}