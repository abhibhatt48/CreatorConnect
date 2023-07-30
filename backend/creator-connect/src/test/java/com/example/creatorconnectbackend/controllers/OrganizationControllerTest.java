package com.example.creatorconnectbackend.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.example.creatorconnectbackend.models.Organization;
import com.example.creatorconnectbackend.services.OrganizationService;

public class OrganizationControllerTest {
    private OrganizationController organizationController;

    @Mock
    private OrganizationService organizationService;

    @BeforeEach
    public void setUp() {
        // Initialize Mockito annotations and create the OrganizationController instance
        MockitoAnnotations.openMocks(this);
        organizationController = new OrganizationController(organizationService);
    }

    @Test
    public void testRegisterOrganization() {
        // Prepare test data
        Organization organization = new Organization();
        Long userId = 12345L;
        BindingResult bindingResult = mock(BindingResult.class);

        // Mock the behavior of the BindingResult and OrganizationService
        when(bindingResult.hasErrors()).thenReturn(false);
        when(organizationService.register(organization, userId)).thenReturn(organization);

        // Execute the controller method
        ResponseEntity<?> response = organizationController.registerOrganization(userId, organization, bindingResult);

        // Verify the result
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertTrue(response.getBody() instanceof Organization);
        assertEquals(organization, response.getBody());
    }

    @Test
    public void testRegisterOrganization_WithValidationErrors() {
        // Prepare test data
        Organization organization = new Organization();
        Long userId = 12345L;
        BindingResult bindingResult = mock(BindingResult.class);
        ObjectError error = new ObjectError("organization", "error message");

        // Mock the behavior of the BindingResult with validation errors
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getAllErrors()).thenReturn(Arrays.asList(error));

        // Execute the controller method
        ResponseEntity<?> response = organizationController.registerOrganization(userId, organization, bindingResult);

        // Verify the result
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody() instanceof List);

        List<String> errors = Arrays.asList("error message");
        assertEquals(errors, response.getBody());
    }

    @Test
    public void testGetOrganizationById() {
        // Prepare test data
        Organization organization = new Organization();
        Long id = 1L;
        when(organizationService.getById(id)).thenReturn(organization);

        // Execute the controller method
        ResponseEntity<Organization> response = organizationController.getOrganizationById(id);

        // Verify the result
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(organization, response.getBody());
    }

    @Test
    public void testUpdateOrganization() {
        // Prepare test data
        Organization organization = new Organization();
        Long id = 1L;
        BindingResult bindingResult = mock(BindingResult.class);

        // Mock the behavior of the BindingResult and OrganizationService
        when(bindingResult.hasErrors()).thenReturn(false);
        when(organizationService.update(id, organization)).thenReturn(organization);

        // Execute the controller method
        ResponseEntity<?> response = organizationController.updateOrganization(id, organization, bindingResult);

        // Verify the result
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof Organization);
        assertEquals(organization, response.getBody());
    }

    @Test
    public void testUpdateOrganization_WithValidationErrors() {
        // Prepare test data
        Organization organization = new Organization();
        Long id = 1L;
        BindingResult bindingResult = mock(BindingResult.class);
        ObjectError error = new ObjectError("organization", "error message");

        // Mock the behavior of the BindingResult with validation errors
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getAllErrors()).thenReturn(Arrays.asList(error));

        // Execute the controller method
        ResponseEntity<?> response = organizationController.updateOrganization(id, organization, bindingResult);

        // Verify the result
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody() instanceof List);

        List<String> errors = Arrays.asList("error message");
        assertEquals(errors, response.getBody());
    }

    @Test
    public void testGetAllOrganizations() {
        // Prepare test data
        Organization organization1 = new Organization();
        Organization organization2 = new Organization();
        List<Organization> organizations = Arrays.asList(organization1, organization2);
        when(organizationService.getAll()).thenReturn(organizations);

        // Execute the controller method
        ResponseEntity<List<Organization>> response = organizationController.getAllOrganizations();

        // Verify the result
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(organizations, response.getBody());
    }

    @Test
    public void testDeleteOrganizationById() {
        // Prepare test data
        Long id = 1L;

        // Execute the controller method
        ResponseEntity<?> response = organizationController.deleteOrganizationById(id);

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(organizationService).deleteById(id);
    }
}
