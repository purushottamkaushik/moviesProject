package com.movie.ticket.booking.system.service.paymentservicee.repo;

import com.movie.ticket.booking.system.service.paymentservicee.entity.PaymentEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PaymentRepository extends CrudRepository<PaymentEntity , UUID> {
}
