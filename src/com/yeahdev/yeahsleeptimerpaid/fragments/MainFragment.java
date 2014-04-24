package com.yeahdev.yeahsleeptimerpaid.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.yeahdev.yeahsleeptimerpaid.R;
import com.yeahdev.yeahsleeptimerpaid.WakeUpTimeActivity;

public class MainFragment extends Fragment {

	Button btnWUT, btnGTB, btnGTBN;
	
	@Override
	 public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//
		SystemBarTintManager stm = new SystemBarTintManager(getActivity());
		stm.setStatusBarTintEnabled(true);
		stm.setStatusBarTintColor(getResources().getColor(R.color.etHeadlineColor));
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

			}
		});
		//
	    btnGTBN.setOnClickListener(new View.OnClickListener() {
	
	    	@Override
	    	public void onClick(View v) {

	    	}
	    });
	 }
}