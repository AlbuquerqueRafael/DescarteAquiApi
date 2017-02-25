package com.descarteaqui;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController implements ErrorController{

    private static final String PATH = "/error";
    
    //This method avoid the white blank page and makes angularjs deals with the routes
    @RequestMapping(value = PATH)
    public String error() {
    	return "forward:/";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}