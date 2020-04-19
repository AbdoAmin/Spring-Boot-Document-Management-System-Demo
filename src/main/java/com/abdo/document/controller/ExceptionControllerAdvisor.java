package com.abdo.document.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvisor {

    //TODO:Handel Each exception and response with descriptive error page
    @ExceptionHandler(Exception.class)
    public String handelAllExceptions(Exception e) {
        return "error";
    }
}
