'use strict';

angular.module("descarteaqui").controller("companyController", 
		function ($scope, companyService, $routeParams, $location) {
	$scope.data = {}
	$scope.company = {}
	$scope.totalItens = 10;
	$scope.state = {}
	$scope.info = companyService.getInfo();
	
	$scope.getCompanyData = function(){
		var state = $scope.state;
		var company = $scope.company;
		
		companyService.getAllCompanies(company, state).then(function successCallback(response) {
			$scope.data = response.data.company;
			$scope.totalItens = response.data.size;
		}, function errorCallback(response) {
			console.log(response)
			console.log(response.data.error)
		});
	}
	
	$scope.create = function(){
		var company = $scope.company;
		companyService.create(company).then(function successCallback(response) {
			$location.path("/");
		}, function errorCallback(response, error) {
			
		});
	}
	
	if($routeParams.id !== undefined){
		companyService.getCompanyById($routeParams.id).then(function successCallback(response) {
			$scope.company = response.data;
		}, function errorCallback(response) {
			console.log(response);
			console.log(response.data.error);
			$location.path("/");
		});
	}
	
	$scope.del = function(){
		companyService.deleteById($routeParams.id).then(function successCallback(response) {
			console.log(response.data.result)
			$location.path("/")
		}, function errorCallback(response) {
			console.log(response);
		});
	}
	
	
	
	
});