'use strict';

angular.module("descarteaqui").factory("menuService", function($location, userService){
  var service = {};
  
  service.getInitMenu = function(){
    var menus = [
      {path : "/", href : "/", nome: "Inicio", class:"home", slide:1},
      {path : "/sobre", href : "/sobre", nome: "Sobre", class: "star", slide:2},
    ];

    return menus;
  };


  service.getUserMenu = function(){
    var menus = [
      {path : "/user", href : "/user", nome: "User", class:"user"},
      {path : "/company", href : "/company", nome: "Company", class:"building-o"},
      {path : "/sair", href: "/sair", nome: "Sair", class: "leaf"}
    ];

    return menus;
  };
  
  service.getCurrentMenu = function(){
	  var user = userService.getLoggedUser();
	 
	  if(user !== null){
		  return service.getUserMenu();
	  }else{
		  return service.getInitMenu();
	  }
  }

  return service;
});