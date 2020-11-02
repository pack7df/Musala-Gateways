package com.musalasoft.challenge;

import com.musalasoft.challenge.entities.Peripheral;

public interface IPeriphericalServices {
    void add(String gatewayId, Peripheral data);
    void delete(String periphericalId);
    void update(Peripheral data);
}
