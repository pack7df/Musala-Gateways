package com.musalasoft.challenge.services;

import com.musalasoft.challenge.entities.Peripheral;

public interface IPeripheralServices {
    Peripheral add(String gatewayId, Peripheral data);
    boolean remove(String gatewayId, int peripheralId);
    Peripheral update(String gatewayId, int currentUid, Peripheral data);
}
