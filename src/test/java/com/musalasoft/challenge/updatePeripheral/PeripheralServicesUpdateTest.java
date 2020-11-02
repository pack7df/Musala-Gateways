package com.musalasoft.challenge.updatePeripheral;

import com.musalasoft.challenge.IGatewayRepository;
import com.musalasoft.challenge.PeripheralServicesImpl;
import com.musalasoft.challenge.TestDataGeneratorHelper;
import com.musalasoft.challenge.entities.Gateway;
import com.musalasoft.challenge.entities.Peripheral;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;


/**
 * Peripheral services. Update cases test.
 */

public class PeripheralServicesUpdateTest {
    PeripheralServicesImpl subject;
    Gateway gatewayFound = null;
    Peripheral parameters = null;
    String gatewayId = null;
    int currentPeripheralId = 0;
    IGatewayRepository repositoryMock;

    @BeforeEach
    void setUp() {
        repositoryMock = mock(IGatewayRepository.class);
        subject = new PeripheralServicesImpl(repositoryMock);

    }

    private void configure_HappyCase(){
        gatewayFound = TestDataGeneratorHelper.GenerateGateway((byte)1);
        gatewayId = gatewayFound.getId();
        var peripheralFound = TestDataGeneratorHelper.GeneratePeripheral((byte)2);
        currentPeripheralId = peripheralFound.getUid();
        gatewayFound.getPeripherals().add(peripheralFound);
        Mockito.when(repositoryMock.findGateWayById (gatewayFound.getId())).thenReturn(gatewayFound);

        parameters = TestDataGeneratorHelper.GeneratePeripheral((byte)3);
        parameters.setUid(currentPeripheralId);
    }

    private void configure_PeripheralId_Change(){

        gatewayFound = TestDataGeneratorHelper.GenerateGateway((byte)1);
        gatewayId = gatewayFound.getId();
        var peripheralFound = TestDataGeneratorHelper.GeneratePeripheral((byte)2);
        currentPeripheralId = peripheralFound.getUid();
        gatewayFound.getPeripherals().add(peripheralFound);
        Mockito.when(repositoryMock.findGateWayById (gatewayFound.getId())).thenReturn(gatewayFound);

        parameters = TestDataGeneratorHelper.GeneratePeripheral((byte)3);
    }

    @Test()
    public void updatePeripheral_OnSuccess_RepositorySave(){
        configure_HappyCase();

        var result= subject.update(gatewayId,currentPeripheralId,parameters);

        verify(repositoryMock, times(1)).update(gatewayFound);
    }

    @Test()
    public void updatePeripheral_OnSuccess_Peripheral_FieldSaved(){
        configure_HappyCase();
        var result= subject.update(gatewayId,currentPeripheralId, parameters);

        Assertions.assertEquals(1,gatewayFound.getPeripherals().stream().count());
        var saved = gatewayFound.getPeripherals().stream().findFirst().get();

        Assertions.assertEquals(parameters.getUid(),saved.getUid());
        Assertions.assertEquals(parameters.getVendor(),saved.getVendor());
        Assertions.assertEquals(parameters.isStatus(),saved.isStatus());
    }

    @Test()
    public void updatePeripheral_OnSuccess_ReturnsTrue(){
        configure_HappyCase();

        var result= subject.update(gatewayId,currentPeripheralId,parameters);

        Assertions.assertTrue (result);
    }



    @Test()
    public void updatePeripheral_IdChange_OnSuccess_RepositorySave(){
        configure_PeripheralId_Change();

        var result= subject.update(gatewayId,currentPeripheralId,parameters);

        verify(repositoryMock, times(1)).update(gatewayFound);
    }

    @Test()
    public void updatePeripheral_IdChange_Peripheral_FieldSaved(){
        configure_PeripheralId_Change();
        var result= subject.update(gatewayId,currentPeripheralId, parameters);

        Assertions.assertEquals(1,gatewayFound.getPeripherals().stream().count());
        var saved = gatewayFound.getPeripherals().stream().findFirst().get();

        Assertions.assertEquals(parameters.getUid(),saved.getUid());
        Assertions.assertEquals(parameters.getVendor(),saved.getVendor());
        Assertions.assertEquals(parameters.isStatus(),saved.isStatus());
    }

    @Test()
    public void updatePeripheral_IdChange_OnSuccess_ReturnsTrue(){
        configure_PeripheralId_Change();

        var result= subject.update(gatewayId,currentPeripheralId,parameters);

        Assertions.assertTrue (result);
    }

    private void configure_GatewayNotFound(){
        gatewayFound = TestDataGeneratorHelper.GenerateGateway((byte)1);
        gatewayId = gatewayFound.getId();
        gatewayFound = null;
        //var peripheralFound = TestDataGeneratorHelper.GeneratePeripheral((byte)2);
        //currentPeripheralId = peripheralFound.getUid();
        //gatewayFound.getPeripherals().add(peripheralFound);
        Mockito.when(repositoryMock.findGateWayById (gatewayId)).thenReturn(gatewayFound);

        parameters = TestDataGeneratorHelper.GeneratePeripheral((byte)3);
        //parameters.setUid(currentPeripheralId);
    }

    @Test()
    public void updatePeripheral_GatewayNotFound_DontSave(){
        configure_GatewayNotFound();
        var result= subject.update(gatewayId,currentPeripheralId,parameters);

        verify(repositoryMock, times(0)).update(Mockito.any());
    }

    @Test()
    public void updatePeripheral_GatewayNotFound_ReturnFalse(){
        configure_GatewayNotFound();
        var result= subject.update(gatewayId,currentPeripheralId,parameters);

        Assertions.assertFalse(result);
    }

    private void configure_PeripheralId_Doesnt_exists(){
        gatewayFound = TestDataGeneratorHelper.GenerateGateway((byte)1);
        gatewayId = gatewayFound.getId();
        var peripheralFound = TestDataGeneratorHelper.GeneratePeripheral((byte)2);
        currentPeripheralId = peripheralFound.getUid();
        //gatewayFound.getPeripherals().add(peripheralFound);
        Mockito.when(repositoryMock.findGateWayById (gatewayFound.getId())).thenReturn(gatewayFound);

        parameters = TestDataGeneratorHelper.GeneratePeripheral((byte)3);
        parameters.setUid(currentPeripheralId);
    }

    @Test()
    public void updatePeripheral_PeripheralId_doesnt_Exist_DontSave(){
        configure_PeripheralId_Doesnt_exists();
        var result= subject.update(gatewayId,currentPeripheralId,parameters);

        verify(repositoryMock, times(0)).update(Mockito.any());
    }

    @Test()
    public void updatePeripheral_PeripheralId_doesnt_Exist_ReturnFalse(){
        configure_PeripheralId_Doesnt_exists();
        var result= subject.update(gatewayId,currentPeripheralId,parameters);

        Assertions.assertFalse(result);
    }

    private void configure_PeripheralIdChange_AlreadyExist(){
        gatewayFound = TestDataGeneratorHelper.GenerateGateway((byte)1);
        gatewayId = gatewayFound.getId();
        var peripheralFound = TestDataGeneratorHelper.GeneratePeripheral((byte)2);
        currentPeripheralId = peripheralFound.getUid();
        gatewayFound.getPeripherals().add(peripheralFound);
        Mockito.when(repositoryMock.findGateWayById (gatewayFound.getId())).thenReturn(gatewayFound);

        parameters = TestDataGeneratorHelper.GeneratePeripheral((byte)3);
        var anotherPeripheral = TestDataGeneratorHelper.GeneratePeripheral((byte)4);
        gatewayFound.getPeripherals().add(anotherPeripheral);
        parameters.setUid(anotherPeripheral.getUid());
    }

    @Test()
    public void updatePeripheral_NewPeripheralId_Exist_DontSave(){
        configure_PeripheralIdChange_AlreadyExist();
        var result= subject.update(gatewayId,currentPeripheralId,parameters);

        verify(repositoryMock, times(0)).update(Mockito.any());
    }

    @Test()
    public void updatePeripheral_NewPeripheralId_Exist_ReturnFalse(){
        configure_PeripheralIdChange_AlreadyExist();
        var result= subject.update(gatewayId,currentPeripheralId,parameters);

        Assertions.assertFalse(result);
    }
}