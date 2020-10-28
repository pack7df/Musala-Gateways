package com.musalasoft.challenge;

import com.musalasoft.challenge.entities.Gateway;

import java.util.List;

public interface IGatewayRepository {
    /**
     * Insert a new gateway in database.
     * @return Gateway data saved.
     * @param gateway Gateway data inserted.
     */
    Gateway insert(Gateway gateway);

    /**
     * Updates a gateway in database given the serial field.
     * @param gateway Gateway data to update.
     * @return Gateway data updated.
     */
    Gateway update(Gateway gateway);

    /**
     *
     * @param serial Serial valur to find the gateway.
     * @return A Gateway found or null if no gateway exists with the given serial.
     */
    Gateway findGatewayBySerial(String serial);

    Gateway findGateWayById(String Id);

    void remove(String id);

    List<Gateway> findAll();
}
