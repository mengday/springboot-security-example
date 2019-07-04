package com.example.security.authentication.social;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 所有bean初始化前后都会经过这个类的方法
 * @author Administrator
 *
 */
@Component
public class SpringSocialBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if ("mySpringSocialConfigurer".equals(beanName)) {
			MySpringSocialConfigurer configurer = (MySpringSocialConfigurer) bean;
			//三方用户openid不存在于user_connection表时，重定向的路径
			configurer.signupUrl("/social/signup");
			return configurer;
		}
		return bean;
	}

}
