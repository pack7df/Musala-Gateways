package com.musalasoft.challenge;

import com.musalasoft.challenge.entities.Peripheral;

public class PeripheralServicesImpl implements IPeripheralServices {
    private IGatewayRepository repository;
    public PeripheralServicesImpl(IGatewayRepository repository){
        this.repository =  repository;
    }
    @Override
    public boolean add(String gatewayId, Peripheral data) {

        return false;
    }

    @Override
    public boolean delete(String peripheralId) {
        return false;
    }

    @Override
    public boolean update(Peripheral data) {
        return false;
    }
}
