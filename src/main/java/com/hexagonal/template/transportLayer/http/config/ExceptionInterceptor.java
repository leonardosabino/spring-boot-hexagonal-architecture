package com.hexagonal.template.transportLayer.http.config;

import com.hexagonal.template.transportLayer.http.InternalServerErrorException;
import com.hexagonal.template.transportLayer.openapi.model.GenericErrorResponse;
import com.hexagonal.template.transportLayer.openapi.model.GenericErrorResults;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionInterceptor {

  @ExceptionHandler(InternalServerErrorException.class)
  public ResponseEntity<GenericErrorResponse> handleMethod(InternalServerErrorException ex) {
    GenericErrorResponse genericFieldErrorResponse = createGenericFieldErrorResponse(
        ex.getUserMessage(),
        String.valueOf(ex.getHttpStatus().value()),
        ex.getOrigin(),
        Optional.of(ex.getDeveloperMessage())
    );
    return ResponseEntity.status(ex.getHttpStatus()).body(genericFieldErrorResponse);
  }

  private GenericErrorResponse createGenericFieldErrorResponse(String userMessage, String code,
      String origin, Optional<String> developerMessage) {
    GenericErrorResponse genericErrorResponse = new GenericErrorResponse();
    GenericErrorResults genericErrorResults = new GenericErrorResults();
    genericErrorResponse.setResults(genericErrorResults);

    genericErrorResults.setUserMessage(userMessage);
    genericErrorResults.setCode(code);
    genericErrorResults.setOrigin(origin);
    developerMessage.ifPresent(genericErrorResults::setDeveloperMessage);

    return genericErrorResponse;
  }

}
