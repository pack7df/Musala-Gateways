package com.musalasoft.challenge.controllers;

import com.musalasoft.challenge.entities.Gateway;
import com.musalasoft.challenge.entities.Peripheral;
import com.musalasoft.challenge.services.IGatewayServices;
import com.musalasoft.challenge.services.IPeripheralServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/peripheral")
public class PeripheralController {
    private final IPeripheralServices peripheralServices;

    @Autowired
    public PeripheralController(IPeripheralServices peripheralServices) {
        this.peripheralServices = peripheralServices;
    }

    private boolean validatePeripheral(String gatewayId, Peripheral data){
        if (gatewayId==null) return  false;
        gatewayId = gatewayId.trim();
        if (gatewayId.equals("")) return  false;
        if (data.getVendor()==null) return  false;
        data.setVendor((data.getVendor().trim()));
        if (data.getVendor().equals("")) return false;
        return true;
    }

    @PostMapping( value="/{gatewayId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Peripheral addPeripheral(@PathVariable String gatewayId, @Valid @RequestBody Peripheral peripheral){
        var validation = validatePeripheral(gatewayId, peripheral);
        if (!validation) return null;
        return peripheralServices.add(gatewayId,peripheral);
    }

    @DeleteMapping("/{gatewayId}/{uid}")
    public boolean deleteGateway(@PathVariable String gatewayId, @PathVariable int uid){
        if (gatewayId==null) return  false;
        gatewayId = gatewayId.trim();
        if (gatewayId.equals("")) return  false;
        return peripheralServices.remove(gatewayId,uid);
    }

    @PutMapping(value = "/{gatewayId}/{uid}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Peripheral updateGateway(@PathVariable String gatewayId,@PathVariable int uid, @Valid @RequestBody Peripheral data) {
        var validation = validatePeripheral(gatewayId, data);
        if (!validation) return  null;
        return peripheralServices.update(gatewayId,uid,data);
    }
}
