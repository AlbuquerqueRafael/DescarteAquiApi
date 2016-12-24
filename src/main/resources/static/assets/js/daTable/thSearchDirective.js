angular.module("daTable").directive("mySearchHeader", function() {
	return {
        templateUrl: 'assets/thSearchTemplate.html',
        restrict: "A",
        replace: false,
        require: "^myTable",
        transclude: true,
        scope: {
        	info: '=',
        	attr: '='
        },
        link: function($scope, element, attr, myTable){
        	$scope.getData = myTable.getData;
        	
        	$scope.getServerData = function(){
        		myTable.state.start = 1;
        		myTable.getData();
        	}
        }
    };
});