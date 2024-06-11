package com.movie.ticket.booking.system.service.paymentservicee.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder

public class PaymentDto {

    private UUID bookingId;

    private PaymentStatus paymentStatus;

    private Double paymentAmount;

    private LocalDateTime paymentTimeStamp;



}
