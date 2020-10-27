package com.musalasoft.challenge.updateGateway;

import com.musalasoft.challenge.GatewayServicesImpl;
import com.musalasoft.challenge.IGatewayRepository;
import com.musalasoft.challenge.TestDataGeneratorHelper;
import com.musalasoft.challenge.entities.Gateway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

/**
 * Gateway services. Update cases test.
 */
class GatewayServicesUpdateTest {

    GatewayServicesImpl subject;
    IGatewayRepository repositoryMock;
    Gateway updateSample = TestDataGeneratorHelper.GenerateGateway((byte)1);
    Gateway gatewaySerialFound;
    Gateway gatewayToEditFound =  null;


    /**
     * Configure to simulate the data is modified. There is no other gateway with the same name.
     */
    private void configure_SerialChangeCase(){
        gatewayToEditFound = TestDataGeneratorHelper.GenerateGateway((byte)2);
        Mockito.when(repositoryMock.FindGateWayById(gatewayToEditFound.getId())).thenReturn(gatewayToEditFound);

        updateSample = TestDataGeneratorHelper.GenerateGateway((byte)1);
        updateSample.setId(gatewayToEditFound.getId());

        gatewaySerialFound = null;
        Mockito.when(repositoryMock.FindGatewayBySerial(updateSample.getSerial())).thenReturn(gatewaySerialFound);


    }

    /**
     * Configure to simulate the data is modified but serial. The gateway exists and is the same as entity in DB to edit.
     */
    private void configure_SerialDoesntChangeCase(){
        gatewayToEditFound = TestDataGeneratorHelper.GenerateGateway((byte)2);
        Mockito.when(repositoryMock.FindGateWayById(gatewayToEditFound.getId())).thenReturn(gatewayToEditFound);

        updateSample = TestDataGeneratorHelper.GenerateGateway((byte)1);
        updateSample.setId(gatewayToEditFound.getId());

        gatewaySerialFound = gatewayToEditFound;
        Mockito.when(repositoryMock.FindGatewayBySerial(updateSample.getSerial())).thenReturn(gatewaySerialFound);


    }

    /**
     * Configure to simulate the data is modified but serial. The gateway exists and is not the same as entity in DB.
     */
    private void configure_GatewayExistsCase(){

        gatewayToEditFound = TestDataGeneratorHelper.GenerateGateway((byte)2);
        Mockito.when(repositoryMock.FindGateWayById(gatewayToEditFound.getId())).thenReturn(gatewayToEditFound);

        updateSample = TestDataGeneratorHelper.GenerateGateway((byte)1);
        updateSample.setId(gatewayToEditFound.getId());

        gatewaySerialFound = TestDataGeneratorHelper.GenerateGateway((byte)100);
        Mockito.when(repositoryMock.FindGatewayBySerial(updateSample.getSerial())).thenReturn(gatewaySerialFound);



    }

    /**
     * Configure to simulate there is no entity in DB to edit.
     */
    private void configure_GatewayToEdit_DoesntExist(){
        gatewayToEditFound = null;

        updateSample = TestDataGeneratorHelper.GenerateGateway((byte)1);
        Mockito.when(repositoryMock.FindGateWayById(updateSample.getId())).thenReturn(gatewayToEditFound);

        gatewaySerialFound = null;
        Mockito.when(repositoryMock.FindGatewayBySerial(updateSample.getSerial())).thenReturn(gatewaySerialFound);

    }

    @BeforeEach
    void setUp() {
        repositoryMock = mock(IGatewayRepository.class);
        subject = new GatewayServicesImpl(repositoryMock);
     }

    @AfterEach
    void tearDown() {
    }

    @Test()
    public void UpdateGateway_OnSuccess_RepositorySave(){
        configure_SerialChangeCase();

        var result= subject.update(updateSample);

        verify(repositoryMock, times(1)).update(updateSample);
    }

    @Test()
    public void UpdateGateway_OnSuccess_ReturnsTrue(){
        configure_SerialChangeCase();

        var result= subject.update(updateSample);

        Assertions.assertTrue(result);
    }

    @Test()
    public void UpdateGateway2_OnSuccess_RepositorySave(){
        configure_SerialDoesntChangeCase();

        var result= subject.update(updateSample);

        verify(repositoryMock, times(1)).update(updateSample);
    }

    @Test()
    public void UpdateGateway2_OnSuccess_ReturnsTrue(){
        configure_SerialDoesntChangeCase();

        var result= subject.update(updateSample);

        Assertions.assertTrue(result);
    }

    @Test()
    public void updateGateway_OnExistsPreviously_RepositoryDontSave(){
        configure_GatewayExistsCase();

        var result= subject.update(updateSample);

        verify(repositoryMock, never()).insert(updateSample);
    }

    @Test()
    public void updateGateway_OnExistsPreviously_ReturnFalse(){

        configure_GatewayExistsCase();

        var result= subject.update(updateSample);

        Assertions.assertFalse(result);
    }

    @Test()
    public void updateGateway_NoEntityFound_RepositoryDontSave(){
        configure_GatewayToEdit_DoesntExist();

        var result= subject.update(updateSample);

        verify(repositoryMock, never()).insert(updateSample);
    }

    @Test()
    public void updateGateway_NoEntityFound_ReturnFalse(){

        configure_GatewayToEdit_DoesntExist();

        var result= subject.update(updateSample);

        Assertions.assertFalse(result);
    }
}
