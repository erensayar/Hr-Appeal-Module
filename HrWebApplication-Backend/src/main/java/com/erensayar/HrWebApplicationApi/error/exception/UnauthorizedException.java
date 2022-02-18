package com.erensayar.HrWebApplicationApi.error.exception;

import static com.erensayar.HrWebApplicationApi.error.exception.ExceptionConstants.UNAUTHORIZED_ERROR_CODE;
import static com.erensayar.HrWebApplicationApi.error.exception.ExceptionConstants.UNAUTHORIZED_ERROR_MESSAGE;

public class UnauthorizedException extends BaseException {

    private static final String ERROR_CODE = UNAUTHORIZED_ERROR_CODE;
    private static final String ERROR_MESSAGE = UNAUTHORIZED_ERROR_MESSAGE;

    public UnauthorizedException() {
        super(ERROR_CODE, ERROR_MESSAGE);
    }

    public UnauthorizedException(final String errCode, final String errorMessage) {
        super(errCode, errorMessage);
    }

    public UnauthorizedException(final String errorMessage) {
        super(ERROR_MESSAGE, errorMessage);
    }
}
