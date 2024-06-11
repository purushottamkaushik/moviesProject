package com.movie.ticket.booking.system.bookingservice.entity;


import com.movie.ticket.booking.system.bookingservice.dto.BookingStatus;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "booking_id")
    private UUID bookingId;

    @NotBlank(message = "User id must not be null or blank")
    @Column(name = "user_id")
    private String userId;


    @NotNull(message = "movie id cannot be blank or null")
    @Column(name = "movie_id")
    private Integer movieId;

    @NotNull(message = "seat books must not be null or blank ")
    @ElementCollection
    @Column(name = "seat_booked")
    private List<String> seatsBooked;

    @Column(name = "show_date")
    private LocalDate showDate;

    @Column(name = "show_time")
    private LocalTime showTime;

    @Column(name = "booking_status")
    private BookingStatus bookingStatus;

    @NotNull(message = "booking Amount must not be null or blank")
    @Positive(message = "booking amount must be greater than 0")
    @Column(name = "booking_amount")
    private Double bookingAmount;
}
