package com.emmanuelirem.studentassistant.controllers.Api.V1.exception;

import com.emmanuelirem.studentassistant.models.Error;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.util.Date;

//@ControllerAdvice
public class ErrorController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public Mono<Error> showError(WebRequest request, Exception exception) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
        Error error = new Error();
        error.setCause("Bad Request");
        error.setTimestamp(dateFormat.format(new Date()));
        error.setPath(request.getContextPath());
        error.setMessage(exception.getMessage());

        return Mono.just(error);
    }


    public Mono<ResponseStatusException> notFound() {

        return Mono.just(new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found"));
    }

}
