angular.module("descarteaqui").controller("companyController", function ($scope, companyService) {
	$scope.data = {}
	
	var init = function(){
		companyService.getAllCompanies().then(function successCallback(response) {
			$scope.data = response.data;
		  }, function errorCallback(response) {
			console.log(response)
		  });
		
		$scope.info = companyService.getInfo();
	
	};
	
	init();
	
	$scope.state = {}
	$scope.company = {}
	
	$scope.getCompanyData = function(){
		companyService.getAllCompanies().then(function successCallback(response) {
			console.log($scope.state)
			console.log(response);
			$scope.data = response.data;
		}, function errorCallback(response) {
			console.log(response)
		});
	}
	
});