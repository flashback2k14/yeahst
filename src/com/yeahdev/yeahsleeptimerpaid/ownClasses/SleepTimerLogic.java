package com.yeahdev.yeahsleeptimerpaid.ownClasses;

public class SleepTimerLogic {

	private int FULLHOUR = 1;
	private int HALFHOURMIN = 30;
	private int FALLASLEEPMINDEFAULT = 14;
	private int FULLHOURMIN = 60;
	private int FULLDAYHOUR = 24;
	private int NULLHOURMINUTE = 0;
	private boolean useFallAsleepTime;
	private int userFallAsleepTime;
	
	public SleepTimerLogic (boolean useFallAsleepTime) {
		this.useFallAsleepTime = useFallAsleepTime;
	}
	
	public SleepTimerLogic (boolean useFallAsleepTime, int userFallAsleepTime) {
		this.useFallAsleepTime = useFallAsleepTime;
		this.userFallAsleepTime = userFallAsleepTime;
	}
	
	public String[] calcGoToSleepTime (int hour, int minutes) {
		String[] goToSleepTimes = new String[6];
		int restHour;
		
		if ((useFallAsleepTime) && (userFallAsleepTime == 0)) {
			minutes = minutes + FALLASLEEPMINDEFAULT;
		}
		
		if (userFallAsleepTime != 0) {
			minutes = minutes - userFallAsleepTime;
		}
		
		for (int i = 0; i < goToSleepTimes.length; i++) {
			hour = hour + FULLHOUR;
			minutes = minutes + HALFHOURMIN;
			
			if (minutes >= FULLHOURMIN) {
				hour = hour + FULLHOUR;
				minutes = minutes - FULLHOURMIN;
			}
			
			if (hour >= FULLDAYHOUR) {
				restHour = hour - FULLDAYHOUR;
				hour = hour + restHour - FULLDAYHOUR;
			}
			
			goToSleepTimes[i] = ConvertValueToDoubleZeroString(hour) + " : " + ConvertValueToDoubleZeroString(minutes);
		}
		
		return goToSleepTimes;		
	}
		
	public String[] calcWakeUpTime (int hour, int minutes) {
		String[] wakeUpTimes = new String[6];
		int index;

		for (index = 0; index < wakeUpTimes.length; index++) {
			hour = hour - FULLHOUR;
			minutes = minutes - HALFHOURMIN;
			
			if (minutes < NULLHOURMINUTE) {
				hour = hour - FULLHOUR;
				minutes = FULLHOURMIN + minutes;
			}
			
			if (hour < NULLHOURMINUTE) {
				hour = FULLDAYHOUR + hour;
			}
			
			if (index == 5) {
				if ((useFallAsleepTime) && (userFallAsleepTime == 0)) {
					minutes = minutes - FALLASLEEPMINDEFAULT;
				}
				
				if (userFallAsleepTime != 0) {
					minutes = minutes - userFallAsleepTime;
				}
						
				if (minutes < NULLHOURMINUTE) {
					minutes = FULLHOURMIN + minutes;
					hour = hour - FULLHOUR;
				}
			}
						
			wakeUpTimes[index] = ConvertValueToDoubleZeroString(hour) + " : " + ConvertValueToDoubleZeroString(minutes);
		}
		
		return wakeUpTimes;
	}
	
	private String ConvertValueToDoubleZeroString (int value) {
		String tmp = "";
		
		if (value >= 0 && value < 10) {
			tmp = String.valueOf("0" + value);
		} else {
			tmp = String.valueOf(value);
		}
		
		return tmp;
	}
}