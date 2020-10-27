package com.musalasoft.challenge;

import com.musalasoft.challenge.entities.Gateway;

public interface IGatewayRepository {
    /**
     *
     * Saves or updates a gateway. The operation depends on serial. If it's null it add, else it updates.
     * Peripherals list updated if the list IS NULL, otherwise IT'S NOT updated.
     * @return Gateway data saved.
     * @param gateway Gateway data to be created or updated.
     */
    Gateway saveOrUpdate(Gateway gateway);

    /**
     *
     * @param serial Serial valur to find the gateway.
     * @return A Gateway found or null if no gateway exists with the given serial.
     */
    Gateway FindGatewayBySerial(String serial);
}
