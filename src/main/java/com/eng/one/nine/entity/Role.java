/**
 * 
 */
package com.eng.one.nine.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * @author laiyp
 * @date 2018年5月6日 
 *
 */
public class Role {
	/**
     * 角色唯一标识符
     */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer rId;
 
    /**
     * 角色名称
     */
	@Column(nullable=false)
    private String rName;
 
    /**
     * 角色别名
     */
    @Column(nullable=false)
    private String rAlias;
 
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
 
 
    @OneToMany
    private List<Permission> permissions;
 

}
