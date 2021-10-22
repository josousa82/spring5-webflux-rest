package com.training.spring5webfluxrest.controllers;


import com.training.spring5webfluxrest.domain.Vendor;
import com.training.spring5webfluxrest.repositories.VendorRepository;
import com.training.spring5webfluxrest.services.VendorService;
import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class VendorController {

    public static final String VENDORS_API_URL = "/api/v1/vendors";
    private final VendorRepository vendorRepository;
    private final VendorService vendorService;


    public VendorController(VendorRepository vendorRepository, VendorService vendorService) {
        this.vendorRepository = vendorRepository;
        this.vendorService = vendorService;
    }


    @GetMapping(VENDORS_API_URL)
    public Flux<Vendor> getVendors() {
        return vendorRepository.findAll();
    }

    @GetMapping(VENDORS_API_URL + "/{id}")
    public Mono<Vendor> getVendorById(@PathVariable String id) {
        return vendorRepository.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(VENDORS_API_URL)
    public Mono<Void> createVendor(@RequestBody Publisher<Vendor> vendorPublisher) {
        return vendorRepository.saveAll(vendorPublisher).then();
    }
}
