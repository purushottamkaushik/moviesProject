package com.movie.ticket.booking.system.service.paymentservicee.kafka.publisher;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.movie.ticket.booking.system.service.paymentservicee.dto.BookingDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentKafkaPublisher {


    private final KafkaTemplate<String,String> paymentKafkaTemplate;

    public void publishBookingResponse(BookingDTO bookingDTO){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        try {
            String response = objectMapper.writeValueAsString(bookingDTO);
            this.paymentKafkaTemplate.send("payment-response" , response);
            log.info("Payment success");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
