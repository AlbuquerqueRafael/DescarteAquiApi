angular.module("descarteaqui").directive("userForm", function(companyService) {
	return {
        templateUrl: '/app/user/form.html',
        restrict: "EA",
        replace: false,
        transclude: true,
        scope: {
        	user: '=',
        	messages: '=',
        	disable: '@'
        },
        link: function($scope, element, attr){
        	$scope.roles = ['USER'];
        	
        	if(!$scope.user.roles){
        		$scope.user.roles = []
        	}
     
        	$scope.user.isAdmin = false;
        	
        	$scope.addRoles = function(role){
        		if ( role != undefined){
	    			var index = $scope.roles.indexOf(role);
	        		var auxRole = $scope.roles[index];
	        		
	        		$scope.roles.splice(index, 1)
	        		$scope.user.roles.push(auxRole);
        		}
        	}
        	
        	$scope.removeRoles = function(role){
        		var index = $scope.user.roles.indexOf(role);
        		var auxRole = $scope.user.roles[index];
        		
        		$scope.user.roles.splice(index, 1)
        		$scope.roles.push(auxRole);
        		
        	}
        	
        	$scope.$parent.$watch('user', function(newValue, oldValue) {
        		if (newValue !== oldValue) {
        			
        			for (index in $scope.user.roles){
        				if($scope.user.roles[index] === 'ADMIN'){
        					$scope.isAdmin = true
        				}
        			}
        			
        			if($scope.isAdmin){
        				for (index in $scope.user.roles){
            				$scope.addRoles($scope.user.roles[index])
            			}
        			}
    			}
        	});
        }
    };
});