package com.training.spring5webfluxrest.bootstrap;

import com.training.spring5webfluxrest.repositories.CategoryRepository;
import com.training.spring5webfluxrest.repositories.VendorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.List;

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
            categoryRepository.save(FRUITS).block();
            categoryRepository.save(DRIED).block();
            categoryRepository.save(EXOTIC).block();
            categoryRepository.save(NUTS).block();
            categoryRepository.save(FRESH).block();
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
