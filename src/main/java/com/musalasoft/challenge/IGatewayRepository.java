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
     * Finds a gateway given the serial code.
     * @param serial Serial value to find the gateway.
     * @return A Gateway found or null if no gateway exists with the given serial.
     */
    Gateway findGatewayBySerial(String serial);

    /**
     * Finds a gateway given the database ID.
     * @param Id Find a gateway given the database ID.
     * @return The gateway found.
     */
    Gateway findGateWayById(String Id);

    /**
     * Removes a gateway from database given the id.
     * @param id
     */
    void remove(String id);

    /**
     * Finds all gateways.
     * @return A list of all gateways in Database.
     */
    List<Gateway> findAll();
}
