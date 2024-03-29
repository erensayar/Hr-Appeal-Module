package com.erensayar.HrAppealModuleApi.error.controller;

import static com.erensayar.HrAppealModuleApi.error.exception.ExceptionConstants.VALIDATION_ERROR_CODE;

import com.erensayar.HrAppealModuleApi.error.exception.BadRequestException;
import com.erensayar.HrAppealModuleApi.error.exception.BaseException;
import com.erensayar.HrAppealModuleApi.error.exception.ConflictException;
import com.erensayar.HrAppealModuleApi.error.exception.ForbiddenException;
import com.erensayar.HrAppealModuleApi.error.exception.InternalServerErrorException;
import com.erensayar.HrAppealModuleApi.error.exception.NoContentException;
import com.erensayar.HrAppealModuleApi.error.exception.NotFoundException;
import com.erensayar.HrAppealModuleApi.error.exception.OkWithMessage;
import com.erensayar.HrAppealModuleApi.error.exception.UnauthorizedException;
import com.erensayar.HrAppealModuleApi.error.model.ErrorResponse;
import com.erensayar.HrAppealModuleApi.error.model.ValidationErrorResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice {

  private static final String CONTENT_TYPE = "Content-Type";
  private static final String CONTENT_TYPE_VALUE = "application/json";

  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<Object> handleBadRequest(BadRequestException e) {
    return handle(e, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ConflictException.class)
  public ResponseEntity<Object> handleConflictException(ConflictException e) {
    return handle(e, HttpStatus.CONFLICT);
  }

  @ExceptionHandler(InternalServerErrorException.class)
  public ResponseEntity<Object> handleInternalServerErrorException(InternalServerErrorException e) {
    return handle(e, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(NoContentException.class)
  public ResponseEntity<Object> handleNoContentException(NoContentException e) {
    return handle(e, HttpStatus.NO_CONTENT);
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<Object> handleNotFoundException(NotFoundException e) {
    return handle(e, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(UnauthorizedException.class)
  public ResponseEntity<Object> handleUnauthorizedException(UnauthorizedException e) {
    return handle(e, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(ForbiddenException.class)
  public ResponseEntity<Object> handleForbiddenException(ForbiddenException e) {
    return handle(e, HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(OkWithMessage.class)
  public ResponseEntity<Object> OkWithMessage(OkWithMessage e) {
    return handle(e, HttpStatus.OK);
  }

  private ResponseEntity<Object> handle(BaseException e, HttpStatus h) {
    log.error(e.getErrorMessage(), e);

    String errorCode = e.getErrorCode();
    String errorMessage = e.getErrorMessage();

    HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.add(CONTENT_TYPE, CONTENT_TYPE_VALUE);

    ErrorResponse responseBody = ErrorResponse.builder()
        .errorType(e.getClass().getSimpleName())
        .errorCode(errorCode)
        .errorMessage(errorMessage)
        .build();

    return ResponseEntity
        .status(h)
        .headers(responseHeaders)
        .body(responseBody);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<Object> handleJavaxValidationException(ConstraintViolationException e) {

    Map<String, String> validationMessageAndProperty = new HashMap<>();
    Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
    for (ConstraintViolation<?> c : constraintViolations) {
      validationMessageAndProperty.put(c.getPropertyPath().toString(), c.getMessage());
    }

    HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.add(CONTENT_TYPE, CONTENT_TYPE_VALUE);

    ValidationErrorResponse responseBody = ValidationErrorResponse.builder()
        .errorType(e.getClass().getName())
        .errorCode(VALIDATION_ERROR_CODE)
        .errorMessage(validationMessageAndProperty)
        .build();

    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .headers(responseHeaders)
        .body(responseBody);
  }

}
