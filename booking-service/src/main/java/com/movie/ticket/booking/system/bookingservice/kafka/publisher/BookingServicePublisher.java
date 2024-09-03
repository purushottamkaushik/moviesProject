package com.movie.ticket.booking.system.bookingservice.kafka.publisher;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.movie.ticket.booking.system.bookingservice.dto.BookingDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingServicePublisher {


    private final KafkaTemplate<String, String> bookingServiceKafkaTemplate ;

    public void sendPaymentRequest(BookingDTO bookingDTO){

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        try {
          String  bookingDtoString = mapper.writeValueAsString(bookingDTO);
            this.bookingServiceKafkaTemplate.send("payment-request" , bookingDtoString);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


    }


}
