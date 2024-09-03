package com.movie.ticket.booking.system.bookingservice.impl;


import com.movie.ticket.booking.system.bookingservice.broker.PaymentBrokerService;
import com.movie.ticket.booking.system.bookingservice.dto.BookingDTO;
import com.movie.ticket.booking.system.bookingservice.dto.BookingStatus;
import com.movie.ticket.booking.system.bookingservice.entity.BookingEntity;
import com.movie.ticket.booking.system.bookingservice.kafka.publisher.BookingServicePublisher;
import com.movie.ticket.booking.system.bookingservice.repo.BookingRepository;
import com.movie.ticket.booking.system.bookingservice.service.BookingService;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class PaymentServiceImpl implements BookingService {

    @Autowired private BookingRepository bookingRepository;
    @Autowired private PaymentBrokerService paymentBrokerService;

    @Autowired private BookingServicePublisher bookingServicePublisher;

    @Override
    @Transactional
    public BookingDTO initiatePayment(BookingDTO bookingDTO) {

        BookingEntity bookingEntity = BookingEntity.builder()
                .movieId(bookingDTO.getMovieId())
                .showDate(bookingDTO.getShowDate())
                .showTime(bookingDTO.getShowTime())
                .seatsBooked(bookingDTO.getSeatsBooked())
                .bookingStatus(BookingStatus.PENDING)
                .bookingAmount(bookingDTO.getBookingAmount())
                .build();

        bookingEntity = this.bookingRepository.save(bookingEntity);

//      bookingDTO =   this.paymentBrokerService.makePayment(bookingDTO);
        bookingDTO.setBookingId(bookingEntity.getBookingId());
        this.bookingServicePublisher.sendPaymentRequest(bookingDTO);
      bookingEntity.setBookingStatus(bookingDTO.getBookingStatus());
//        this.paymentBrokerService.

        return bookingDTO;

    }

    @Override
    @Transactional
    public void processPayment(BookingDTO bookingDTO) {
        Optional<BookingEntity> bookingEntityOptional = this.bookingRepository.findById(bookingDTO.getBookingId());
        if (bookingEntityOptional.isPresent()){
            BookingEntity bookingEntity = bookingEntityOptional.get();
            bookingEntity.setBookingStatus(bookingDTO.getBookingStatus());
        }

    }
}
