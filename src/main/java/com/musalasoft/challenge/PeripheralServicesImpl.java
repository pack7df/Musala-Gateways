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
        var gateway = repository.findById(gatewayId).orElse(null);
        if (gateway==null) return false;

        //There is not another peripheral with the same uid.
        var currentPeripheral = gateway.getPeripherals().stream().filter(p -> p.getUid()==data.getUid()).findFirst().orElse(null);
        if (currentPeripheral!=null) return false;

        //No more than 10 peripherals.
        var count = gateway.getPeripherals().stream().count();
        if (count>9) return false;


        gateway.getPeripherals().add(data);
        repository.save(gateway);
        return true;
    }

    @Override
    public boolean remove(String gatewayId, int peripheralId) {
        //Validation

        //Gateway exists.
        var gateway = repository.findById(gatewayId).orElse(null);
        if (gateway==null) return false;

        //Current peripheral must exist.
        var currentPeripheral = gateway.getPeripherals().stream().filter(p -> p.getUid()==peripheralId).findFirst().orElse(null);
        if (currentPeripheral==null) return false;

        gateway.getPeripherals().remove(currentPeripheral);
        repository.save(gateway);
        return true;
    }

    @Override
    public boolean update(String gatewayId, int currentUid, Peripheral data) {
        //Validation
        //Gateway exists.
        var gateway = repository.findById(gatewayId).orElse(null);
        if (gateway==null) return false;

        //Current peripheral must exist.
        var currentPeripheral = gateway.getPeripherals().stream().filter(p -> p.getUid()==currentUid).findFirst().orElse(null);
        if (currentPeripheral==null) return false;

        //Ensure no other peripheral exists,
        // Hint: the same peripheral to edit is found if no uid change.
        var anotherPeripheral = gateway.getPeripherals().stream().filter(p -> p.getUid()==data.getUid()).findFirst().orElse(null);
        if ((anotherPeripheral!=null) && (data.getUid()!=currentUid)) return false;
        //

        gateway.getPeripherals().remove(currentPeripheral);
        gateway.getPeripherals().add(data);
        repository.save(gateway);
        return true;
    }
}
