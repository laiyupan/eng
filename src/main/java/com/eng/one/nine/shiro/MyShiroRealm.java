/**
 * 
 */
package com.eng.one.nine.shiro;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.eng.one.nine.entity.AdminUser;
import com.eng.one.nine.service.AdminUserService;

/**
 * @author laiyp
 * @date 2018年5月6日 
 *
 */
public class MyShiroRealm extends AuthorizingRealm{
	private final static Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);

	@Autowired
	private AdminUserService adminUserService;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		logger.info("不进行权限处理");
		/*logger.debug("##################执行Shiro权限认证##################");
        AdminUser user = (AdminUser) principalCollection.getPrimaryPrincipal();
        if(user!=null){
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            Role role = roleService.getById(user.getRole().getRId());
            List<Permission> permissions = role.getPermissions();
            for (Permission p : permissions){
                info.addStringPermission(p.getPAlias());
            }
            info.addRole(role.getRAlias());
            return info;
        }*/
        return null;
	}

	/* (non-Javadoc)
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        logger.info("用户验证执行 : "+token.getUsername());
        AdminUser user = adminUserService.findByAdminName(token.getUsername());
        if(user==null){
            logger.error("用户 { "+token.getUsername()+" } 不存在 ");
            throw new AccountException("账户不存在");
        }
        if(user.getStatus()==0){
            logger.error("用户 { "+token.getUsername()+" } 被禁止登录 ");
            throw new DisabledAccountException("账号已经禁止登录");
        }else{
            logger.info("用户 { "+token.getUsername()+" }存在..开始进行密码校验");
        }
        return new SimpleAuthenticationInfo(user,user.getAdminPassword(),getName());
	}
	
	@PostConstruct
    public void initCredentialsMatcher() {
        //该句作用是重写shiro的密码验证，让shiro用我自己的验证
        setCredentialsMatcher(new CredentialsMatcher());
 
    }

}
