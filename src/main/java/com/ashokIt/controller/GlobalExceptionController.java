package com.ashokIt.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionController {
	@ExceptionHandler(Exception.class)
	public ModelAndView displayErrorpage(Exception e) {
		ModelAndView modelAndView = new ModelAndView("errorpage");
		modelAndView.addObject("error_msg", e.getMessage());
		return modelAndView;
	}
}
