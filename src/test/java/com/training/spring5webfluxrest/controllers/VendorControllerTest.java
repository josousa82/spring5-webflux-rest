package com.training.spring5webfluxrest.controllers;

import com.training.spring5webfluxrest.domain.Vendor;
import com.training.spring5webfluxrest.repositories.VendorRepository;
import com.training.spring5webfluxrest.services.VendorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.training.spring5webfluxrest.bootstrap.DataInitializerHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = {VendorController.class})
class VendorControllerTest {

    @MockBean
    VendorRepository vendorRepository;

    @MockBean
    VendorService vendorService;

    @Autowired
    VendorController vendorController;

    WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient.bindToController(vendorController).build();
    }

    @Test
    void getVendors() {
        BDDMockito.given(vendorRepository.findAll())
                .willReturn(Flux.just(VENDOR_1, VENDOR_2));

        webTestClient.get().uri(VENDORS_API_URL)
                .exchange()
                .expectBodyList(Vendor.class)
                .hasSize(2);
    }

    @Test
    void getVendorById() {

        VENDOR_1.setId(ID_1);
        BDDMockito.given(vendorRepository.findById(ID_1))
                .willReturn(Mono.just(VENDOR_1));

        webTestClient.get().uri(VENDORS_API_URL + ID_1)
                .exchange()
                .expectBody(Vendor.class)
                .value(vendor -> {
                    assertEquals(ID_1, vendor.getId());
                    assertEquals(VENDOR_1.getFirstName(), vendor.getFirstName());
                    assertEquals(VENDOR_1.getLastName(), vendor.getLastName());
                });
    }
}