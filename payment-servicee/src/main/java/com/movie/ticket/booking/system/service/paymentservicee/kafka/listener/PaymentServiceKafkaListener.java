package com.movie.ticket.booking.system.service.paymentservicee.kafka.listener;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.movie.ticket.booking.system.service.paymentservicee.dto.BookingDTO;
import com.movie.ticket.booking.system.service.paymentservicee.dto.BookingStatus;
import com.movie.ticket.booking.system.service.paymentservicee.dto.PaymentStatus;
import com.movie.ticket.booking.system.service.paymentservicee.entity.PaymentEntity;
import com.movie.ticket.booking.system.service.paymentservicee.impl.StripePaymentService;
import com.movie.ticket.booking.system.service.paymentservicee.kafka.publisher.PaymentKafkaPublisher;
import com.movie.ticket.booking.system.service.paymentservicee.repo.PaymentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j

public class PaymentServiceKafkaListener {

    @Autowired private PaymentRepository paymentRepository;
    @Autowired private StripePaymentService stripePaymentService;

    @Autowired private PaymentKafkaPublisher publisher;

    @KafkaListener(topics = {"payment-request"} , groupId = "payment-rq1")
    @Transactional
    public void paymentKafkaListener(String bookingDtoString) {

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        try {
            BookingDTO bookingDTO = mapper.readValue(bookingDtoString, BookingDTO.class);
            PaymentEntity paymentEntity = PaymentEntity.builder()
                    .bookingId(bookingDTO.getBookingId())
                    .paymentAmount(bookingDTO.getBookingAmount())
                    .paymentStatus(PaymentStatus.PENDING)
                    .build();

            this.paymentRepository.save(paymentEntity);
            paymentEntity.setPaymentTimeStamp(LocalDateTime.now());
            bookingDTO.setBookingStatus(BookingStatus.PENDING);
            stripePaymentService.makePayment(bookingDTO);
            paymentEntity.setPaymentStatus(PaymentStatus.SUCCESS);

            this.publisher.publishBookingResponse(bookingDTO);



        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
