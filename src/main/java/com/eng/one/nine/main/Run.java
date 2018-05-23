package com.eng.one.nine.main;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * @author lyp
 * 第一种方式 
 * 通过在UserController中加上@EnableAutoConfiguration开启自动配置，然后通过SpringApplication.run(UserController.class);
 * 运行这个控制器；这种方式只运行一个控制器比较方便； 
 * 第二种方式 
 * 通过@Configuration+@ComponentScan开启注解扫描并自动注册相应的注解Bean 
 * 第三种方式
 * @SpringBootApplication取代了Spring项目中的@Configuration、@EnableAutoConfiguration、@ComponentScan。因此，我们所有的bean需要在BootTestApplication的同级目录下被扫描
 * 不同级则加上@SpringBootApplication(scanBasePackages="扫描包路径")
 * 也可以@ComponentScan(basePackages = "扫描包路径")
 *	
 * 运行Application类即可发布整个应用 
 */
@EnableJpaRepositories(basePackages = "com.eng.one.nine.dao") //整合hirbernate,扫描Dao层
@SpringBootApplication(scanBasePackages="com")
@EntityScan("com.eng.one.nine.entity")
@EnableTransactionManagement//启动事务管理
public class Run extends SpringBootServletInitializer{
	@Override
	  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	    // 注意这里要指向原先用main方法执行的Application启动类
	    return builder.sources(Run.class);
	  }
}
