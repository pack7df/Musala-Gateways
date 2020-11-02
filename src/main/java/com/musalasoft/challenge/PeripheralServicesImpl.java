package com.musalasoft.challenge;

import com.musalasoft.challenge.entities.Peripheral;

public class PeripheralServicesImpl implements IPeripheralServices {
    private IGatewayRepository repository;
    public PeripheralServicesImpl(IGatewayRepository repository){
        this.repository =  repository;
    }
    @Override
    public boolean add(String gatewayId, Peripheral data) {
        var gateway = repository.findGateWayById(gatewayId);
        if (gateway==null) return false;
        var currentPeripheral = gateway.getPeriphericals().stream().filter(p -> p.getUid()==data.getUid()).findFirst();
        if (!currentPeripheral.isEmpty()) return false;
        var count = gateway.getPeriphericals().stream().count();
        if (count>9) return false;
        gateway.getPeriphericals().add(data);
        repository.update(gateway);
        return true;
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
