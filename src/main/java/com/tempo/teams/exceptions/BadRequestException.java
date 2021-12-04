package com.tempo.teams.exceptions;

import com.tempo.teams.presenter.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseBody
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    private final transient ResponseError errorGetData;

    public BadRequestException(String message, ResponseError errorGetData) {
        super(message);
        this.errorGetData = errorGetData;
    }

    public ResponseError getErrorGetData() {
        return errorGetData;
    }
}
