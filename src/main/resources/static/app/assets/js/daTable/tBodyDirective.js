angular.module("daTable").directive("myBody", function($location) {
	return {
        templateUrl: '/app/assets/js/daTable/templates/tBodyTemplate.html',
        restrict: "A",
        replace: false,
        require: '^myTable',
        transclude: true,
        scope: {
        	data: '=',
        	showUrl: '@showUrl'
        },
        link: function($scope, element, attr, myTable){
        	
        	$scope.route = function(row){
        		$location.path("/company/" + row.id + "/show");
        	}
        	
        }
    };
});