angular.module("daTable").directive("myNumElements", function(companyService) {
	return {
        templateUrl: '/app/assets/numPagesTemplate.html',
        restrict: "E",
        replace: false,
        require: '^myTable',
        transclude: true,
        scope: {
        	totalItens: '='
        },
        link: function($scope, element, attr, myTable){
        	        	
        	myTable.state.lengthTable = $scope.totalItens;
        	
        	$scope.$watch('totalItens', function(newValue, oldValue) {
       
        		if (newValue !== oldValue) {
        			myTable.state.lengthTable = $scope.lengthTable;
        			myTable.getDataServer();
    			}
        	});
        	
        	
        }
    };
});