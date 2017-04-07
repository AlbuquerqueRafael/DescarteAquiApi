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
        	$scope.roles = ['USER', 'COMPANY'];
        	$scope.isAdmin = false;
        	
        	$scope.$parent.$watch('user', function(newValue, oldValue) {
        		if (newValue !== oldValue) {
        		
        			for (index in $scope.user.roles){
        				if($scope.user.roles[index] === 'ADMIN'){
        					$scope.isAdmin = true
        					$scope.user.roles.splice(index, 1);
        					break;
        				}
        				
        				var role = $scope.user.roles[index];
        				var index = $scope.roles.indexOf(role);
        				
        				if(index != -1){
        				  $scope.roles.splice(index, 1);
        				}
        			}
        			
    			}
        	});
        	
        	$scope.addRoles = function(role){
        		//Checking the index of role
        		var index = $scope.roles.indexOf(role);
        		
        	
        		//Push role into list and excluding from select
        		$scope.user.roles.push(role)
        		$scope.roles.splice(index, 1)
        	}
        	
        	$scope.removeRoles = function(role){
        		//Checking the index of role
        		var index = $scope.user.roles.indexOf(role);
        	
        		
        		//Push role into select and excluding from list
        		$scope.roles.push(role)
        		$scope.user.roles.splice(index, 1);
        	}
        	
        	$scope.changeIsAdmin = function(){
        		if($scope.disable == 'false'){
	    			if($scope.isAdmin){
	        			$scope.isAdmin = false;
	        		}else{
	        			$scope.isAdmin = true;
	        		}
        		}
        	
        	}
        }
    };
});