package by.htp.news.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	public static final String ERROR_PAGE = "error";

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
       
        return ERROR_PAGE;
    }

}

