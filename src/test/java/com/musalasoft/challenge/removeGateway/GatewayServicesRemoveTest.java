package com.musalasoft.challenge.removeGateway;

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
 * Gateway services. Remove cases test.
 */
class GatewayServicesRemoveTest {

    GatewayServicesImpl subject;
    IGatewayRepository repositoryMock;

    Gateway gatewayToRemove = null;

    /**
     * Configure to simulate the entity exists.
     */
    private void configure_Happyway(){
        gatewayToRemove = TestDataGeneratorHelper.GenerateGateway((byte)1);
        Mockito.when(repositoryMock.findGateWayById(gatewayToRemove.getId())).thenReturn(gatewayToRemove);
    }

    /**
     * Configure to simulate the entity exists.
     */
    private void configure_Entity_DoesntExist(){
        gatewayToRemove = TestDataGeneratorHelper.GenerateGateway((byte)1);
        Mockito.when(repositoryMock.findGateWayById(gatewayToRemove.getId())).thenReturn(null);
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
    public void RemoveGateway_OnSuccess_RepositorySave(){
        configure_Happyway();

        var result= subject.remove(gatewayToRemove.getId());

        verify(repositoryMock, times(1)).remove(gatewayToRemove.getId());
    }

    @Test()
    public void RemoveGateway_OnSuccess_ReturnsTrue(){
        configure_Happyway();

        var result= subject.remove(gatewayToRemove.getId());

        Assertions.assertTrue(result);
    }

    @Test()
    public void RemoveGateway_NoEntityFound_RepositoryDontSave(){
        configure_Entity_DoesntExist();

        var result= subject.remove(gatewayToRemove.getId());

        verify(repositoryMock, never()).remove(gatewayToRemove.getId());
    }

    @Test()
    public void updateGateway_NoEntityFound_ReturnFalse(){

        configure_Entity_DoesntExist();

        var result= subject.remove(gatewayToRemove.getId());

        Assertions.assertFalse(result);
    }
}
