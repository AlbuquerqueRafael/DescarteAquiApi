'use strict';

angular.module("descarteaqui", ['daTable', 'ngRoute'])

.config(function($locationProvider, $routeProvider) {
  
	$routeProvider
    .when('/', {
    	templateUrl: '/app/user/login.html',
    	controller: 'userController'
    })
    
     .when('/company', {
    	templateUrl: '/app/company/index.html',
    	controller: 'companyController',
    	roles: ["USER"]
    })
    
    .when('/company/create', {
    	templateUrl: '/app/company/create.html',
    	controller: 'companyController',
    	roles: ["USER"],
    	css: '/app/assets/css/page.css'
    })
    
    .when('/company/:id/show', {
    	templateUrl: '/app/company/show.html',
    	controller: 'companyController',
    	roles: ["USER"]
  	})
   
  	.when('/company/:id/edit', {
    	templateUrl: '/app/company/edit.html',
    	controller: 'companyController',
    	roles: ["USER"]
    })
    
    .when('/user/login/', {
    	templateUrl: '/app/user/login.html',
    	controller: 'userController'
    }) 
    
    .when('/user', {
    	templateUrl: '/app/user/index.html',
    	controller: 'userController',
    	roles: ["USER"]
    })
    
    .when('/user/:id/show', {
    	templateUrl: '/app/user/show.html',
    	controller: 'userController',
    	roles: ["USER"]
    })
    
     .when('/user/:id/edit', {
    	templateUrl: '/app/user/edit.html',
    	controller: 'userController',
    	roles: ["USER"]
    })
    
     .when('/user/create', {
    	templateUrl: '/app/user/create.html',
    	controller: 'userController',
    	roles: ["USER"]
    })
    
    .otherwise({
      redirectTo: '/'
    });
    
    $locationProvider.html5Mode({
        enabled: true,
        requireBase: false
    });
    
})

.run(function($rootScope, $location, $http, userService){
  $rootScope.$on('$routeChangeStart', function(event, next, current) {
	var user = JSON.parse(userService.getLoggedUser());
	
	//Changes the main page depending if the user is logged or not and deals with logout
	if ($location.path() === "/sair") {	
		userService.logout();
		$location.path("/");
	}else if($location.path() === "/" && user !== null){
    	$location.path("/company");
    }
	
	if(user !== null){
    	$http.defaults.headers.common['Authorization'] = 'Bearer ' + user.token;
    }
	
	//Checks if the user has the permission to acess the route
	if(next.$$route != undefined && next.$$route.roles != undefined){
		var userRoles = user.user.roles;
		var routRoles = next.$$route.roles;
		var hasPermission = false;
		
		if(userRoles.indexOf("ADMIN") != -1){
			hasPermission = true;
		}else{
			for(var index in userRoles){
				if(routRoles.indexOf(userRoles[index]) != -1){
					hasPermission = true;
					break;
				}
			}
		}
		
		if(!hasPermission){
			event.preventDefault();
		}
	
	}
 

  });
});
