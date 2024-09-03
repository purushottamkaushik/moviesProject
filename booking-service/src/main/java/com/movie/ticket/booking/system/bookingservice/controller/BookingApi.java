package com.movie.ticket.booking.system.bookingservice.controller;

import com.movie.ticket.booking.system.bookingservice.broker.PaymentBrokerService;

import com.movie.ticket.booking.system.bookingservice.dto.BookingDTO;
import com.movie.ticket.booking.system.bookingservice.service.BookingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking")
public class BookingApi {

    @Autowired
    private PaymentBrokerService paymentBrokerService;

    @Autowired private BookingService paymentService;

    @GetMapping
    public BookingDTO initiatePayment(@RequestBody BookingDTO bookingDTO) {

        return paymentService.initiatePayment(bookingDTO);
    }
}
