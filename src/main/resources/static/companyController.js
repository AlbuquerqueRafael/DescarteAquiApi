angular.module("descarteaqui").controller("companyController", function ($scope, companyService) {
	$scope.data = {}
	console.log()
	var init = function(){
		var state = $scope.state;
		var company = $scope.company;
		
		companyService.getAllCompanies(company, state).then(function successCallback(response) {
			$scope.data = response.data;
		  }, function errorCallback(response) {
			console.log(response)
		  });
		
		$scope.info = companyService.getInfo();
	
	};
	
	init();
	$scope.company = {}
	
	$scope.getCompanyData = function(){
		var state = $scope.state;
		var company = $scope.company;
		
		companyService.getAllCompanies(company, state).then(function successCallback(response) {
			$scope.data = response.data;
		}, function errorCallback(response) {
			console.log(response.data.message)
		});
	}
	
});