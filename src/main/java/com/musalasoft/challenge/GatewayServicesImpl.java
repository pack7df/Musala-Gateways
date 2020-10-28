package com.musalasoft.challenge;

import com.musalasoft.challenge.entities.Gateway;

import java.util.List;

public class GatewayServicesImpl implements IGatewayServices {
    private IGatewayRepository repository;

    public GatewayServicesImpl(IGatewayRepository repository){
        this.repository = repository;
    }

    @Override
    public Gateway findById(String id) {
        return repository.findGateWayById(id);
    }

    @Override
    public boolean add(Gateway data) {
        var gatewayFound = repository.findGatewayBySerial(data.getSerial());
        if (gatewayFound!=null) return false;
        repository.insert(data);
        return true;
    }

    @Override
    public boolean update(Gateway entity) {
        var gatewayToEdit = repository.findGateWayById(entity.getId());
        if (gatewayToEdit==null) return false;
        var gatewayFound = repository.findGatewayBySerial(entity.getSerial());
        if ((gatewayFound!=null) && (gatewayFound.getId()!=entity.getId())) return false;
        repository.update(entity);
        return true;
    }

    @Override
    public boolean remove(String id) {
        var gatewayToEdit = repository.findGateWayById(id);
        if (gatewayToEdit==null) return false;
        repository.remove(id);
        return true;
    }

    @Override
    public List<Gateway> getAll() {
        return repository.findAll();
    }
}
