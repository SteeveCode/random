package com.countryservice.demo.beans;

import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@ToString
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String countryName;

    @Column
    private String capital;


    public Country(int id,  String countryName, String capital) {
        this.id = id;
        this.countryName = countryName;
        this.capital = capital;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCapital() {
        return capital;
    }

    public void setCountryCapital(String countryCapital) {
        this.capital = countryCapital;
    }
}
