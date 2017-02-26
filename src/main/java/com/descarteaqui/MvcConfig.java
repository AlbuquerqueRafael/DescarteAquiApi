package com.descarteaqui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.descarteaqui.securityConfig.PermissionInterceptor;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private PermissionInterceptor permInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(permInterceptor).addPathPatterns("/**");
	}

}