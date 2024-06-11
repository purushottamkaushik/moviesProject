package com.movie.ticket.booking.system.bookingservice.broker;


import com.movie.ticket.booking.system.bookingservice.dto.BookingDTO;
import com.movie.ticket.booking.system.bookingservice.dto.PaymentStatus;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "payment-servicee")
public interface PaymentBrokerService {



    @PostMapping("/payments")
    public BookingDTO makePayment(@RequestBody BookingDTO bookingDTO);

}
