package com.yeahdev.yeahsleeptimerpaid.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceFragment;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.yeahdev.yeahsleeptimerpaid.R;
import com.yeahdev.yeahsleeptimerpaid.ownClasses.G;
import common.services.billing.IabHelper;
import common.services.billing.IabHelper.OnIabSetupFinishedListener;
import common.services.billing.IabResult;
import common.services.billing.Purchase;

public class PrefsFragment extends PreferenceFragment {

	String base64EncodedPublicKey;
	IabHelper mHelper;
	Preference btnOpenFirstLoadPopUp, btnOpenIAB, linkToPlayStore, btnSendFeedback, 
			   btnOpenLicenseDialog, btnIAB1, btnIAB2, btnIAB3;
	
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
		base64EncodedPublicKey = G.getApplicationKey();
		mHelper = new IabHelper(getActivity(), base64EncodedPublicKey);
		mHelper.startSetup(new OnIabSetupFinishedListener() {
			
			@Override
			public void onIabSetupFinished(IabResult result) {

				if (!result.isSuccess()) {
					Log.d(G.IabDebugTag, "iab setup failed");
				} else {
					 
					Log.d(G.IabDebugTag, "iab setup is OK");
    	      	    mHelper.enableDebugLogging(true, G.IabDebugTag);
				}
			}
		});
		//
		btnOpenFirstLoadPopUp = (Preference)findPreference("btnOpenHowToPref");
//		btnOpenIAB = (Preference)findPreference("btnOpenInAppBillingPref");
		btnIAB1 = (Preference)findPreference("btnOpenInAppBillingSup1Pref");
		btnIAB2 = (Preference)findPreference("btnOpenInAppBillingSup2Pref");
		btnIAB3 = (Preference)findPreference("btnOpenInAppBillingSup3Pref");
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
//		btnOpenIAB.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
//			
//			@Override
//			public boolean onPreferenceClick(Preference preference) {	
//				openIAB(0);
//				return false;
//			}
//		});
		//
		btnIAB1.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			
			@Override
			public boolean onPreferenceClick(Preference preference) {
				openIAB(1);
				return false;
			}
		});
		//
		btnIAB2.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			
			@Override
			public boolean onPreferenceClick(Preference preference) {
				openIAB(2);
				return false;
			}
		});
		//
		btnIAB3.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			
			@Override
			public boolean onPreferenceClick(Preference preference) {
				openIAB(3);
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

	public void openIAB(int iabLevel) {
		switch (iabLevel) {
		case 0:
			mHelper.launchPurchaseFlow(getActivity(), G.ITEM_SKU_Testing, 100011, mPurchaseFinishedListener, G.PurchasedTestToken);		
			break;
		case 1:
			mHelper.launchPurchaseFlow(getActivity(), G.ITEM_SKU_Support_Small, 100021, mPurchaseFinishedListener, G.PurchasedSupportSmallToken);		
			break;
		case 2:
			mHelper.launchPurchaseFlow(getActivity(), G.ITEM_SKU_Support_Mid, 100031, mPurchaseFinishedListener, G.PurchasedSupportMidToken);		
			break;
		case 3:
			mHelper.launchPurchaseFlow(getActivity(), G.ITEM_SKU_Support_High, 100041, mPurchaseFinishedListener, G.PurchasedSupportHighToken);		
			break;
		default:
			break;
		}		
	}
		
	IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() {
	
		public void onIabPurchaseFinished(IabResult result, Purchase purchase) {
			if (result.isFailure()) {
				// Handle error
				Toast.makeText(getActivity(), "Something went wrong!.", Toast.LENGTH_SHORT).show();
				return;
			} else if (purchase.getSku().equals(G.ITEM_SKU_Testing)) {
				Toast.makeText(getActivity(), "Thanks for Testing.", Toast.LENGTH_SHORT).show();
				disposeMHelper();
			} else if (purchase.getSku().equals(G.ITEM_SKU_Support_Small)) {
				Toast.makeText(getActivity(), "Thanks for Supporting my Work Small!.", Toast.LENGTH_SHORT).show();
				disposeMHelper();
			} else if (purchase.getSku().equals(G.ITEM_SKU_Support_Mid)) {
				Toast.makeText(getActivity(), "Thanks for Supporting my Work Mid!.", Toast.LENGTH_SHORT).show();
				disposeMHelper();
			} else if (purchase.getSku().equals(G.ITEM_SKU_Support_High)) {
				Toast.makeText(getActivity(), "Thanks for Supporting my Work High!.", Toast.LENGTH_SHORT).show();
				disposeMHelper();
			}
		}
	};
	
	public void disposeMHelper() {
		if (mHelper != null) {
			mHelper.dispose();
			mHelper = null;
		}
	}
	
	public void linkToPlayStore() {
		Intent iltps = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.yeahdev.yeahsleeptimerpaid"));
		startActivity(iltps);
	}
	
	public void sendFeedback() {
		Intent email = new Intent(Intent.ACTION_SEND);
        //
		email.setType("text/email");
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{"yeahdev@gmail.com"});
        email.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.fbSubject));
        email.putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.fbBody));
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
                Intent i1 = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.l21)));
                startActivity(i1);
            }
        });
        //
        TextView tv2 = (TextView)dialog.findViewById(R.id.tvSystemBarTint);
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.l31)));
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
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	      if (!mHelper.handleActivityResult(requestCode, resultCode, data)) {     
	    	super.onActivityResult(requestCode, resultCode, data);
	      }
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		disposeMHelper();
	}
}