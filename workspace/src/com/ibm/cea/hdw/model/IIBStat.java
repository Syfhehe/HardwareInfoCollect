package com.ibm.cea.hdw.model;

public class IIBStat {
	private long statTime;//统计时间点: yyyymmddHHMM
	private String hostname;//系统名称或IP
	private String status;//IIB状态
	private int iibMemoryUsed;//内存占用量
	
	public long getStatTime() {
		return statTime;
	}
	public void setStatTime(long statTime) {
		this.statTime = statTime;
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getIibMemoryUsed() {
		return iibMemoryUsed;
	}
	public void setIibMemoryUsed(int iibMemoryUsed) {
		this.iibMemoryUsed = iibMemoryUsed;
	}
}
