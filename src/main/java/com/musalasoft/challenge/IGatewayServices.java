package com.musalasoft.challenge;

import com.musalasoft.challenge.entities.Gateway;

import java.util.List;

public interface IGatewayServices {
    Gateway findBySerial(String serial);
    boolean add(Gateway data);
    boolean update(Gateway entity);
    boolean remove(String serial);
    List<Gateway> getAll();
}
