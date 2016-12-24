angular.module('descarteaqui').factory('companyService', function($http){
    var service = {};
    
    service.getInfo = function(){
    	var data = [{
    		name: "Name",
    		type: "text",
    		sortIcon: "fa fa-sort",
    		input: "name"
    	},{
    		name: "Adress",
    		type: "text",
    		sortIcon: "fa fa-sort",
    		input: "adress"
    	},{
    		name: "Phone",
    		type: "text",
    		sortIcon: "fa fa-sort",
    		input: "phone"
    	}];
    	
    	return data;
    }
    
	service.getAllCompanies = function(company, state){
		var data = { company: company, state: state};
		return $http.post("/company", data);
	}
	
	return service;
});