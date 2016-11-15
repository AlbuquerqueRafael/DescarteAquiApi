angular.module("descarteaqui").controller("companyController", function ($scope, companyService) {
	$scope.data = {}
	$scope.company = {}
	$scope.totalItens = 10;
	
	$scope.getCompanyData = function(){
		var state = $scope.state;
		var company = $scope.company;

		companyService.getAllCompanies(company, state).then(function successCallback(response) {
			$scope.data = response.data.company;
			$scope.totalItens = response.data.size;
	     
		}, function errorCallback(response) {
			console.log(response)
			console.log(response.data.erro)
		});
	}
	
	$scope.info = companyService.getInfo();
	
});