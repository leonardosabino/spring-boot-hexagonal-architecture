package com.hexagonal.template.config;

import com.hexagonal.template.dataSource.restclient.FeignException;
import com.hexagonal.template.dataSource.sqs.SqsException;
import com.hexagonal.template.transportLayer.openapi.model.GeneralError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionAdviceConfiguration {

  @ResponseBody
  @ExceptionHandler(SqsException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public GeneralError sqsExceptionHandler(SqsException e) {
    GeneralError generalError = new GeneralError();
    generalError.setCode(500);
    generalError.setMessage(e.getMessage());
    return generalError;
  }

  @ResponseBody
  @ExceptionHandler(FeignException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public GeneralError feignExceptionHandler(FeignException e) {
    GeneralError generalError = new GeneralError();
    generalError.setCode(500);
    generalError.setMessage(e.getMessage());
    return generalError;
  }
}