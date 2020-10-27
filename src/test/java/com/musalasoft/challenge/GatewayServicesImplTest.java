package com.musalasoft.challenge;

import com.musalasoft.challenge.entities.Gateway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

/**
 * Gateway services test.
*/
class GatewayServicesImplTest {

    GatewayServicesImpl subject;
    IGatewayRepository repositoryMock;

    @BeforeEach
    void setUp() {
        repositoryMock = mock(IGatewayRepository.class);
        subject = new GatewayServicesImpl(repositoryMock);
    }

    @AfterEach
    void tearDown() {
    }

    Gateway addSample = TestDataGeneratorHelper.GenerateGateway((byte)1);
    Gateway gatewayFound = null;
    private void configure_AddGatewayCase_HappyWay(){
        Mockito.when(repositoryMock.FindGatewayBySerial(addSample.getSerial())).thenReturn(null);
    }

    private void configure_AddGatewayCase_GatewayExists(){
        gatewayFound = TestDataGeneratorHelper.GenerateGateway((byte)100);
        Mockito.when(repositoryMock.FindGatewayBySerial(addSample.getSerial())).thenReturn(gatewayFound);
    }

    @Test()
    public void addGateway_OnSuccess_RepositorySave(){
        configure_AddGatewayCase_HappyWay();

        var result= subject.add(addSample);

        verify(repositoryMock, times(1)).insert(addSample);
    }

    @Test()
    public void addGateway_OnSuccess_ReturnsTrue(){
        configure_AddGatewayCase_HappyWay();

        var result= subject.add(addSample);

        Assertions.assertTrue(result);
    }

    @Test()
    public void addGateway_OnExistsPreviously_RepositoryDontSave(){
        configure_AddGatewayCase_GatewayExists();

        var result= subject.add(addSample);

        verify(repositoryMock, never()).insert(addSample);
    }

    @Test()
    public void addGateway_OnExistsPreviously_ReturnFalse(){

        configure_AddGatewayCase_GatewayExists();

        var result= subject.add(addSample);

        Assertions.assertFalse(result);
    }
}
