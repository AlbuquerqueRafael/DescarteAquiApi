package com.descarteaqui.securityConfig;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.descarteaqui.annotation.Roles;
import com.descarteaqui.user.AppUser;
import com.descarteaqui.user.UserDAO;

@Component
@Transactional
public class PermissionInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private UserDAO appUserRepository;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
	    HandlerMethod handlerMethod = (HandlerMethod) handler;
	    
	    if(handlerMethod.getMethod().getName().equals("error") ||
	    	handlerMethod.getMethod().getName().equals("login")	||
	    	handlerMethod.getMethod().getName().equals("register")){
	    	return true;
	    }
	    
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedUsername = auth.getName();
		AppUser user = appUserRepository.findByUsername(loggedUsername);
		
		//Checks if the user has the permission role attached to the method
		try{
			Method method = handlerMethod.getMethod();
			Roles roles = method.getAnnotation(Roles.class);
		
		    for (String role : roles.roles()){
		    	if(user.getRoles().contains(role)){
		    		return true;
		    	}
		    }
		
		}catch(NoRoleAttachedException e){
			//If the method does not have role, this means that he can be acess by anyone
			return true;
		}
	
		return false;
	}
}