package com.countryservice.demo;

import com.countryservice.demo.beans.Country;
import com.countryservice.demo.repository.CountryRepository;
import com.countryservice.demo.services.CountryService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class ServiceMockitoTest{

    @Mock
    CountryRepository countryRepository;

    @InjectMocks
    CountryService countryService;

    List<Country> myCountries;

    @Test
    @Order(1)
    void test_shouldGetAllCounties() {
        myCountries = new ArrayList<Country>();
        myCountries.add(new Country(1, "India", "Delhi"));
        myCountries.add(new Country(1, "US", "Washington"));

        when(countryRepository.findAll()).thenReturn(myCountries); //Mocking

        assertEquals(2, countryService.getAllCountries().size());
    }

    @Test
    @Order(2)
    void test_shouldGetCountryId(){
        myCountries = new ArrayList<Country>();
        myCountries.add(new Country(1, "India", "Delhi"));
        myCountries.add(new Country(2, "US", "Washington"));
        int countryId = 1;

        when(countryRepository.findAll()).thenReturn(myCountries); //Mocking
        assertEquals(1,countryService.getCountryById(countryId).getId());

    }

    @Test
    @Order(3)
    void test_shouldGetCountryName(){
        myCountries = new ArrayList<Country>();
        myCountries.add(new Country(1, "India", "Delhi"));
        myCountries.add(new Country(2, "US", "Washington"));
        String countryName = "India";

        when(countryRepository.findAll()).thenReturn(myCountries); //Mocking
        assertEquals(countryName,countryService.getCountryByName(countryName).getCountryName());
    }

    @Test
    @Order(4)
    void test_shouldAddCountry(){
        Country country = new Country(3, "Germany", "Berlin");

        when(countryRepository.save(country)).thenReturn(country); //Mocking
        assertEquals(country, countryService.addCountry(country));
    }

    @Test
    @Order(5)
    void test_shouldUpdateCountry(){
        Country country = new Country(3, "Germany", "Berlin");

        when(countryRepository.save(country)).thenReturn(country); //Mocking
        assertEquals(country, countryService.updateCountry(country));
    }

    @Test
    @Order(6)
    void test_shouldDeleteCountry(){
        Country country = new Country(3, "Germany", "Berlin");

        countryService.deleteCountry(country);
        verify(countryRepository, times(1)).delete(country); //Mocking
    }

}
