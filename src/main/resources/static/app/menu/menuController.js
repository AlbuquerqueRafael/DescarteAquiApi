'use strict';

angular.module('descarteaqui').controller('menuController', function($scope, menuService) {
	$scope.menu = menuService.getCurrentMenu();
	
	$scope.$on('$routeChangeStart', function() {
		$scope.menu = menuService.getCurrentMenu();
	});
});