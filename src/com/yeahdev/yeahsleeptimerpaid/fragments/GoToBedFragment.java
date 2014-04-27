package com.yeahdev.yeahsleeptimerpaid.fragments;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.view.CardListView;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.yeahdev.yeahsleeptimerpaid.R;
import com.yeahdev.yeahsleeptimerpaid.ownClasses.ColorCard;
import com.yeahdev.yeahsleeptimerpaid.ownClasses.SleepTimerLogic;

public class GoToBedFragment extends Fragment {

	private SharedPreferences sharedPrefs;
	private boolean useFallAsleepTime;
	private String tmpUserFAT;
	private int userFallAsleepTime = 0;
	private TimePicker timePicker;
	private Button btnGo;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//
		SystemBarTintManager stm = new SystemBarTintManager(getActivity());
		stm.setStatusBarTintEnabled(true);
		stm.setStatusBarTintColor(getResources().getColor(R.color.etHeadlineColor));
		//
	    timePicker = (TimePicker)getActivity().findViewById(R.id.tpGTB);
        timePicker.setIs24HourView(true);
        timePicker.setCurrentHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
        //
        btnGo = (Button)getActivity().findViewById(R.id.btnGoGTB);
        btnGo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int hourNow;
				int minuteNow;
				String[] calcGoToBedTimes = null;
				//
				sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
				useFallAsleepTime = sharedPrefs.getBoolean("perform_updates_fallasleep", false);
				//
				if (useFallAsleepTime) {
					tmpUserFAT = sharedPrefs.getString("updates_interval_fallasleep", "0");		
					userFallAsleepTime = Integer.parseInt(tmpUserFAT) / 60000;
				}
				//
				hourNow = timePicker.getCurrentHour();
				minuteNow = timePicker.getCurrentMinute();
				//
				SleepTimerLogic logic = new SleepTimerLogic(useFallAsleepTime, userFallAsleepTime);
				userFallAsleepTime = 0;
				calcGoToBedTimes = logic.calcGoToSleepTime(hourNow, minuteNow);				
				//
				if (calcGoToBedTimes != null) {
					initCards(calcGoToBedTimes);
				} else {
					Toast.makeText(getActivity(), "Something went wrong! :-)", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	public void initCards(String[] goToBedTimes) {
        //Init an array of Cards
        ArrayList<Card> cards = new ArrayList<Card>();
        //
        for (int i = 0; i < 6; i++) {
            //
        	ColorCard card = new ColorCard(getActivity());
            card.setHeader((i+1) + getResources().getString(R.string.wut));
            card.setTime(goToBedTimes[i]);
            card.setSleepCycle((i+1) + getResources().getString(R.string.sc));
            //
            switch (i) {
                case 0:
                	card.setBackgroundResourceId(R.drawable.card_selec_dark_red);       	
                    break;
                case 1:
                    card.setBackgroundResourceId(R.drawable.card_selec_light_red);
                    break;
                case 2:
                    card.setBackgroundResourceId(R.drawable.card_selec_dark_orange);
                    break;
                case 3:
                    card.setBackgroundResourceId(R.drawable.card_selec_light_orange);
                    break;
                case 4:
                    card.setBackgroundResourceId(R.drawable.card_selec_light_green);
                    break;
                case 5:
                    card.setBackgroundResourceId(R.drawable.card_bg_dark_green);
                    break;
            }
            //
            cards.add(card);
        }
        //
        CardArrayAdapter mCardArrayAdapter = new CardArrayAdapter(getActivity(), cards);
        //
        CardListView listView = (CardListView)getActivity().findViewById(R.id.cardlistviewGTB);
        //
        if (listView != null) {
            listView.setAdapter(mCardArrayAdapter);
        }
    }
	
	@Override
    public void onDestroy() {
		useFallAsleepTime = false;
		tmpUserFAT = null;
		userFallAsleepTime = 0;
        super.onDestroy();
    }
}