package com.nntdata.common.exception;

import lombok.Getter;

@Getter
public class GeneralException extends RuntimeException {

    private final String errorMessage;

    public GeneralException(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
