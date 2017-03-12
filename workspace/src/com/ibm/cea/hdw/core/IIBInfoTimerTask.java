package com.ibm.cea.hdw.core;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;
import java.util.TimerTask;

import com.google.gson.Gson;
import com.ibm.cea.hdw.model.IIBStat;
import com.ibm.cea.hdw.util.DataProcessUtil;
import com.ibm.cea.hdw.util.RunCommondUtil;
import com.ibm.cea.hdw.util.SendHdwInfoUtil;

public class IIBInfoTimerTask extends TimerTask{
	private static String getCurrentTime;
	private static String getHostname;
	private static String getOracleStat;
	private static String targetUrl;

	static {
		Properties properties = new Properties();
		try {
			Reader in = new FileReader("src/cmd.properties");
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		getCurrentTime = properties.getProperty("getCurrentTimeCMD");
		getHostname = properties.getProperty("getHostnameCMD");	
		getOracleStat=properties.getProperty("getOracleStatCMD");
		targetUrl = properties.getProperty("targetUrlCMD");

	}
	
	@SuppressWarnings("unused")
	@Override
	public void run() {
		try {
			Gson gson = new Gson();
			IIBStat iibstat = new IIBStat();
			
			String iibStatString = RunCommondUtil.exec(getOracleStat).toString();
			String statTime = RunCommondUtil.exec(getCurrentTime).toString();
			String hostname = RunCommondUtil.exec(getHostname).toString();
			
			String[] iibStatStringArray = DataProcessUtil.splitString(iibStatString);
			int iibMemoryUsed = DataProcessUtil.calculateTatolMemory(iibStatStringArray);
			
			iibstat.setHostname(DataProcessUtil.normalizeUrl(hostname));
			iibstat.setStatTime(DataProcessUtil.normalizeStringToLong(statTime));
			iibstat.setStatus(DataProcessUtil.getProcessStatus(iibStatStringArray));
			iibstat.setIibMemoryUsed(DataProcessUtil.calculateTatolMemory(iibStatStringArray));
			String jsonOBject = gson.toJson(iibstat);
			SendHdwInfoUtil.sendData(targetUrl, jsonOBject);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	};
}

