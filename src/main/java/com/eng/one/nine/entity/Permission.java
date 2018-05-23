/**
 * 
 */
package com.eng.one.nine.entity;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author laiyp
 * @date 2018年5月6日 
 *
 */
public class Permission {
	 /**
     * 权限唯一标识ID
     */
    private Integer pId;
 
    /**
     * 权限名称
     */
    private String pName;
 
    /**
     * 权限别名
     */
    private String pAlias;
 
    /**
     * 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
 
    /**
     * 修改时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;
 
    
 
}
