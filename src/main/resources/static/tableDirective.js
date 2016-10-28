angular.module("descarteaqui").directive("myTable", function(companyService) {
	return {
        restrict: "A",
        replace: false,
        scope: {
        	someCtrlFn: '&callbackFn',
        	data: '='
        },
        link: function($scope, attr, element){
        	$scope.numPages = 2;
        	$scope.totalItens = 50;
        	$scope.currentPage = 1;
        	
        	$scope.$watch('data', function(newValue, oldValue) {
        		console.log($scope.data.length)
        		$scope.totalItens = $scope.data.length
        	});
        }	
    };
});