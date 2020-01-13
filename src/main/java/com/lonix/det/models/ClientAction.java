package com.lonix.det.models;

public class ClientAction {
	
	private String ip;
	private String action;
	private String date;
	
	public ClientAction() {
		
	}
	
	public ClientAction(String ip, String action, String date) {
		super();
		this.ip = ip;
		this.action = action;
		this.date = date;
	}
	
	
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	

}
