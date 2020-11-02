package com.musalasoft.challenge;

import com.musalasoft.challenge.entities.Peripheral;

public interface IPeripheralServices {
    void add(String gatewayId, Peripheral data);
    void delete(String peripheralId);
    void update(Peripheral data);
}
