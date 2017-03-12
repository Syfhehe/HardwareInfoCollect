package com.ibm.cea.hdw.core;

import java.util.Timer;

public class Collector {	

	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.schedule(new HardwareTimerTask(), 1000, 60000);	
		timer.schedule(new IIBInfoTimerTask(), 10000, 60000);	
	}
}
