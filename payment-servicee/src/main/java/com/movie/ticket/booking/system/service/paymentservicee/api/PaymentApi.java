package com.movie.ticket.booking.system.service.paymentservicee.api;

import com.movie.ticket.booking.system.service.paymentservicee.dto.BookingDTO;
import com.movie.ticket.booking.system.service.paymentservicee.dto.PaymentStatus;
import com.movie.ticket.booking.system.service.paymentservicee.entity.PaymentEntity;
import com.movie.ticket.booking.system.service.paymentservicee.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentApi {
    @Autowired private PaymentService paymentService;

    @GetMapping
    public String test() {
        return "Payment Success";
    }

    @PostMapping
    public BookingDTO makePayment(@RequestBody BookingDTO bookingDTO){

        return this.paymentService.makePayment(bookingDTO);

    }
}
