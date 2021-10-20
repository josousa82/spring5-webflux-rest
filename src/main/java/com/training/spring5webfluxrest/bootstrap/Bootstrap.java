package com.training.spring5webfluxrest.bootstrap;

import com.training.spring5webfluxrest.repositories.CategoryRepository;
import com.training.spring5webfluxrest.repositories.VendorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static com.training.spring5webfluxrest.bootstrap.DataInitializerHelper.*;


@Slf4j
@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if(categoryRepository.count().block() == 0){
            categoryRepository.save(CATEGORY_1).block();
            categoryRepository.save(CATEGORY_2).block();
            categoryRepository.save(CATEGORY_3).block();
            categoryRepository.save(CATEGORY_4).block();
            categoryRepository.save(CATEGORY_5).block();
            log.info("{} categories loaded.", categoryRepository.count().block());
        }



        if(vendorRepository.count().block() == 0) {
            vendorRepository.save(VENDOR_1).block();
            vendorRepository.save(VENDOR_2).block();
            vendorRepository.save(VENDOR_3).block();
            vendorRepository.save(VENDOR_4).block();
            log.info("{} vendors loaded.", vendorRepository.count().block());
        }


    }
}
