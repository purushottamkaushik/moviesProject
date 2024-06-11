package com.movie.ticket.booking.system.service.paymentservicee.impl;


import com.movie.ticket.booking.system.service.paymentservicee.dto.PaymentStatus;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StripePaymentService {


    @Value("${stripe.key")
    private String apiKey;

    StripePaymentService(){

    }
    @PostConstruct
    public void init () {
        Stripe.apiKey = apiKey;
    }

    public PaymentStatus makePayment(Double amount){



        try {
            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                    .setPaymentMethod("pm_card_us")
                    .setAmount(amount.longValue())
                    .setCurrency("usd")

                    .build();
            PaymentIntent paymentIntent  = PaymentIntent.create(params);
            System.out.println("Payment Success");
        } catch (StripeException e) {
            log.error("Error making payment " + e.getStripeError().getMessage());
            return PaymentStatus.FAILED;
        }
        return PaymentStatus.SUCCESS;
    }

}
