angular.module("daTable").directive("myNumElements", function(companyService) {
	return {
        templateUrl: '/app/assets/numPagesTemplate.html',
        restrict: "E",
        replace: false,
        require: '^myTable',
        transclude: true,
        scope: {},
        link: function($scope, element, attr, myTable){
        	
        	$scope.numElements = 10;
        	myTable.state.lengthTable = $scope.numElements;
        	
        	$scope.$watch('numElements', function(newValue, oldValue) {
       
        		if (newValue !== oldValue) {
        			myTable.state.lengthTable = $scope.numElements;
        			myTable.getDataServer();
    			}
        	});
        		
        }
    };
});