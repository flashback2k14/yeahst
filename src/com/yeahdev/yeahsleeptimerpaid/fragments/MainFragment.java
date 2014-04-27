package com.yeahdev.yeahsleeptimerpaid.fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.yeahdev.yeahsleeptimerpaid.GoToBedActivity;
import com.yeahdev.yeahsleeptimerpaid.GoToBedNowActivity;
import com.yeahdev.yeahsleeptimerpaid.R;
import com.yeahdev.yeahsleeptimerpaid.WakeUpTimeActivity;

public class MainFragment extends Fragment {

	boolean firstrun;
	SharedPreferences preferences;
	Button btnWUT, btnGTB, btnGTBN;
	
	@Override
	 public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//
		SystemBarTintManager stm = new SystemBarTintManager(getActivity());
		stm.setStatusBarTintEnabled(true);
		stm.setStatusBarTintColor(getResources().getColor(R.color.etHeadlineColor));
		//
		preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
		firstrun = preferences.getBoolean("btnOpenHowToPref", true);
		//
		if (firstrun) {
			openFirstLoadDialog();
			preferences.edit().putBoolean("btnOpenHowToPref", false).commit();
		}
		//
		btnWUT = (Button)getActivity().findViewById(R.id.btnWUT);
	    btnGTB = (Button)getActivity().findViewById(R.id.btnGTB);
	    btnGTBN = (Button)getActivity().findViewById(R.id.btnGTBN);
	    //
	    btnWUT.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent iWUT = new Intent(getActivity(), WakeUpTimeActivity.class);
	    		startActivity(iWUT);	    			
			}
		});
	    //
	    btnGTB.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent iGTB = new Intent(getActivity(), GoToBedActivity.class);
	    		startActivity(iGTB);	 
			}
		});
		//
	    btnGTBN.setOnClickListener(new View.OnClickListener() {
	
	    	@Override
	    	public void onClick(View v) {
	    		Intent iGTBN = new Intent(getActivity(), GoToBedNowActivity.class);
	    		startActivity(iGTBN);
	    	}
	    });
	 }
	
	public void openFirstLoadDialog() {
		AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity(), R.style.CustomDialogTheme);
		//
		dialog.setTitle(getString(R.string.firstloadtitle));
		//
		WebView wv = new WebView(getActivity());
		wv.loadData(getString(R.string.firststart_dialog), "text/html", "utf-8");
		//
		dialog.setView(wv);
		//
		dialog.setPositiveButton(getString(android.R.string.ok), new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();				
			}
		});
		//
		dialog.show();
	}
}