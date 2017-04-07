'use strict';

angular.module('descarteaqui').controller('userController', 
		function($scope, $http, userService, $window, $rootScope, $location, $routeParams){
	
	$scope.user = {}
	$scope.data = {}
	$scope.company = {}
	$scope.totalItens = 10;
	$scope.state = {}
	$scope.info = userService.getInfo();
	$scope.currenteId = $routeParams.id;
	
	// method for login
	$scope.login = function() {
		// requesting the token by usename and passoword
		userService.login($scope.user.mail, $scope.user.password).then(function(res) {
				$scope.password = null;
				// checking if the token is available in the response
				if (res.data.token) {
					userService.authUser(res)
				} else {
					// if the token is not present in the response then the
					// authentication was not successful. Setting the error message.
					$scope.messages = []
					// if authentication was not successful. Setting the error message.
					$scope.messages.push(response.data.error)
				}
			}, function errorCallback(response)  {
				console.log(response)
				$scope.messages = []
				// if authentication was not successful. Setting the error message.
				$scope.messages.push(response.data.error)
			});
	};
	
	$scope.getUserData = function(){
		var state = $scope.state;
		var user = JSON.parse(JSON.stringify($scope.user));
		
		if($scope.user.roles !== undefined){
			user.roles = []
			user.roles.push($scope.user.roles)
		}
	
		userService.getAllUsers(user, state).then(function successCallback(response) {
			$scope.data = response.data.user;
			$scope.totalItens = response.data.size;
		}, function errorCallback(response) {
			console.log(response)
			console.log(response.data.error)
		});
	}
	
	if($scope.currenteId !== undefined){
		userService.getCompanyById($scope.currenteId).then(function successCallback(response) {
			$scope.user = response.data;
			$scope.user.email = response.data.username;
		}, function errorCallback(response) {
			console.log(response);
			console.log(response.data.error);
			$location.path("/");
		});
	}
	
	$scope.create = function(){
		if($scope.user.password !== $scope.user.repeatPassword){
			$scope.messages = ['Password and Repeat Password doesn\'t match']
		}else{
			userService.save($scope.user).then(function successCallback(response) {
				console.log("Works")
				console.log(response)
			}, function errorCallback(response) {
				$scope.messages = ['Password and Repeat Password doesn\'t match']
			});	
		}
		
	}
	
	
	
});