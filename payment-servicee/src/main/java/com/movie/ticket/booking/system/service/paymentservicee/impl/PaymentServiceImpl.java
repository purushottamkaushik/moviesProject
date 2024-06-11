package com.movie.ticket.booking.system.service.paymentservicee.impl;



import com.movie.ticket.booking.system.service.paymentservicee.dto.BookingDTO;
import com.movie.ticket.booking.system.service.paymentservicee.dto.BookingStatus;
import com.movie.ticket.booking.system.service.paymentservicee.dto.PaymentDto;
import com.movie.ticket.booking.system.service.paymentservicee.dto.PaymentStatus;
import com.movie.ticket.booking.system.service.paymentservicee.entity.PaymentEntity;
import com.movie.ticket.booking.system.service.paymentservicee.repo.PaymentRepository;
import com.movie.ticket.booking.system.service.paymentservicee.service.PaymentService;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {


    @Autowired private StripePaymentService stripePaymentService;
    @Autowired private PaymentRepository paymentRepository;

    @Override
    @Transactional
    public BookingDTO makePayment(BookingDTO bookingDTO) {

        PaymentEntity paymentEntity = PaymentEntity.builder()
                .bookingId(bookingDTO.getBookingId())
                .paymentAmount(bookingDTO.getBookingAmount())
                .paymentStatus(PaymentStatus.PENDING)
                .build();

        this.paymentRepository.save(paymentEntity);

        PaymentStatus paymentStatus = stripePaymentService.makePayment(paymentEntity.getPaymentAmount());

        if (paymentStatus.equals(PaymentStatus.SUCCESS)) {
            paymentEntity.setPaymentStatus(PaymentStatus.SUCCESS);
            bookingDTO.setBookingStatus(BookingStatus.CONFIRMED);
        } else {
            paymentEntity.setPaymentStatus(PaymentStatus.FAILED);
            bookingDTO.setBookingStatus(BookingStatus.CANCELLED);
        }
        return bookingDTO;


    }
}
