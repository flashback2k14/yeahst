package com.yeahdev.yeahsleeptimerpaid.ownClasses;

import com.yeahdev.yeahsleeptimerpaid.R;

import it.gmariotti.cardslib.library.internal.Card;
import android.content.Context;
import android.content.Intent;
import android.provider.AlarmClock;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ColorCard extends Card {

	private Context context;
    protected String mHeader;
    protected String mTime;

    public ColorCard(Context context) {
        this(context, R.layout.card_color_inner_base);
        this.context = context;
    }

    public ColorCard(Context context, int innerLayout) {
        super(context, innerLayout);
        init();
    }

    public String getHeader() {
        return mHeader;
    }

    public void setHeader(String header) {
        mHeader = header;
    }
    
    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        mTime = time;
    }
    
    private void init() {
        //Add ClickListener
        setOnClickListener(new OnCardClickListener() {
            @Override
            public void onClick(Card card, View view) {
            	//
            	int hour = Integer.parseInt(mTime.split(" : ")[0]);
                int minute = Integer.parseInt(mTime.split(" : ")[1]);
                //
                Intent openNewAlarm = new Intent(AlarmClock.ACTION_SET_ALARM);
            	//
            	openNewAlarm.putExtra(AlarmClock.EXTRA_HOUR, hour);
            	openNewAlarm.putExtra(AlarmClock.EXTRA_MINUTES, minute);
            	openNewAlarm.putExtra(AlarmClock.EXTRA_MESSAGE, "Yeah! SleepTimer");
            	//
            	context.startActivity(openNewAlarm);
            }
        });
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {
        //Retrieve elements
        TextView tvHeader = (TextView) parent.findViewById(R.id.card_header_inner_simple_title);
        TextView tvTime = (TextView) parent.findViewById(R.id.carddemo_card_color_inner_simple_title);
        //	
        if (tvHeader != null)
            tvHeader.setText(mHeader);
        //
        if (tvTime != null)
            tvTime.setText(mTime);
    }
}