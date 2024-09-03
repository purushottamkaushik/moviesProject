package com.movie.ticket.booking.system.bookingservice.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class ErrorDto {

    private List<String> defaultMessage;

    private String errorCode;

}
