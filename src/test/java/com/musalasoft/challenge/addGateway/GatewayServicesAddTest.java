package com.musalasoft.challenge.addGateway;

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
 * Gateway services. Add cases test.
*/
class GatewayServicesAddTest {

    GatewayServicesImpl subject;
    IGatewayRepository repositoryMock;
    Gateway addSample = TestDataGeneratorHelper.GenerateGateway((byte)1);
    Gateway gatewayFound = null;

    private void configure_HappyCase(){
        Mockito.when(repositoryMock.findGatewayBySerial(addSample.getSerial())).thenReturn(null);
    }

    private void configure_GatewayExistsCase(){
        gatewayFound = TestDataGeneratorHelper.GenerateGateway((byte)100);
        Mockito.when(repositoryMock.findGatewayBySerial(addSample.getSerial())).thenReturn(gatewayFound);
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
    public void addGateway_OnSuccess_RepositorySave(){
        configure_HappyCase();

        var result= subject.add(addSample);

        verify(repositoryMock, times(1)).insert(addSample);
    }

    @Test()
    public void addGateway_OnSuccess_ReturnsTrue(){
        configure_HappyCase();

        var result= subject.add(addSample);

        Assertions.assertTrue(result);
    }

    @Test()
    public void addGateway_OnExistsPreviously_RepositoryDontSave(){
        configure_GatewayExistsCase();

        var result= subject.add(addSample);

        verify(repositoryMock, never()).insert(addSample);
    }

    @Test()
    public void addGateway_OnExistsPreviously_ReturnFalse(){

        configure_GatewayExistsCase();

        var result= subject.add(addSample);

        Assertions.assertFalse(result);
    }
}
