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
        var currentPeripheral = gateway.getPeripherals().stream().filter(p -> p.getUid()==data.getUid()).findFirst();
        if (!currentPeripheral.isEmpty()) return false;
        var count = gateway.getPeripherals().stream().count();
        if (count>9) return false;
        gateway.getPeripherals().add(data);
        repository.update(gateway);
        return true;
    }

    @Override
    public boolean delete(String peripheralId) {
        return false;
    }

    @Override
    public boolean update(String gatewayId, int currentUid, Peripheral data) {
        //Validation
        var gateway = repository.findGateWayById(gatewayId);
        if (gateway==null) return false;
        var currentPeripheral = gateway.getPeripherals().stream().filter(p -> p.getUid()==currentUid).findFirst();
        if (currentPeripheral.isEmpty()) return false;
        var anotherPeripheral = gateway.getPeripherals().stream().filter(p -> p.getUid()==data.getUid()).findFirst();
        if (!anotherPeripheral.isEmpty() && (data.getUid()!=currentUid)) return false;
        gateway.getPeripherals().remove(currentPeripheral.get());
        gateway.getPeripherals().add(data);
        repository.update(gateway);
        return true;
    }
}
