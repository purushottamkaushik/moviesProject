package com.movie.ticket.system.registry.moviesregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MoviesRegistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoviesRegistryApplication.class, args);
    }

}
