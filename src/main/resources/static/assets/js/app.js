'use strict';

angular.module("descarteaqui", ['daTable', 'ngRoute'])

.config(function($locationProvider, $routeProvider) {
    $locationProvider.html5Mode(true);
    
    $routeProvider
    .when('/', {
      templateUrl: 'company/index.html',
      controller: 'companyController'
    })
    
    .when('/company/create', {
      templateUrl: 'company/companyCreate.html'
    })
    
    .otherwise({
      redirectTo: '/'
    });
    console.log();
});