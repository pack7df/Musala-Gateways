package com.musalasoft.challenge.entities;

import java.util.ArrayList;
import java.util.List;

public class Gateway {
    private String serial;
    private String name;
    private String ip;
    private String id;

    public String getId() {
        return id;
    }
    public void setId(String value) {
        id = value;
   }

    List<Peripherical> periphericals = new ArrayList<>();

    public List<Peripherical> getPeriphericals(){
        return periphericals;
    }
    public void setPeriphericals(List<Peripherical> periphericals){
        this.periphericals = periphericals;

    }
    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
