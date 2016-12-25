'use strict';

angular.module("descarteaqui", ['daTable', 'ngRoute'])

.config(function($locationProvider, $routeProvider) {
  
	$routeProvider
    .when('/', {
      templateUrl: '/company/index.html',
      controller: 'companyController'
    })
    
    .when('/company/create', {
      templateUrl: '/company/companyCreate.html',
      controller: 'companyController'
    })
    
     .when('/company/show/:id', {
      templateUrl: '/company/show.html',
      controller: 'companyController'
    })
    
    .otherwise({
      redirectTo: '/'
    });
    
    $locationProvider.html5Mode({
        enabled: true,
        requireBase: false
    });
});