package com.countryservice.demo;

import com.countryservice.demo.beans.Country;
import com.countryservice.demo.controllers.CountryController;
import com.countryservice.demo.services.CountryService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ControllerMockitoTest {

    @Mock
    CountryService countryService;

    @InjectMocks
    CountryController countryController;

    List<Country> myCountries;
    Country country;
    ResponseEntity<List<Country>> response;

    @Test
    @Order(1)
    void test_countryControllerGetCountries(){
        myCountries = new ArrayList<>();
        myCountries.add(new Country(1, "India", "Delhi"));
        myCountries.add(new Country(2, "US", "Washington"));

        when(countryService.getAllCountries()).thenReturn(myCountries);
        response = countryController.getCounties();
        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(2, response.getBody().size());

    }

    @Test
    @Order(2)
    void test_countryControllerGetCountryById(){
        ResponseEntity<Country> response;
        country = new Country(2, "US", "'Washington'");
        int id = 2;
        when(countryService.getCountryById(id)).thenReturn(country);
        response = countryController.getCountryById(id);
        assertEquals(2, response.getBody().getId());
        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(country, response.getBody());
    }

    @Test
    @Order(3)
    void test_countryControllerGetCountryByName(){
        country = new Country(2, "USA", "'Washington'");
        String countryName = "USA";
        ResponseEntity<Country> response;
        when(countryService.getCountryByName(countryName)).thenReturn(country);
        response = countryController.getCountryByName(countryName);
        assertEquals(countryName, response.getBody().getCountryName());
        assertEquals(HttpStatus.FOUND, response.getStatusCode());
    }

    @Test
    @Order(4)
    void test_countryControllerAddCountryBy(){
        ResponseEntity<Country> response;
        country = new Country(1, "UK", "London");
        when(countryService.addCountry(country)).thenReturn(country);
        response = countryController.addCountry(country);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(country, response.getBody());
    }

    @Test
    @Order(5)
    void test_countryControllerUpdateCountry(){
        country = new Country(3, "Germany", "Berlin");
        int countryId = 3;
        when(countryService.getCountryById(countryId)).thenReturn(country);
        when(countryService.updateCountry(country)).thenReturn(country);
        ResponseEntity<Country> response = countryController.updateCountry(countryId, country);
        assertEquals(countryId, response.getBody().getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Germany", response.getBody().getCountryName());
        assertEquals("Berlin", response.getBody().getCountryCapital());
    }

    @Test
    @Order(6)
    void test_countryControllerDeleteCountry(){
        country = new Country(3, "Germany", "Berlin");
        int countryId = 3;

        when(countryService.getCountryById(countryId)).thenReturn(country);
       ResponseEntity<Country> response = countryController.deleeCountry(countryId);
       assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
