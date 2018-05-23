/**
 * 
 */
package com.eng.one.nine.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eng.one.nine.base.BaseParams;
import com.eng.one.nine.base.BaseService;
import com.eng.one.nine.entity.AdminUser;
import com.eng.one.nine.utils.ListUtils;

/**
 * @author laiyp
 * @date 2018年5月6日 
 *
 */
@Service("AdminUserService")
public class AdminUserService extends BaseService<AdminUser>{
	
	
	public AdminUser findByAdminName(String adminName){
		List<BaseParams> baseParams=new ArrayList<>();
		baseParams.add(new BaseParams("adminName", String.class, adminName));
		List<AdminUser> adminUsers=this.findWithParamsList(baseParams, pageable);
		if(ListUtils.isNullOrEmptyList(adminUsers)) return adminUsers.get(0);
		
		return null;
	}

}
