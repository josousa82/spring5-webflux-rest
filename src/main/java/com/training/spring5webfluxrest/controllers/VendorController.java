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
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(VENDORS_API_URL + "/{id}")
    public Mono<Vendor> updateVendor(@PathVariable String id, @RequestBody Vendor vendor){
        vendor.setId(id);
        return vendorRepository.save(vendor);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(VENDORS_API_URL + "/{id}")
    public Mono<Vendor> pathVendor(@PathVariable String id, @RequestBody Vendor vendor){
        var vendorFound = vendorRepository.findById(id).block();

        //TODO fix method, move logic to service, wrap in try catch, deal with null exceptions
        // logic is updating all fields, correct, patch should update only fields that change
        // write generic method or use library, implement in the service

        if(!vendorFound.getFirstName().equals(vendor.getFirstName())||
                !vendorFound.getLastName().equals(vendor.getLastName())) {

            vendorFound.setFirstName(vendor.getFirstName());
            vendorFound.setLastName(vendor.getLastName());
            return vendorRepository.save(vendorFound);
        }
        return Mono.just(vendorFound);
    }
}
