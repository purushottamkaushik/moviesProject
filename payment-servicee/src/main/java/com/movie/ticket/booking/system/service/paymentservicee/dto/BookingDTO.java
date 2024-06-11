package com.movie.ticket.booking.system.service.paymentservicee.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookingDTO {

    private UUID bookingId;


    @NotBlank(message = "User id must not be null or blank")
    private String userId;


    @NotNull(message = "movie id cannot be blank or null")
    private Integer movieId;

    @NotNull(message = "seat books must not be null or blank ")
    private List<String> seatsBooked;

    private LocalDate showDate;

    private LocalTime showTime;

    @NotNull(message = "seat books must not be null or blank ")
    private BookingStatus bookingStatus;

    @NotNull(message = "booking Amount must not be null or blank")
    @Positive(message = "booking amount must be greater than 0")
    private Double bookingAmount;
}
