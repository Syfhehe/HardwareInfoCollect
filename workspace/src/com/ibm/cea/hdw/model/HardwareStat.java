package com.ibm.cea.hdw.model;

public class HardwareStat {
	private long statTime;//统计时间点: yyyymmddHHMM
	private String hostname;//系统名称或IP
	private int free;//空闲内存（M）
	private int swpd;//已用虚拟内存
	private int si;//虚拟内存读入
	private int so;//虚拟内存写出
	private int wa;//等待磁盘读写CPU时间
	private int b;//阻塞的进程数
	private int id;//空闲 CPU时间
	private int tatolMem;// total memory
	private int diskSpace;//
	
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
	public int getFree() {
		return free;
	}
	public void setFree(int free) {
		this.free = free;
	}
	public int getSwpd() {
		return swpd;
	}
	public void setSwpd(int swpd) {
		this.swpd = swpd;
	}
	public int getSi() {
		return si;
	}
	public void setSi(int si) {
		this.si = si;
	}
	public int getSo() {
		return so;
	}
	public void setSo(int so) {
		this.so = so;
	}
	public int getWa() {
		return wa;
	}
	public void setWa(int wa) {
		this.wa = wa;
	}
	public int getB() {
		return b;
	}
	public void setB(int b) {
		this.b = b;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDiskSpace() {
		return diskSpace;
	}
	public void setDiskSpace(int diskSpace) {
		this.diskSpace = diskSpace;
	}
	public int getTatolMem() {
		return tatolMem;
	}
	public void setTatolMem(int tatolMem) {
		this.tatolMem = tatolMem;
	}
}
