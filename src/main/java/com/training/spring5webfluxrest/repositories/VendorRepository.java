package com.training.spring5webfluxrest.repositories;

import com.training.spring5webfluxrest.domain.Vendor;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VendorRepository extends ReactiveMongoRepository<Vendor, String> {
}
