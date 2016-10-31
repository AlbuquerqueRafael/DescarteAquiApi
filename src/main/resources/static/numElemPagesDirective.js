angular.module("daTable").directive("myNumElements", function(companyService) {
	return {
        templateUrl: 'assets/numPagesTemplate.html',
        restrict: "E",
        replace: false,
        require: '^myTable',
        transclude: true,
        scope: {},
        link: function($scope, element, attr, myTable){
        	
        	$scope.lengthTable = 10;
        	myTable.state.lengthTable = $scope.lengthTable;
        	
        	$scope.$watch('lengthTable', function(newValue, oldValue) {
        		if (newValue !== oldValue) {
        			myTable.state.lengthTable = $scope.lengthTable;
        			myTable.getDataServer();
    			}
        	});
        	
        	
        }
    };
});