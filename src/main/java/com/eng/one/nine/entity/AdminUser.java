/**
 * 
 */
package com.eng.one.nine.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author laiyp
 * @date 2018年5月5日 
 *
 *	管理员表
 */
@Entity
@Table(name = "admin_user")
public class AdminUser {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String adminName;
	
	private String adminPassword;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date ts;
	
	private String telphone;
	
	
	private Integer status;
	
	private String imgStr;
	/**
     * 邮箱
     */
    private String email;
    
	/**
	 * 角色
	 * *//*
    @ManyToOne
	private Role role;*/

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Date getTs() {
		return ts;
	}

	public void setTs(Date ts) {
		this.ts = ts;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getImgStr() {
		return imgStr;
	}

	public void setImgStr(String imgStr) {
		this.imgStr = imgStr;
	}
	
	
	
	

	

}
