'use strict';

angular.module('descarteaqui').factory('userService', function($http, $window){
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
    
	
	return service;
});