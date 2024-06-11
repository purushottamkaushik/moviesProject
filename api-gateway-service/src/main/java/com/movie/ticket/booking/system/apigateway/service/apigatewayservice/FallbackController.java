package com.movie.ticket.booking.system.apigateway.service.apigatewayservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {


    @GetMapping("/fallbackBooking")
    public String fallback(){
        return "Please select a fallback";
    }
}
