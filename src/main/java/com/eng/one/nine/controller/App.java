package com.eng.one.nine.controller;

import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eng.one.nine.entity.AdminUser;
import com.eng.one.nine.service.AdminUserService;
import com.eng.one.nine.service.UserService;
import com.eng.one.nine.utils.AESUtils;

/**
 * Hello world!
 *
 */
@Controller
@RequestMapping("App")
public class App 
{
	private static Logger log = LoggerFactory.getLogger(App.class);
	@Autowired
	private UserService userService;
	
	@Autowired
	private AdminUserService adminUserService;
	
	@RequestMapping(value="/index",method=RequestMethod.GET)  
    public String index(Model model){ 
		System.out.println(SecurityUtils.getSubject().getPrincipal());
        return "index";  
    }  
	
	@RequestMapping(value="/addAdmin",method=RequestMethod.GET)  
    @ResponseBody  
    public Object sayHi() throws Exception{
		AdminUser adminUser=new AdminUser();
		adminUser.setAdminName("laiyupan");
		adminUser.setAdminPassword(AESUtils.aesEncrypt("123456"));
		adminUser.setEmail("461107851@qq.com");
		adminUser.setTelphone("18600598832");
		adminUser.setStatus(0);
		adminUser.setTs(new Date());
		return adminUserService.save(adminUser);
          
    }
	
	/*@RequestMapping("/saveUser")
    @ResponseBody
	public Long saveUser(){
		User user=new User();
		user.setAddress("北京");
		user.setSex("男");
		user.setUserName("lyp");
		user.setBirthDay(new Date());
		
		return this.userDao.save(user).getId();
	}*/
  
    
    
}
