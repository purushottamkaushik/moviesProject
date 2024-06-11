package com.movie.ticket.booking.system.bookingservice.repo;

import com.movie.ticket.booking.system.bookingservice.entity.BookingEntity;
import org.springframework.data.jpa.repository.support.CrudMethodMetadata;
import org.springframework.data.repository.CrudRepository;

public interface BookingRepository extends CrudRepository<BookingEntity,Long> {


}
