package com.movie.ticket.booking.system.service.paymentservicee.service;


import com.movie.ticket.booking.system.service.paymentservicee.dto.BookingDTO;
import com.movie.ticket.booking.system.service.paymentservicee.dto.PaymentStatus;

public interface PaymentService {
    public BookingDTO makePayment(BookingDTO paymentDto);

}
