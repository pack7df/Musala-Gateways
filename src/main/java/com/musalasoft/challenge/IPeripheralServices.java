package com.musalasoft.challenge;

import com.musalasoft.challenge.entities.Peripheral;

public interface IPeripheralServices {
    boolean add(String gatewayId, Peripheral data);
    boolean delete(String peripheralId);
    boolean update(String gatewayId, int currentUid, Peripheral data);
}
