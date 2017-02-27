angular.module("descarteaqui").directive("companyForm", function(companyService) {
	return {
        templateUrl: '/app/company/form.html',
        restrict: "EA",
        replace: false,
        transclude: true,
        scope: {
        	company: '=',
        	disable: '@'
        },
        link: function($scope, element, attr){}
    };
});