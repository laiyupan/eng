/**
 * 
 */
package com.eng.one.nine.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eng.one.nine.vo.RequestInfo;

/**
 * @author laiyp
 * @date 2018年5月14日 
 *
 */
@Controller
@RequestMapping("auth")
public class AuthController {
	private static Logger log = LoggerFactory.getLogger(AuthController.class);
	
	@RequestMapping(value="/login",method=RequestMethod.GET)  
    public String login(Model model){  
        return "login";  
    }  
	
	@RequestMapping(value="/login",method=RequestMethod.POST)  
    public @ResponseBody Object login(@RequestBody RequestInfo info){  
		log.info(info.getAdminName());
        return "ok";  
    } 

}
