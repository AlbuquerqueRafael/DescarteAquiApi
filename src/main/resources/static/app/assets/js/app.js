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
    	roles: ['USER']
    })
    
    .when('/company/create', {
    	templateUrl: '/app/company/companyCreate.html',
    	controller: 'companyController',
    	roles: ['ADMIN']
    })
    
    .when('/user/login/', {
    	templateUrl: '/app/user/login.html',
    	controller: 'userController'
    }) 
    
    .when('/company/show/:id', {
      templateUrl: '/app/company/show.html',
      controller: 'companyController',
      roles: ['ADMIN']
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
	
	//Checks if the user has the permission to acess a route
	if(next.$$route.roles != undefined){
		var userRoles = user.user.roles;
		var routRoles = next.$$route.roles;
		var hasPermission = false;
		
		for(var index in userRoles){
			if(routRoles.indexOf(userRoles[index]) != -1){
				hasPermission = true;
				break;
			}
		}
		
		if(!hasPermission){
			event.preventDefault();
		}
	
	}

    if(user !== null){
    	$http.defaults.headers.common['Authorization'] = 'Bearer ' + user.token;
    }
 

  });
});
