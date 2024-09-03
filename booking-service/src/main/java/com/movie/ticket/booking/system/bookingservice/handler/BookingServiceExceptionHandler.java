package com.movie.ticket.booking.system.bookingservice.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class BookingServiceExceptionHandler extends RuntimeException {

    @ExceptionHandler ( value = {MethodArgumentNotValidException.class})
    public ErrorDto handleMethodArguementInvalidException (MethodArgumentNotValidException methodArgumentNotValidException){

        List<ObjectError> allErrors = methodArgumentNotValidException.getAllErrors();
        return ErrorDto.builder()
                        .errorCode(HttpStatus.BAD_REQUEST.getReasonPhrase())
                                .defaultMessage(allErrors.stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.toList())).
                build();


    }

}
