package com.countryservice.demo;

import com.countryservice.demo.controllers.CountryController;
import com.countryservice.demo.services.CountryService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ComponentScan
@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest
public class ControllerMockMvcTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    CountryService countryService;

    @InjectMocks
    CountryController countryController;
}
