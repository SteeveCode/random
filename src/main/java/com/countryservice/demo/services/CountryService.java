package com.countryservice.demo.services;

import com.countryservice.demo.beans.Country;
import com.countryservice.demo.controllers.AddResponse;
import com.countryservice.demo.repository.CountryRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


// Note: the Hashmap below is used to store data temporarily until a database is set up
@Component
@Service
public class CountryService {
//    static HashMap<Integer, Country> countryIdMap;

//    public CountryService() {
//        countryIdMap = new HashMap<Integer, Country>();
//        Country indiaCountry = new Country(1, "India", "Delhi");
//        Country usaCountry = new Country(2, "Us", "Washington");
//        Country ukCountry = new Country(3, "Uk", "London");
//
//        countryIdMap.put(1, indiaCountry);
//        countryIdMap.put(2, usaCountry);
//        countryIdMap.put(3, ukCountry);
//    }

    @Autowired
    CountryRepository countryRepository;
    public List<Country> getAllCountries(){
        return countryRepository.findAll();
//        val countryIdMap = CountryService.countryIdMap;
//        List countries = new ArrayList<>(countryIdMap.values());
//        return countries;
    }

    public Country getCountryById(int id){
        //return countryRepository.findById(id).get();
        List<Country> countries = countryRepository.findAll();
        Country country = null;
        for(Country con:countries){
            if(con.getId() == id)
                country = con;
        }
//        Country country = countryIdMap.get(id);
       return country;
    }

    public Country getCountryByName(String countryName){

        List<Country> countries = countryRepository.findAll();
        Country country = null;
        for(Country con:countries){
            if(con.getCountryName().equalsIgnoreCase(countryName)){
                country = con;
            }
        }
        return country;
    }
////       Country country = null;
////       for(int i: countryIdMap.keySet()){
////           if(countryIdMap.get(i).getCountryName().equals(countryName)){
////               country = countryIdMap.get(i);
//           }
//       }
//        return country;


    public Country addCountry(Country country){
        countryRepository.save(country);
//        country.setId(getMaxId());
//        countryIdMap.put(country.getId(), country);
        return country;
    }

    // Utility method to get max Id
//    public static int getMaxId(){
//        int maxId = 0;
//        for(int i: countryIdMap.keySet()){
//            if(maxId <= i){
//                maxId = i;
//            }
//        }
//        return maxId + 1;
//    }
    public Country updateCountry(Country country){
        countryRepository.save(country);
//        if(country.getId()> 0){
//            countryIdMap.put(country.getId(), country);
//        }
        return country;
    }


    public void deleteCountry(Country country){
        countryRepository.delete(country);
    }
//    public AddResponse deleteCountry(int id){
//        countryRepository.deleteById(id);
//        AddResponse response = new AddResponse();
//        response.setMsg("Country deleted...");
//        response.setId(id);
//        return response;
//    }

}

