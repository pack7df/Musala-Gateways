package com.musalasoft.challenge;

import com.musalasoft.challenge.entities.Peripheral;

public class PeripheralServicesImpl implements IPeripheralServices {
    private IGatewayRepository repository;
    public PeripheralServicesImpl(IGatewayRepository repository){
        this.repository =  repository;
    }

    @Override
    public boolean add(String gatewayId, Peripheral data) {
        //Validation

        //Gateway exists.
        var gateway = repository.findGateWayById(gatewayId);
        if (gateway==null) return false;

        //There is not another peripheral with the same uid.
        var currentPeripheral = gateway.getPeripherals().stream().filter(p -> p.getUid()==data.getUid()).findFirst();
        if (!currentPeripheral.isEmpty()) return false;

        //No more than 10 peripherals.
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
        //Gateway exists.
        var gateway = repository.findGateWayById(gatewayId);
        if (gateway==null) return false;

        //Current peripheral must exist.
        var currentPeripheral = gateway.getPeripherals().stream().filter(p -> p.getUid()==currentUid).findFirst();
        if (currentPeripheral.isEmpty()) return false;

        //Ensure no other peripheral exists,
        // Hint: the same peripheral to edit is found if no uid change.
        var anotherPeripheral = gateway.getPeripherals().stream().filter(p -> p.getUid()==data.getUid()).findFirst();
        if (!anotherPeripheral.isEmpty() && (data.getUid()!=currentUid)) return false;
        //

        gateway.getPeripherals().remove(currentPeripheral.get());
        gateway.getPeripherals().add(data);
        repository.update(gateway);
        return true;
    }
}
