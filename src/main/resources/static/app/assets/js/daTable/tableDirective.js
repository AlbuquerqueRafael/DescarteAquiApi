angular.module("daTable").controller("testeController", function($scope){
	var myTable = this;
	myTable.state = {};
	
	$scope.state.start = 1;
	$scope.state.lengthTable = 10;
	$scope.state.sortValue = 0;
	$scope.state.varSort = $scope.$parent.info[0].input;
	
	this.getDataServer = function(){
		$scope.state.start = myTable.state.start;
		$scope.state.lengthTable = myTable.state.lengthTable;
		$scope.state.varSort = myTable.state.varSort;
		$scope.state.sortValue = myTable.state.sortValue;

		$scope.someCtrlFn();
	}	
	
	$scope.someCtrlFn();
})

.directive("myTable", function() {
	return {
        restrict: "A",
        replace: true,
        controller: 'testeController',
        scope: {
        	someCtrlFn: '&callbackFn',
        	data: '=',
        	state: '='
        },
        link: function($scope, element, attr, myTable){
        	myTable.data = $scope.data;
        	myTable.getData = $scope.someCtrlFn;
        }
    };
});