package com.musalasoft.challenge.repositories;

import com.musalasoft.challenge.entities.Gateway;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IGatewayRepository extends MongoRepository<Gateway, String> {
    /**
     * Finds a gateway given the serial code.
     * @param serial Serial value to find the gateway.
     * @return A Gateway found or null if no gateway exists with the given serial.
     */
    Gateway findGatewayBySerial(String serial);
}
