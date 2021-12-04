package com.tempo.teams.exceptions;

import com.tempo.teams.presenter.Errors;
import com.tempo.teams.presenter.ResponseError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseError> handleBadRequest(BadRequestException ex, WebRequest request) {

        ResponseError rs = getErrorList("400", ex.getMessage(), "BAD REQUEST EXCEPTION");
        return new ResponseEntity<>(rs, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ResponseError> handleBadRequest(InternalServerErrorException ex, WebRequest request) {

        ResponseError rs = getErrorList("500", ex.getMessage(), "INTERNAL SERVER ERROR EXCEPTION");
        return new ResponseEntity<>(rs, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    private ResponseError getErrorList(String code, String detail, String title) {
        List<Errors> listErrors = new ArrayList<>();
        var errors = new Errors(code, detail, title);
        listErrors.add(errors);
        return new ResponseError(listErrors);
    }
}
