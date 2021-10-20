package com.training.spring5webfluxrest.bootstrap;

import com.training.spring5webfluxrest.repositories.CategoryRepository;
import com.training.spring5webfluxrest.repositories.VendorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.training.spring5webfluxrest.bootstrap.DataInitializerHelper.*;


@Slf4j
@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    private final VendorRepository vendorRepository;


    @Autowired
    public Bootstrap(CategoryRepository categoryRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.vendorRepository = vendorRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        categoryRepository.save(FRUITS);
//        categoryRepository.save(DRIED).block();
//
//        categoryRepository.save(EXOTIC).block();
//
//        categoryRepository.save(NUTS).block();
//        categoryRepository.save(FRESH).block();
//
//
//        log.info("Loaded {} categories", categoryRepository.count());
    }
}
