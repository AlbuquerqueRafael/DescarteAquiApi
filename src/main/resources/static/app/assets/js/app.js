'use strict';

angular.module("descarteaqui", ['daTable', 'ngRoute'])

.config(function($locationProvider, $routeProvider) {
  
	$routeProvider
    .when('/', {
    	templateUrl: '/app/user/login.html',
    	controller: 'userController',
    	requireLogin : false
    })
    
     .when('/company', {
    	templateUrl: '/app/company/index.html',
    	controller: 'companyController',
    	requireLogin : true
    })
    
    .when('/company/create', {
    	templateUrl: '/app/company/companyCreate.html',
    	controller: 'companyController',
    	requireLogin : true
    })
    
    .when('/user/login/', {
    	templateUrl: '/app/user/login.html',
    	controller: 'userController',
    	requireLogin : false
    }) 
    
    .when('/company/show/:id', {
      templateUrl: '/app/company/show.html',
      controller: 'companyController',
      requireLogin : true
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
	var user = userService.getLoggedUser();
	
	if ($location.path() === "/sair") {	
		userService.logout();
		$location.path("/");
	}else if($location.path() === "/" && user !== null){
    	$location.path("/company");
    }
	
    //Algoritmo para não deixar algum usario entrar sem estar logado
    if (next.$$route === undefined || 
    	(next.$$route.requireLogin && user === null))  {
       $location.path("/");
    }
   


    //$http.interceptors.push('ApiInterceptorService');

  });
});