package com.udacity.jwdnd.course1.cloudstorage.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlingController {

  @ExceptionHandler(MaxUploadSizeExceededException.class)
  public ModelAndView handleError(HttpServletRequest req, Exception e) {
    ModelAndView mav = new ModelAndView();
    mav.addObject("operationFileError", e.getMessage());
    mav.addObject("module", "files");
    mav.setViewName("home");
    return mav;
  }
}