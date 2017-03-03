angular.module("descarteaqui").directive("userForm", function(companyService) {
	return {
        templateUrl: '/app/user/form.html',
        restrict: "EA",
        replace: false,
        transclude: true,
        scope: {
        	user: '=',
        	disable: '@'
        },
        link: function($scope, element, attr){}
    };
});