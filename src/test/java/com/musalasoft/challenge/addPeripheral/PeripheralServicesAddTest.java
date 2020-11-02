package com.musalasoft.challenge.addPeripheral;

import com.musalasoft.challenge.IGatewayRepository;
import com.musalasoft.challenge.PeripheralServicesImpl;
import com.musalasoft.challenge.TestDataGeneratorHelper;
import com.musalasoft.challenge.entities.Gateway;
import com.musalasoft.challenge.entities.Peripheral;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Date;

import static java.util.Optional.of;
import static java.util.Optional.ofNullable;
import static org.mockito.Mockito.*;


/**
 * Peripheral services. Add cases test.
 */

public class PeripheralServicesAddTest {
    PeripheralServicesImpl subject;
    Gateway gatewayFound = null;
    Peripheral parameters = null;
    String gatewayId = null;
    IGatewayRepository repositoryMock;

    @BeforeEach
    void setUp() {
        repositoryMock = mock(IGatewayRepository.class);
        subject = new PeripheralServicesImpl(repositoryMock);

    }

    private void configure_HappyCase(){
        gatewayFound = TestDataGeneratorHelper.GenerateGateway((byte)2);
        gatewayId = gatewayFound.getId();
        parameters = TestDataGeneratorHelper.GeneratePeripheral((byte)1);
        Mockito.when(repositoryMock.findById(gatewayFound.getId())).thenReturn(of(gatewayFound));
    }

    @Test()
    public void addPeripheral_OnSuccess_RepositorySave(){
        configure_HappyCase();

        var result= subject.add(gatewayId,parameters);

        verify(repositoryMock, times(1)).save(gatewayFound);
    }

    @Test()
    public void addPeripheral_OnSuccess_Peripheral_FieldSaved(){
        configure_HappyCase();
        var result= subject.add(gatewayId,parameters);

        Assertions.assertEquals(1,gatewayFound.getPeripherals().stream().count());
        var saved = gatewayFound.getPeripherals().stream().findFirst().get();

        //Ensure date is approximately the time was called.
        Assertions.assertTrue(new Date().getTime() - saved.getCreated().getTime()<1000);
        Assertions.assertEquals(parameters.getUid(),saved.getUid());
        Assertions.assertEquals(parameters.getVendor(),saved.getVendor());
        Assertions.assertEquals(parameters.isStatus(),saved.isStatus());
    }

    @Test()
    public void addPeripheral_OnSuccess_ReturnsTrue(){
        configure_HappyCase();
        var result= subject.add(gatewayId,parameters);

        Assertions.assertTrue (result);
    }

    private void configure_GatewayNotFound(){
        gatewayFound = TestDataGeneratorHelper.GenerateGateway((byte)2);;
        gatewayId = gatewayFound.getId();
        gatewayFound = null;
        parameters = TestDataGeneratorHelper.GeneratePeripheral((byte)1);
        Mockito.when(repositoryMock.findById (gatewayId)).thenReturn(ofNullable(gatewayFound));
    }

    @Test()
    public void addPeripheral_GatewayNotFound_DontSave(){
        configure_GatewayNotFound();
        var result= subject.add(gatewayId,parameters);

        verify(repositoryMock, times(0)).save(Mockito.any());
    }

    @Test()
    public void addPeripheral_GatewayNotFound_ReturnFalse(){
        configure_GatewayNotFound();
        var result= subject.add(gatewayId,parameters);

        Assertions.assertFalse(result);
    }

    private void configure_PeripheralIdExist(){
        gatewayFound = TestDataGeneratorHelper.GenerateGateway((byte)2);
        gatewayId = gatewayFound.getId();
        parameters = TestDataGeneratorHelper.GeneratePeripheral((byte)1);
        var existingPeripheral = TestDataGeneratorHelper.GeneratePeripheral((byte)3);
        existingPeripheral.setUid(parameters.getUid());
        gatewayFound.getPeripherals().add(existingPeripheral);
        Mockito.when(repositoryMock.findById (gatewayFound.getId())).thenReturn(of(gatewayFound));
    }

    @Test()
    public void addPeripheral_PeripheralIdExist_DontSave(){
        configure_PeripheralIdExist();
        var result= subject.add(gatewayId,parameters);

        verify(repositoryMock, times(0)).save(Mockito.any());
    }

    @Test()
    public void addPeripheral_PeripheralIdExist_ReturnFalse(){
        configure_PeripheralIdExist();
        var result= subject.add(gatewayId,parameters);

        Assertions.assertFalse(result);
    }

    private void configure_PeripheralIsFull(){
        gatewayFound = TestDataGeneratorHelper.GenerateGateway((byte)2);
        gatewayId = gatewayFound.getId();
        parameters = TestDataGeneratorHelper.GeneratePeripheral((byte)1);
        for (int i=0; i< 10; i++)
        {
            var existingPeripheral = TestDataGeneratorHelper.GeneratePeripheral((byte)(100+i));
            gatewayFound.getPeripherals().add(existingPeripheral);
        }
        Mockito.when(repositoryMock.findById (gatewayFound.getId())).thenReturn(ofNullable(gatewayFound));
    }

    @Test()
    public void addPeripheral_PeripheralList_IsFull_DontSave(){
        configure_PeripheralIsFull();
        var result= subject.add(gatewayId,parameters);

        verify(repositoryMock, times(0)).save(Mockito.any());
    }

    @Test()
    public void addPeripheral_PeripheralList_IsFull_ReturnFalse(){
        configure_PeripheralIsFull();
        var result= subject.add(gatewayId,parameters);

        Assertions.assertFalse(result);
    }
}
