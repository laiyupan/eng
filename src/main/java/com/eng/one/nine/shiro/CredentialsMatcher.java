package com.eng.one.nine.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eng.one.nine.utils.AESUtils;

public class CredentialsMatcher extends SimpleCredentialsMatcher {
 
    private final static Logger LOGGER = LoggerFactory.getLogger(CredentialsMatcher.class);
 
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken authcToken = (UsernamePasswordToken) token;
        Object tokenCredentials =null;
        LOGGER.info("密码："+authcToken.getPassword()+"进行加密");
		try {
			tokenCredentials = AESUtils.aesEncrypt(String.valueOf(authcToken.getPassword()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Object accountCredentials = getCredentials(info);
        return accountCredentials.equals(tokenCredentials);
    }
}