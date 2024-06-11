package com.movie.ticket.booking.system.bookingservice.impl;


import com.movie.ticket.booking.system.bookingservice.broker.PaymentBrokerService;
import com.movie.ticket.booking.system.bookingservice.dto.BookingDTO;
import com.movie.ticket.booking.system.bookingservice.dto.BookingStatus;
import com.movie.ticket.booking.system.bookingservice.entity.BookingEntity;
import com.movie.ticket.booking.system.bookingservice.repo.BookingRepository;
import com.movie.ticket.booking.system.bookingservice.service.PaymentService;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired private BookingRepository bookingRepository;
    @Autowired private PaymentBrokerService paymentBrokerService;

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

      bookingDTO =   this.paymentBrokerService.makePayment(bookingDTO);
      bookingEntity.setBookingStatus(bookingDTO.getBookingStatus());
//        this.paymentBrokerService.

        return BookingDTO.builder()
                .movieId(bookingEntity.getMovieId())
                .showDate(bookingEntity.getShowDate())
                .showTime(bookingEntity.getShowTime())
                .seatsBooked(bookingEntity.getSeatsBooked())
                .bookingStatus(bookingEntity.getBookingStatus())
                .bookingAmount(bookingEntity.getBookingAmount())
                .build();

    }
}
