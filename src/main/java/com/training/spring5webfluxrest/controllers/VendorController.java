package com.training.spring5webfluxrest.controllers;


import com.training.spring5webfluxrest.domain.Vendor;
import com.training.spring5webfluxrest.repositories.VendorRepository;
import com.training.spring5webfluxrest.services.VendorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
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
}
