package com.musalasoft.challenge;

import com.musalasoft.challenge.entities.Gateway;

import java.util.List;

public class GatewayServicesImpl implements IGatewayServices {
    private IGatewayRepository repository;

    /**
     * Creates a new Gateway Services.
     * @param repository Repository instance to manage DB.
     */
    public GatewayServicesImpl(IGatewayRepository repository){
        this.repository = repository;
    }
    @Override
    public Gateway findBySerial(String serial) {
        return null;
    }

    @Override
    public boolean add(Gateway data) {
        var gatewayFound = repository.FindGatewayBySerial(data.getSerial());
        if (gatewayFound!=null) return false;
        repository.insert(data);
        return true;
    }

    @Override
    public boolean update(Gateway entity) {
        var gatewayToEdit = repository.FindGateWayById(entity.getId());
        if (gatewayToEdit==null) return false;
        var gatewayFound = repository.FindGatewayBySerial(entity.getSerial());
        if ((gatewayFound!=null) && (gatewayFound.getId()!=entity.getId())) return false;
        repository.update(entity);
        return true;
    }

    @Override
    public boolean remove(String serial) {
        return false;
    }

    @Override
    public List<Gateway> getAll() {
        return null;
    }
}
