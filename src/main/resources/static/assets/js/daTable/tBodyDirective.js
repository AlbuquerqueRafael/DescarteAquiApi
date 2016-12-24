angular.module("daTable").directive("myBody", function() {
	return {
        templateUrl: 'assets/tBodyTemplate.html',
        restrict: "A",
        replace: false,
        require: '^myTable',
        transclude: true,
        scope: {
        	data: '='
        }
    };
});