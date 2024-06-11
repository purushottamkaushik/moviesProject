package com.movie.ticket.booking.system.service.paymentservicee.entity;


import com.movie.ticket.booking.system.service.paymentservicee.dto.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "payment_tab")
@Entity
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "payment_id")
    private UUID paymentId;

    @Column(name = "booking_id")
    private UUID bookingId;

    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;

    @Column(name = "payment_amount")
    private Double paymentAmount;

    @Column(name = "created_time")
    private LocalDateTime paymentTimeStamp;
}
