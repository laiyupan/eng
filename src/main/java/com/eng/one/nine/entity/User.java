package com.eng.one.nine.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String userName;

	@Temporal(TemporalType.TIMESTAMP)
	private Date ts;

	private String phone;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLoginTs;
	
	
	private String password;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public Date getTs() {
		return ts;
	}


	public void setTs(Date ts) {
		this.ts = ts;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public Date getLastLoginTs() {
		return lastLoginTs;
	}


	public void setLastLoginTs(Date lastLoginTs) {
		this.lastLoginTs = lastLoginTs;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

}
