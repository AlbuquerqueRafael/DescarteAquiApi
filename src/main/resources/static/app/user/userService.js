'use strict';

angular.module('descarteaqui').factory('userService', function($http, $window){
    var service = {};
    
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
    
	
	return service;
});