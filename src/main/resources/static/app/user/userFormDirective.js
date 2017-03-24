angular.module("descarteaqui").directive("userForm", function(companyService) {
	return {
        templateUrl: '/app/user/form.html',
        restrict: "EA",
        replace: false,
        transclude: true,
        scope: {
        	user: '=',
        	disable: '@'
        },
        link: function($scope, element, attr){
        	$scope.roles = ['USER', 'TESTE', 'AINDA TESTANDO'];
        	$scope.selectedRoles = []
        
        	
        	$scope.addRoles = function(role){
        		if ( role != undefined){
	    			var index = $scope.roles.indexOf(role);
	        		var auxRole = $scope.roles[index];
	        		
	        		$scope.roles.splice(index, 1)
	        		$scope.selectedRoles.push(auxRole);
        		}
        	}
        	
        	$scope.removeRoles = function(role){
        		console.log(role)
        		var index = $scope.selectedRoles.indexOf(role);
        		var auxRole = $scope.selectedRoles[index];
        		
        		$scope.selectedRoles.splice(index, 1)
        		$scope.roles.push(auxRole);
        		
        	}
        	
        	$scope.$parent.$watch('user', function(newValue, oldValue) {
        		if (newValue !== oldValue) {
        			console.log($scope.user)
        			for (index in $scope.user.roles){
        				if($scope.user.roles[index] === 'ADMIN'){
        					$scope.isAdmin = true
        				}
        			}
    			}
        	});
        }
    };
});