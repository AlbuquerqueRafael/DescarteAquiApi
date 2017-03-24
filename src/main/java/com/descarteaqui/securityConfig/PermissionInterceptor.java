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

import com.descarteaqui.annotation.Permission;
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
		
		if(user.getIsAdmin()){
			return true;
		}
				
		//Checks if the user has the permission role attached to the method
		try{
			Method method = handlerMethod.getMethod();
			Permission roles = method.getAnnotation(Permission.class);
		
		    for (String role : roles.roles()){
		    	if(user.getRoles().contains(role)){
		    		return true;
		    	}
		    }
		
		}catch(NullPointerException e){
			//If the method does not have role, this means that it can be acess by anyone
			return true;
		}
	
		return false;
	}
}