package com.ibm.cea.hdw.core;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Map;
import java.util.Properties;
import java.util.TimerTask;

import com.google.gson.Gson;
import com.ibm.cea.hdw.model.HardwareStat;
import com.ibm.cea.hdw.util.DataProcessUtil;
import com.ibm.cea.hdw.util.RunCommondUtil;
import com.ibm.cea.hdw.util.SendHdwInfoUtil;

public class HardwareTimerTask extends TimerTask{
	private static String getCurrentTime;
	private static String getHostname;
	private static String getVmstat;
	private static String getMem;
	private static String targetUrl;
	private static String getDiskSpace;

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
		getVmstat = properties.getProperty("getVmstatCMD");
		getMem = properties.getProperty("getMemCMD");
		targetUrl = properties.getProperty("targetUrlCMD");
		getDiskSpace = properties.getProperty("getDiskSpaceCMD");
	}
	
	@Override
	public void run() {
		try {
			Gson gson = new Gson();
			HardwareStat hardwareStat = new HardwareStat();
			String statTime = RunCommondUtil.exec(getCurrentTime).toString();
			String hostname = RunCommondUtil.exec(getHostname).toString();
			String vmstatString = RunCommondUtil.exec(getVmstat).toString();
			String memstatString = RunCommondUtil.exec(getMem).toString();
			String diskInfoString = RunCommondUtil.exec(getDiskSpace).toString();
			
			String[] vmstatStringArray = DataProcessUtil.splitString(vmstatString);
			String[] memstatStringArray = DataProcessUtil.splitString(memstatString);
			String[] diskInfoStringArray = DataProcessUtil.splitString(diskInfoString);

			Map<String, String> vmstatMap = DataProcessUtil.genVmstatMap(vmstatStringArray[1],vmstatStringArray[2]);
			Map<String, String> memstatMap = DataProcessUtil.genMemMap(memstatStringArray[0],memstatStringArray[1]);
			Map<String, String> diskInfoMap = DataProcessUtil.genDiskInfoMap(diskInfoStringArray[0],diskInfoStringArray[1]);

			hardwareStat.setStatTime(DataProcessUtil.normalizeStringToLong(statTime));
			hardwareStat.setHostname(DataProcessUtil.normalizeUrl(hostname));
			hardwareStat.setFree(DataProcessUtil.normalizeStringToInt(vmstatMap.get("free")));
			hardwareStat.setSwpd(DataProcessUtil.normalizeStringToInt(vmstatMap.get("swpd")));
			hardwareStat.setSi(DataProcessUtil.normalizeStringToInt(vmstatMap.get("si")));
			hardwareStat.setSo(DataProcessUtil.normalizeStringToInt(vmstatMap.get("so")));
			hardwareStat.setWa(DataProcessUtil.normalizeStringToInt(vmstatMap.get("wa")));
			hardwareStat.setB(DataProcessUtil.normalizeStringToInt(vmstatMap.get("b")));
			hardwareStat.setId(DataProcessUtil.normalizeStringToInt(vmstatMap.get("id")));
			hardwareStat.setTatolMem(DataProcessUtil.normalizeStringToInt(memstatMap.get("total")));
			hardwareStat.setDiskSpace(DataProcessUtil.normalizeStringToInt(diskInfoMap.get("Avail")));
			String jsonOBject = gson.toJson(hardwareStat);
			SendHdwInfoUtil.sendData(targetUrl, jsonOBject);
		} catch (Exception e) {
			e.printStackTrace();
		}
	};
}

