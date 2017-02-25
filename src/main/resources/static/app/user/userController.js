'use strict';

angular.module('descarteaqui').controller('userController', 
		function($scope, $http, userService, $window, $rootScope, $location){
	
	$scope.user = {}
	// method for login
	$scope.login = function() {
		// requesting the token by usename and passoword
		userService.login($scope.user.mail, $scope.user.password)
			.success(function(res) {
				$scope.password = null;
				// checking if the token is available in the response
				if (res.token) {
					$scope.message = '';
					// setting the Authorization Bearer token with JWT token
					$http.defaults.headers.common['Authorization'] = 'Bearer ' + res.token;
					
					// setting the user in AuthService
					$window.sessionStorage.setItem('user', res.user);
					console.log($window.sessionStorage.getItem('user'))
					$rootScope.$broadcast('LoginSuccessful');
					$location.path('/company');
					// going to the home page
				} else {
					// if the token is not present in the response then the
					// authentication was not successful. Setting the error message.
					$scope.message = 'Authetication Failed !';
				}
			}).error(function(error) {
				console.log(error)
				// if authentication was not successful. Setting the error message.
				$scope.message = 'Authetication Failed !';
			});
	};
});