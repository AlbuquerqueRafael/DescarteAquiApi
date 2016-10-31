angular.module("daTable").directive("myBody", function() {
	return {
        templateUrl: 'assets/tableBodyTemplate.html',
        restrict: "A",
        replace: false,
        require: '^myTable',
        transclude: true,
        scope: {
        	data: '='
        },
        link: function($scope, element, attr, myTable){
        }
    };
});