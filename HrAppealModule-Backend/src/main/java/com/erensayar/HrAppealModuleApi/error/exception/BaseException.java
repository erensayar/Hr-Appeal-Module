package com.erensayar.HrAppealModuleApi.error.exception;

import lombok.Builder;
import lombok.Getter;

import static com.erensayar.HrAppealModuleApi.error.exception.ExceptionConstants.ERROR_CODE;
import static com.erensayar.HrAppealModuleApi.error.exception.ExceptionConstants.ERROR_MESSAGE;

@Builder
@Getter
public class BaseException extends RuntimeException {

    @Builder.Default
    private String errorCode = ERROR_CODE;
    @Builder.Default
    private String errorMessage = ERROR_MESSAGE;

    public BaseException() {
      this.errorCode = ERROR_CODE;
      this.errorMessage = ERROR_MESSAGE;
    }

    public BaseException(final String errCode, final String errorMessage) {
      this.errorCode = errCode;
      this.errorMessage = errorMessage;
    }

    public BaseException(final String errorMessage) {
      this.errorCode = ERROR_CODE;
      this.errorMessage = errorMessage;
    }
}
