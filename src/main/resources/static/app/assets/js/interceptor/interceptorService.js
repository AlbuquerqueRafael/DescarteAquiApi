angular.module("descarteaqui").factory("errorInterceptor",	
		['$q', '$location', '$window',
		function ($q, $location, $window) {
  return {
    responseError: function (rejection) {
    	console.log(rejection)
     if (rejection.status == 401) {
    	 $window.sessionStorage.clear();
    	 alert("Session expired. Please make the login again")
    	 $location.path("/");
      }

      return $q.reject(rejection);
    }
  };
}]);