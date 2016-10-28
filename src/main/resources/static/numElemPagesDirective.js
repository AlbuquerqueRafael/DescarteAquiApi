angular.module("descarteaqui").directive("myNumElements", function(companyService) {
	return {
        templateUrl: 'assets/numPagesTemplate.html',
        restrict: "E",
        replace: false,
        scope: {
        	someCtrlFn: '&callbackFn',
        	data: '='
        },
        link: function($scope, attr){
        	$scope.elements = [5, 10, 50];
        	$scope.radioModel = 10;
        	$scope.$watch('radioModel', function(newValue, oldValue) {
        		console.log($scope.radioModel);
        	});
        }
    };
});