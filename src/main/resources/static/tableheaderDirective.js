angular.module("descarteaqui").directive("myHeader", function() {
	return {
        templateUrl: 'assets/template.html',
        restrict: "A",
        replace: false,
        transclude: true,
        scope: {
        	info: '=',
        	someCtrlFn: '&callbackFn',
        	attr: '='
        },
        link: function($scope, attr, element){
        	$scope.order = function(row){
        		var sortBefore = row.sortIcon;
        		
        		for(var i = 0; i < $scope.info.length; i++){
        			$scope.info[i].sortIcon = "fa fa-sort";
        		}
        		
        		if(sortBefore === "fa fa-sort-asc"){
        			row.sortIcon = "fa fa-sort-desc";
        		}else{
        			row.sortIcon = "fa fa-sort-asc";
        		}
        		
        		$scope.someCtrlFn();
        	};
        	
        }
    };
});