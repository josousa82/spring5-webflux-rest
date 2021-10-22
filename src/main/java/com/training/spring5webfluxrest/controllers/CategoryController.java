package com.training.spring5webfluxrest.controllers;


import com.training.spring5webfluxrest.domain.Category;
import com.training.spring5webfluxrest.repositories.CategoryRepository;
import com.training.spring5webfluxrest.services.CategoryService;
import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class CategoryController {

    public static final String CATEGORIES_API_URL = "/api/v1/categories";
    private final CategoryRepository categoryRepository;
        private final CategoryService categoryService;


    public CategoryController(CategoryRepository categoryRepository, CategoryService categoryService) {
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
    }


    @GetMapping(CATEGORIES_API_URL)
    public Flux<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @GetMapping(CATEGORIES_API_URL + "/{id}")
    public Mono<Category> getCategoryById(@PathVariable String id) {
        return categoryRepository.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(CATEGORIES_API_URL)
    public Mono<Void> createCategory(@RequestBody Publisher<Category> categoryStream){
        return categoryRepository.saveAll(categoryStream).then();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(CATEGORIES_API_URL + "/{id}")
    public Mono<Category> updateCategory(@PathVariable String id, @RequestBody Category category){
        category.setId(id);
        return categoryRepository.save(category);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(CATEGORIES_API_URL + "/{id}")
    public Mono<Category> pathCategory(@PathVariable String id, @RequestBody Category category){
        var categoryFound = categoryRepository.findById(id).block();

        //TODO fix method, move logic to service, wrap in try catch, deal with null exceptions

        if (categoryFound != null) {
            if(!categoryFound.getDescription().equals(category.getDescription())) {
                categoryFound.setDescription(category.getDescription());
                return categoryRepository.save(categoryFound);
            }

        }

        return Mono.just(categoryFound);
    }
}
