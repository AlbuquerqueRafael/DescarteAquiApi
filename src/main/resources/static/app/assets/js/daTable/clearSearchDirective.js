angular.module("daTable").directive("myClearSearch", function(companyService) {
	return {
        templateUrl: '/app/assets/js/daTable/templates/clearSearchTemplate.html',
        restrict: "EA",
        replace: false,
        require: '^myTable',
        transclude: true,
        scope: {
        	attr: '='
        },
        link: function($scope, element, attr, myTable){
        	
        	$scope.clearSearch = function(){
        		deleteProperties($scope.attr);
        		myTable.state.start = 1;
        		myTable.getDataServer();
        	}
        	
        	var deleteProperties = function(objectToClean) {
        		  for (var x in objectToClean){
        			  if (objectToClean.hasOwnProperty(x)){
        				  delete objectToClean[x];
        		  	}
        		  }
        	}
        	
        }
    };
});