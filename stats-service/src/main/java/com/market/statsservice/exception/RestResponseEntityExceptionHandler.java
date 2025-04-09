package com.market.statsservice.exception;

import com.market.statsservice.model.dto.ErrorResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.stream.Collectors;

import static com.market.statsservice.utils.ErrorCatalog.GENERIC_ERROR;
import static com.market.statsservice.utils.ErrorCatalog.INVALID_ITEM;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        //TODO: refactor method
        return  ErrorResponse.builder()
                .code(INVALID_ITEM.getCode())
                .status(HttpStatus.BAD_REQUEST)
                .message(INVALID_ITEM.getMessage())
                .detailMessages(result.getFieldErrors()
                        .stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.toList()))
                .timeStamp(LocalDateTime.now())
                .build();
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleInternalServeError(
            Exception exception) {
        return ErrorResponse.builder()
                .code(GENERIC_ERROR.getCode())
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(GENERIC_ERROR.getMessage())
                .detailMessages(Collections.singletonList(exception.getMessage()))
                .timeStamp(LocalDateTime.now())
                .build();
    }
}
