package com.musalasoft.challenge.removePeripheral;

import com.musalasoft.challenge.IGatewayRepository;
import com.musalasoft.challenge.PeripheralServicesImpl;
import com.musalasoft.challenge.TestDataGeneratorHelper;
import com.musalasoft.challenge.entities.Gateway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

/**
 * Peripheral services. Remove cases test.
 */
class PeripheralServicesRemoveTest {

    PeripheralServicesImpl subject;
    Gateway gatewayFound = null;
    String gatewayId = null;
    int currentPeripheralId = 0;
    IGatewayRepository repositoryMock;

    /**
     * Configure to simulate the entity exists.
     */
    private void configure_HappyCase(){
        gatewayFound = TestDataGeneratorHelper.GenerateGateway((byte)1);
        gatewayId = gatewayFound.getId();
        var peripheralFound = TestDataGeneratorHelper.GeneratePeripheral((byte)2);
        currentPeripheralId = peripheralFound.getUid();
        Mockito.when(repositoryMock.findGateWayById (gatewayFound.getId())).thenReturn(gatewayFound);
        gatewayFound.getPeripherals().add(peripheralFound);
    }

    /**
     * Configure to simulate the gateway not found.
     */
    private void configure_GatewayNotFound(){
        gatewayFound = TestDataGeneratorHelper.GenerateGateway((byte)1);
        gatewayId = gatewayFound.getId();
        gatewayFound = null;
        var peripheralFound = TestDataGeneratorHelper.GeneratePeripheral((byte)2);
        currentPeripheralId = peripheralFound.getUid();
        Mockito.when(repositoryMock.findGateWayById (gatewayId)).thenReturn(gatewayFound);
        //gatewayFound.getPeripherals().add(peripheralFound);
    }

    /**
     * Configure to simulate the gateway not found.
     */
    private void configure_PeripheralNotFound(){
        gatewayFound = TestDataGeneratorHelper.GenerateGateway((byte)1);
        gatewayId = gatewayFound.getId();
        var peripheralFound = TestDataGeneratorHelper.GeneratePeripheral((byte)2);
        currentPeripheralId = peripheralFound.getUid();
        Mockito.when(repositoryMock.findGateWayById (gatewayFound.getId())).thenReturn(gatewayFound);
        //gatewayFound.getPeripherals().add(peripheralFound);
    }

    @BeforeEach
    void setUp() {
        repositoryMock = mock(IGatewayRepository.class);
        subject = new PeripheralServicesImpl(repositoryMock);
     }

    @AfterEach
    void tearDown() {
    }

    @Test()
    public void RemovePeripheral_OnSuccess_RepositorySave(){
        configure_HappyCase();

        var result= subject.remove(gatewayId,currentPeripheralId);

        verify(repositoryMock, times(1)).update(gatewayFound);
    }

    @Test()
    public void RemovePeripheral_OnSuccess_ReturnsTrue(){
        configure_HappyCase();

        var result= subject.remove(gatewayId,currentPeripheralId);

        Assertions.assertTrue(result);
    }

    @Test()
    public void RemovePeripheral_NoGatewayFound_RepositoryDontSave(){
        configure_GatewayNotFound();

        var result= subject.remove(gatewayId,currentPeripheralId);

        verify(repositoryMock, never()).update(any());
    }

    @Test()
    public void RemovePeripheral_NoGatewayFound_ReturnFalse(){

        configure_GatewayNotFound();

        var result= subject.remove(gatewayId,currentPeripheralId);

        Assertions.assertFalse(result);
    }

    @Test()
    public void RemovePeripheral_NoPeripheralFound_RepositoryDontSave(){
        configure_PeripheralNotFound();

        var result= subject.remove(gatewayId,currentPeripheralId);

        verify(repositoryMock, never()).update(any());
    }

    @Test()
    public void RemovePeripheral_NoPeripheralFound_ReturnFalse(){

        configure_PeripheralNotFound();

        var result= subject.remove(gatewayId,currentPeripheralId);

        Assertions.assertFalse(result);
    }
}
