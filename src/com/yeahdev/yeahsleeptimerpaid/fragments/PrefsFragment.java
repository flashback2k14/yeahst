package com.yeahdev.yeahsleeptimerpaid.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceFragment;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.yeahdev.yeahsleeptimerpaid.R;

public class PrefsFragment extends PreferenceFragment {

	Preference btnOpenFirstLoadPopUp, btnOpenIAB, linkToPlayStore, btnSendFeedback, btnOpenLicenseDialog;
	
	@Override
	 public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//
		SystemBarTintManager stm = new SystemBarTintManager(getActivity());
		stm.setStatusBarTintEnabled(true);
		stm.setStatusBarTintColor(getResources().getColor(R.color.etHeadlineColor));
		//
		addPreferencesFromResource(R.xml.perferences);
		//
		btnOpenFirstLoadPopUp = (Preference)findPreference("btnOpenHowToPref");
		btnOpenIAB = (Preference)findPreference("btnOpenInAppBillingPref");
		linkToPlayStore = (Preference)findPreference("linkToPlayStorePref");
		btnSendFeedback = (Preference)findPreference("btnSendFeedbackEmailPref");
		btnOpenLicenseDialog = (Preference)findPreference("btnOpenLicenseDialogPref");
		//
		btnOpenFirstLoadPopUp.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			
			@Override
			public boolean onPreferenceClick(Preference preference) {
				openFirstLoadDialog();
				return false;
			}
		});
		//
		btnOpenIAB.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
			
			@Override
			public boolean onPreferenceClick(Preference preference) {	
				openIAB();
				return false;
			}
		});
		//
		linkToPlayStore.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
			
			@Override
			public boolean onPreferenceClick(Preference preference) {
				linkToPlayStore();
				return false;
			}
		});
		//
		btnSendFeedback.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
			
			@Override
			public boolean onPreferenceClick(Preference preference) {
				sendFeedback();
				return false;
			}
		});
		//		
		btnOpenLicenseDialog.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
		               
			@Override
		    public boolean onPreferenceClick(Preference arg0) { 
				openLicenseDialog();
				return true;
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

	
	public void openIAB() {
		Toast.makeText(getActivity(), "tempory no function.", Toast.LENGTH_SHORT).show();
	}
	
	public void linkToPlayStore() {
		Intent iltps = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.flashback.yeahklr.yklr&hl=de"));
        startActivity(iltps);
	}
	
	public void sendFeedback() {
		Intent email = new Intent(Intent.ACTION_SEND);
        //
		email.setType("text/email");
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{"yeahdev@gmail.com"});
        email.putExtra(Intent.EXTRA_SUBJECT, "Feedback Yeah! SleepTimer");
        email.putExtra(Intent.EXTRA_TEXT, "Vielen Dank für das Benutzen meiner App! " +
                "Bitte sende mir deine Meinung! \n\nhilfreiche Angaben\n\nTelefon:\nAndroid-OS:\nPro:\nKon:\nAnregungen:");
        //
        startActivity(Intent.createChooser(email, "Send Feedback:"));
	}
		
	public void openLicenseDialog() {
        //
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.licensedialog);
        //
        TextView tv1 = (TextView)dialog.findViewById(R.id.tvCardlib);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/gabrielemariotti/cardslib"));
                startActivity(i1);
            }
        });
        //
        TextView tv2 = (TextView)dialog.findViewById(R.id.tvSystemBarTint);
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/jgilfelt/SystemBarTint"));
                startActivity(i2);
            }
        });
        //
        Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        //
        dialog.show();
    }
}