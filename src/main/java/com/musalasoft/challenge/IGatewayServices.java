package com.musalasoft.challenge;

import com.musalasoft.challenge.entities.Gateway;

import java.util.List;

/**
 * Gateway services protocol.
 */
public interface IGatewayServices {
    /**
     * Finds a gatewat given the database Id.
     * @param id Database Id.
     * @return The gateway found or null if it doesn't exist.
     */
    Gateway findById(String id);

    /**
     * Adds a gateway.
     * @param data Gateways data. It does not take into account the peripherical list.
     * @return True if the operation completes successfull, false otherwise.
     */
    boolean add(Gateway data);

    /**
     * Updates a gateway.
     * @param data Data used to update the gateway. It does not take into account the peripherical list.
     * @return True if the operation completes successfull, false otherwise.
     */
    boolean update(Gateway data);

    /**
     * Removes a gateway.
     * @param id Database id used of the gateway to remove.
     * @return True if the operation completes successfull, false otherwise.
     */
    boolean remove(String id);

    /**
     * List all gateways stored.
     * @return List of gateways with all peripherical information.
     */
    List<Gateway> getAll();
}
