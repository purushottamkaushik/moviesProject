package com.movie.ticket.booking.system.bookingservice.service;


import com.movie.ticket.booking.system.bookingservice.dto.BookingDTO;

public interface BookingService {

    public BookingDTO initiatePayment( BookingDTO bookingDTO);

    void processPayment(BookingDTO bookingDTO);
}
