package com.movie.ticket.booking.system.bookingservice.service;


import com.movie.ticket.booking.system.bookingservice.dto.BookingDTO;

public interface PaymentService {

    public BookingDTO initiatePayment( BookingDTO bookingDTO);
}
