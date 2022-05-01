package com.erensayar.HrAppealModuleApi.error.exception;

import static com.erensayar.HrAppealModuleApi.error.exception.ExceptionConstants.FORBIDDEN_ERROR_CODE;
import static com.erensayar.HrAppealModuleApi.error.exception.ExceptionConstants.FORBIDDEN_ERROR_MESSAGE;

public class ForbiddenException extends BaseException {

  private static final String ERROR_CODE = FORBIDDEN_ERROR_CODE;
  private static final String ERROR_MESSAGE = FORBIDDEN_ERROR_MESSAGE;

  public ForbiddenException() {
    super(ERROR_CODE, ERROR_MESSAGE);
  }

  public ForbiddenException(final String errCode, final String errorMessage) {
    super(errCode, errorMessage);
  }

  public ForbiddenException(final String errorMessage) {
    super(ERROR_MESSAGE, errorMessage);
  }
}