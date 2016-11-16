angular.module("daTable").directive("myPagination", function(companyService) {
	return {
        templateUrl: 'assets/paginationTemplate.html',
        restrict: "EA",
        replace: false,
    	require: '^myTable',
		scope: {
			totalItens: '='
	    },
        link: function($scope, element, attr, myTable, $watch){ 
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
    			}
        	});
        	
            $scope.$watch(function () {
                return  myTable.state.start;
            },  function(newValue, oldValue) {
        		if (newValue !== oldValue) {
        			$scope.currentPage = myTable.state.start;
    			}
        	});
        	
           	myTable.getDataServer();
        }
    };
});