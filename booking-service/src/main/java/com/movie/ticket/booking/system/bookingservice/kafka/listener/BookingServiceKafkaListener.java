package com.movie.ticket.booking.system.bookingservice.kafka.listener;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.movie.ticket.booking.system.bookingservice.dto.BookingDTO;
import com.movie.ticket.booking.system.bookingservice.service.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookingServiceKafkaListener {

    @Autowired private BookingService bookingService;


    @KafkaListener(topics = "payment-response" , groupId = "payment-response11")

    public void listenPaymentResponse(String bookingResponse) {

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        try {
            BookingDTO bookingDTO = mapper.readValue(bookingResponse, BookingDTO.class);
            bookingService.processPayment(bookingDTO);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
