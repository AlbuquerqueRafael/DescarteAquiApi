package com.descarteaqui.user;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.descarteaqui.annotation.Permission;
import com.descarteaqui.state.State;
import com.descarteaqui.state.StateService;
import com.fasterxml.jackson.databind.JsonNode;

@RestController
public class UserController {
	@Autowired
	private UserDAO appUserRepository;

	
	@Autowired
	private UserService userService;
	
	@Autowired
	private StateService tableStateService;
	
	@RequestMapping(value = "/user/info", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> getUsers(@RequestBody String searchData) 
			throws IOException{
		Map<String, Object> model = new HashMap<String, Object>();

		JsonNode node = userService.initNode(model, searchData);
		AppUser user = userService.initCompany( node);
		State tableState = tableStateService.initTableState( node);
		PageRequest request = tableStateService.getPageRequest(tableState);
		
		model = userService.getSearch(user, request, tableState);
		
		return new ResponseEntity<Map<String, Object>>(model, HttpStatus.OK);
	}
	
	/**
	 * This method is used for user registration. Note: user registration is not
	 * require any authentication.
	 * 
	 * @param appUser
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<AppUser> createUser(@RequestBody AppUser appUser) {
		if (appUserRepository.findByUsername(appUser.getUsername()) != null) {
			throw new RuntimeException("Username already exist");
		}
		
		List<String> roles = new ArrayList<>();
		roles.add("USER");
		appUser.setRoles(roles);
		return new ResponseEntity<AppUser>(appUserRepository.save(appUser), HttpStatus.CREATED);
	}

	/**
	 * This method will return the logged user.
	 * 
	 * @param principal
	 * @return Principal java security principal object
	 */
	@RequestMapping("/user")
	public AppUser user(Principal principal) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedUsername = auth.getName();
		return appUserRepository.findByUsername(loggedUsername);
	}

	
	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> login(@RequestParam String username, 
							 @RequestParam String password) throws IOException {
		
		AppUser appUser = appUserRepository.findByUsername(username);
		Map<String, Object> tokenMap = new HashMap<String, Object>();
		
		tokenMap = userService.checkAuth(appUser, password, username);
		
		return new ResponseEntity<Map<String, Object>>(tokenMap, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/user/{id}/show", method = RequestMethod.PUT)
	public AppUser show(@PathVariable("id") Long id) {
		return userService.getCompanyById(id);
	}
	
	@Permission(roles={"USER"})
	@RequestMapping(value = "/user/edit", method = RequestMethod.PUT)
	public ResponseEntity<Map<String, Object>> edit(@RequestBody AppUser user) {
		userService.saveCompany(user);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("Status", "User was updated successfully");
		
		return new ResponseEntity<Map<String, Object>>(model, HttpStatus.OK);
	}
	
	@Permission(roles={"USER"})
	@RequestMapping(value = "/user/{id}/delete", method = RequestMethod.DELETE)
	public ResponseEntity<Map<String, Object>> delete(@PathVariable("id") Long id) {
		String result = userService.deleteCompanyById(id);
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("result", result);
		
		return new ResponseEntity<Map<String, Object>>(model, HttpStatus.OK);
	}
}
