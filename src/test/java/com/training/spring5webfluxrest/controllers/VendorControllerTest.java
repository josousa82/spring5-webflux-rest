package com.training.spring5webfluxrest.controllers;

import com.training.spring5webfluxrest.domain.Vendor;
import com.training.spring5webfluxrest.repositories.VendorRepository;
import com.training.spring5webfluxrest.services.VendorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
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
    void getVendorsTest() {
        BDDMockito.given(vendorRepository.findAll())
                .willReturn(Flux.just(VENDOR_1, VENDOR_2));

        webTestClient.get().uri(VENDORS_API_URL)
                .exchange()
                .expectBodyList(Vendor.class)
                .hasSize(2);
    }

    @Test
    void getVendorByIdTest() {

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

    @Test
    void createVendorTest() {
        BDDMockito.given(vendorRepository.saveAll(Mockito.any(Publisher.class)))
                .willReturn(Flux.just(VENDOR_1));

        Mono<Vendor> vendorMono = Mono.just(VENDOR_1);

        webTestClient.post()
                .uri(VENDORS_API_URL)
                .body(vendorMono, Vendor.class)
                .exchange()
                .expectStatus()
                .isCreated();
    }

    @Test
    void updateCategoryTest() {

        VENDOR_1.setId(ID_1);
        VENDOR_1.setFirstName("Updated vendor 1");

        BDDMockito.given(vendorRepository.save(Mockito.any(Vendor.class)))
                .willReturn(Mono.just(VENDOR_1));

        var vendorToUpdateMono = Mono.just(Vendor
                .builder()
                .firstName("Updated vendor 1")
                .build());

        webTestClient.put()
                .uri(VENDORS_API_URL + ID_1)
                .body(vendorToUpdateMono, Vendor.class)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Vendor.class)
                .value(vendor -> assertEquals("Updated vendor 1", vendor.getFirstName()));
    }
}