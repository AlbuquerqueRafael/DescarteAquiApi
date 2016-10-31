angular.module("daTable").directive("myPagination", function(companyService) {
	return {
        templateUrl: 'assets/paginationTemplate.html',
        restrict: "EA",
        replace: false,
    	require: '^myTable',
        scope: {},
        link: function($scope, element, attr, myTable, $watch){   
        	$scope.totalItens = $scope.$parent.data.length;
        	$scope.currentPage = 1;
        	$scope.maxSize = 5;
        	$scope.itensPerPage = myTable.state.lengthTable;
        	
        	myTable.state.start = $scope.currentPage;
        	
        	$scope.$watch('currentPage', function(newValue, oldValue) {
        		if (newValue !== oldValue) {
        			myTable.state.start = $scope.currentPage;
        			myTable.getDataServer();
    			}
        	});
        	  	
        	$scope.$parent.$watch('data', function(newValue, oldValue) {
        		if (newValue !== oldValue) {
        			$scope.itensPerPage = myTable.state.lengthTable;
        			$scope.totalItens = $scope.$parent.data.length;
        			
    			}
        	});
        }
    };
});