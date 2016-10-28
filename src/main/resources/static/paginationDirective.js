angular.module("descarteaqui").directive("myPagination", function(companyService) {
	return {
        templateUrl: 'assets/paginationTemplate.html',
        restrict: "E",
        replace: false,
        scope: {
        	someCtrlFn: '&callbackFn',
        	data: '='
        },
        link: function($scope, attr){
        	$scope.totalItens = 100;
        	$scope.currentPage = 1;
        	$scope.maxSize = 2;
        	$scope.itensPerPage = 1;
        	
        	
        	$scope.$watch('currentPage', function(newValue, oldValue) {
        		if (newValue !== oldValue) {
        			$scope.someCtrlFn();
    			}
        	});
        	
        	$scope.$watch('data', function(newValue, oldValue) {
        		if (newValue !== oldValue) {
        			$scope.totalItens = $scope.data.length
    			}
        	});
        }
    };
});