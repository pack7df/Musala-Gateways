package com.musalasoft.challenge.controllers;

import com.musalasoft.challenge.entities.Gateway;
import com.musalasoft.challenge.services.IGatewayServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/gateway")
public class GatewayController {

    private final IGatewayServices gatewayServices;

    @Autowired
    public GatewayController(IGatewayServices gatewayServices) {
        this.gatewayServices = gatewayServices;
    }

    private boolean validateGateway(Gateway data){
        if (data.getSerial()==null) return  false;
        data.setSerial((data.getSerial().trim()));
        if (data.getSerial().equals("")) return false;
        if (data.getIp()==null) return false;
        data.setIp(data.getIp().trim());
        String PATTERN = "^((0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)\\.){3}(0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)$";
        var isIpValid = data.getIp().matches(PATTERN);
        if (!isIpValid) return false;
        return true;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Gateway addGateway(@Valid @RequestBody Gateway newGateway){
        var validation = validateGateway(newGateway);
        if (!validation) return null;
        return gatewayServices.add(newGateway);
    }

    @DeleteMapping("/{gatewayId}")
    public boolean deleteGateway(@PathVariable String gatewayId){
        return gatewayServices.remove(gatewayId);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Gateway updateGateway(@Valid @RequestBody Gateway data) {
        if (data.getId()==null) return  null;
        data.setId(data.getId().trim());
        if (data.getId().equals("")) return null;
        var validation = validateGateway(data);
        if (!validation) return null;
        return gatewayServices.update(data);
    }

    @GetMapping
    public List<Gateway> getAllGateways() {
        return gatewayServices.getAll();
    }

    @GetMapping("/{gatewayId}")
    public Gateway getGateway(@PathVariable String gatewayId) {
        return gatewayServices.findById(gatewayId);
    }
}


