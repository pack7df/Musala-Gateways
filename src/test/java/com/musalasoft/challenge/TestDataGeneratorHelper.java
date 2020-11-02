package com.musalasoft.challenge;

import com.musalasoft.challenge.entities.Gateway;
import com.musalasoft.challenge.entities.Peripheral;

import java.util.Date;

public class TestDataGeneratorHelper {
    public static Gateway GenerateGateway(byte code){
        var gateway = new Gateway();
        gateway.setId(""+code);
        gateway.setIp("192.168.1." + code);
        gateway.setName("NameSample - " + code);
        gateway.setSerial("SerialSample - " + code);
        return gateway;
    }

    public static Peripheral GeneratePeripheral(byte code){
        var peripheral = new Peripheral();
        peripheral.setUid(code);
        peripheral.setCreated(new Date());
        peripheral.setStatus(false);
        peripheral.setVendor("Vendor - " + code);
        return peripheral;
    }
}
