'use strict';

angular.module("descarteaqui", ['daTable', 'ngRoute'])

.config(function($locationProvider, $routeProvider) {
  
	$routeProvider
    .when('/', {
      templateUrl: '/company/index.html',
      controller: 'companyController'
    })
    
    .when('/company/create', {
      templateUrl: '/company/companyCreate.html'
    })
    
    .otherwise({
      redirectTo: '/'
    });
    
    $locationProvider.html5Mode({
        enabled: true,
        requireBase: false
    });
});