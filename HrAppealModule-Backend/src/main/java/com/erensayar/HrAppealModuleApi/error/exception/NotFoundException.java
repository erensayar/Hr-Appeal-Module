package com.erensayar.HrAppealModuleApi.error.exception;

import static com.erensayar.HrAppealModuleApi.error.exception.ExceptionConstants.NOT_FOUND_ERROR_CODE;
import static com.erensayar.HrAppealModuleApi.error.exception.ExceptionConstants.NOT_FOUND_ERROR_MESSAGE;

public class NotFoundException extends BaseException {

    private static final String ERROR_CODE = NOT_FOUND_ERROR_CODE;
    private static final String ERROR_MESSAGE = NOT_FOUND_ERROR_MESSAGE;

    public NotFoundException() {
        super(ERROR_CODE, ERROR_MESSAGE);
    }

    public NotFoundException(final String errCode, final String errorMessage) {
        super(errCode, errorMessage);
    }

    public NotFoundException(final String errorMessage) {
        super(NOT_FOUND_ERROR_CODE, errorMessage);
    }
}
