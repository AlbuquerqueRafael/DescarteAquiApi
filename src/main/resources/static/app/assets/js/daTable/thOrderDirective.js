angular.module("daTable").directive("myOrderHeader", function() {
	return {
        templateUrl: '/app/assets/thOrderTemplate.html',
        restrict: "A",
        replace: false,
        require: "^myTable",
        transclude: true,
        scope: {
        	info: '=',
        	attr: '='
        },
        link: function($scope, element, attr, myTable){
        	$scope.info[0].sortIcon = "fa fa-sort-asc";
        	
        	myTable.state.varSort = $scope.info[0].input;
        	myTable.state.sortValue = 0;
        	
        	$scope.order = function(row){
        		var sortBefore = row.sortIcon;
        		var sortValue;
        		
        		for(var i = 0; i < $scope.info.length; i++){
        			$scope.info[i].sortIcon = "fa fa-sort";
        		}
        		
        		if(sortBefore === "fa fa-sort-asc"){
        			row.sortIcon = "fa fa-sort-desc";
        			sortValue = 1;
        		}else{
        			row.sortIcon = "fa fa-sort-asc";
        			sortValue = 0;
        		}
        		
        		myTable.state.varSort = row.input;
        		myTable.state.sortValue = sortValue;
        		
        		myTable.getDataServer();
        	};
        	
        }
    };
});