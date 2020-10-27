package com.musalasoft.challenge;

import com.musalasoft.challenge.entities.Gateway;

public class TestDataGeneratorHelper {
    public static Gateway GenerateGateway(byte code){
        var gateway = new Gateway();
        gateway.setId(""+code);
        gateway.setIp("192.168.1." + code);
        gateway.setName("NameSample - " + code);
        gateway.setSerial("SerialSample - " + code);
        return gateway;
    }
}
