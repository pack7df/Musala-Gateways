package com.musalasoft.challenge.services;

import com.musalasoft.challenge.repositories.IGatewayRepository;
import com.musalasoft.challenge.entities.Gateway;

import java.util.List;

public class GatewayServicesImpl implements IGatewayServices {
    private IGatewayRepository repository;

    public GatewayServicesImpl(IGatewayRepository repository){
        this.repository = repository;
    }

    @Override
    public Gateway findById(String id) {
        return repository.findById(id).orElse(null);
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
        var gatewayToEdit = repository.findById(entity.getId()).orElse(null);
        if (gatewayToEdit==null) return false;
        var gatewayFound = repository.findGatewayBySerial(entity.getSerial());
        if ((gatewayFound!=null) && (gatewayFound.getId()!=entity.getId())) return false;
        repository.save(entity);
        return true;
    }

    @Override
    public boolean remove(String id) {
        var gatewayToEdit = repository.findById(id).orElse(null);
        if (gatewayToEdit==null) return false;
        repository.deleteById(id);
        return true;
    }

    @Override
    public List<Gateway> getAll() {
        return repository.findAll();
    }
}
