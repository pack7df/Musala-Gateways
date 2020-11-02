package com.musalasoft.challenge.entities;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;


public class Gateway {
    @NotBlank
    private String serial;
    @NotBlank
    private String name;
    @NotBlank
    private String ip;
    @Id
    private String id;

    public String getId() {
        return id;
    }
    public void setId(String value) {
        id = value;
   }

    List<Peripheral> peripherals = new ArrayList<>();

    public List<Peripheral> getPeripherals(){
        return peripherals;
    }
    public void setPeripherals(List<Peripheral> periphericals){
        this.peripherals = periphericals;

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
