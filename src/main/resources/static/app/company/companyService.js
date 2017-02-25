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
	
	service.create = function(company){
		return $http.post("/company/create", company);
	}
	
	service.getCompanyById = function(id){
		return $http.put("/company/show/" + id);
	}
	
	service.deleteById = function(id){
		return $http.delete("/company/delete/" + id);
	}
	
	return service;
});