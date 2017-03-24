angular.module("descarteaqui").config(function ($httpProvider) {
	$httpProvider.interceptors.push("errorInterceptor");
});