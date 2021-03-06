'use strict';

angular.module('descarteaqui').factory('userService', function($http, $window, $location){
    var service = {};
    
    service.getInfo = function(){
    	var data = [{
    		name: "Name",
    		type: "text",
    		sortIcon: "fa fa-sort",
    		input: "name"
    	},{
    		name: "Email",
    		type: "text",
    		sortIcon: "fa fa-sort",
    		input: "username"
    	},{
    		name: "Roles",
    		type: "text",
    		sortIcon: "fa fa-sort",
    		input: "roles"
    	}];
    	
    	return data;
    }
    
    service.login = function(mail, pass){
    	var data = { username: mail, password: pass};
    	return $http({
			url : '/user/login',
			method : "POST",
			params : {
				username : mail,
				password : pass
			}
    	});
    }
    
    service.getLoggedUser = function(){
    	return $window.sessionStorage.getItem('user')
    }
    
    service.logout = function(){
    	return $window.sessionStorage.clear();
    }
    
    service.getAllUsers = function(user, state){
		var data = { user: user, state: state};
		return $http.post("/user/info", data);
	}
    
    service.authUser = function(res){
    	// setting the Authorization Bearer token with JWT token
		$http.defaults.headers.common['Authorization'] = 'Bearer ' + res.data.token;
		// setting the user in AuthService
		$window.sessionStorage.setItem('user', JSON.stringify(res.data));
	
		$location.path('/company');
    }
    
    service.getCompanyById = function(id){
		return $http.put("/user/" + id + "/show");
	}
	
	service.deleteById = function(id){
		return $http.delete("/user/" + id + "/delete");
	}
	
	service.update = function(user){
		return $http.put("/user/edit", user);
	}
	
	service.save = function(user){
		return $http.post("/user/register", user);
	}
	
	return service;
});