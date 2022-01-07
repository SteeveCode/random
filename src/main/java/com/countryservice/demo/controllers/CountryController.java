package com.countryservice.demo.controllers;

import com.countryservice.demo.beans.Country;
import com.countryservice.demo.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CountryController {
    @Autowired
    CountryService countryService;

    @GetMapping("/getcountries")
    public ResponseEntity<List<Country>> getCounties(){
        List<Country> countries = countryService.getAllCountries();
        return new ResponseEntity<>(countries, HttpStatus.FOUND);
    }

    @GetMapping("/getcountries/{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable ("id") int id){
        try{
        Country country = countryService.getCountryById(id);
        return new ResponseEntity<Country>(country,HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getcountries/countryname")
    public ResponseEntity<Country> getCountryByName(@RequestParam(value="name") String countryName){
        try{
            Country country = countryService.getCountryByName(countryName);
            return new ResponseEntity<Country>(country,HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addcountry")
    public ResponseEntity<Country> addCountry(@RequestBody Country country){
        country = countryService.addCountry(country);
        return new ResponseEntity<Country>(country, HttpStatus.CREATED);
    }

    @PutMapping("/updatecountry/{id}")
    public ResponseEntity<Country> updateCountry(@PathVariable("id") int id, @RequestBody Country country){
        try{
            Country countryExist = countryService.getCountryById(id);
            countryExist.setCountryName(country.getCountryName());
            countryExist.setCountryCapital(country.getCountryCapital());
            countryService.updateCountry(countryExist);
            return new ResponseEntity<Country>(country, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deletecountry/{id}")
    public ResponseEntity<Country> deleeCountry(@PathVariable("id") int id){
        Country country = null;
        try {
            country = countryService.getCountryById(id);
            countryService.deleteCountry(country);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(country, HttpStatus.OK);
    }

//    DeleteMapping("/deletecountry/{id}")
//    public AddResponse deleeCountry(@PathVariable("id") int id){
//        return countryService.deleteCountry(id);
//    }

}
