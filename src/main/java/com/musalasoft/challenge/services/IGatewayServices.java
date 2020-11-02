package com.musalasoft.challenge.services;

import com.musalasoft.challenge.entities.Gateway;

import java.util.List;

/**
 * Gateway services protocol.
 */
public interface IGatewayServices {
    /**
     * Finds a gateway given the database Id.
     * @param id Database Id.
     * @return The gateway found or null if it doesn't exist.
     */
    Gateway findById(String id);

    /**
     * Adds a gateway.
     * @param data Gateways data. It does not take into account the peripheral list.
     * @return True if the operation completes successfully, false otherwise.
     */
    boolean add(Gateway data);

    /**
     * Updates a gateway.
     * @param data Data used to update the gateway. It does not take into account the peripheral list.
     * @return True if the operation completes successfully, false otherwise.
     */
    boolean update(Gateway data);

    /**
     * Removes a gateway.
     * @param id Database id used of the gateway to remove.
     * @return True if the operation completes successfully, false otherwise.
     */
    boolean remove(String id);

    /**
     * List all gateways stored.
     * @return List of gateways with all peripherals information.
     */
    List<Gateway> getAll();
}
