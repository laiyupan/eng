/**
 * 
 */
package com.eng.one.nine.vo;

import com.google.gson.Gson;

/**
 * @author laiyp
 * @date 2018年5月13日 
 *
 */
public class RequestInfo {
	private String adminName;
	
	private String adminPassword;

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	
	public static void main(String[] args) {
		RequestInfo info=new RequestInfo();
		info.setAdminName("123");
		info.setAdminPassword("123");
		System.out.println(new Gson().toJson(info));
	}
	
	

}
