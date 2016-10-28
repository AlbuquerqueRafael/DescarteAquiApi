angular.module("descarteaqui").directive("myBody", function() {
	return {
        templateUrl: 'assets/tableBodyTemplate.html',
        restrict: "A",
        replace: false,
        transclude: true,
        scope: {
        	data: '='
        },
        link: function($scope, attr, element){
        	console.log("ss")
        }
    };
});