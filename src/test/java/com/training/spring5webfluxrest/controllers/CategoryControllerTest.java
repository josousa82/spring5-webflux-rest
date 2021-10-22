package com.training.spring5webfluxrest.controllers;

import com.training.spring5webfluxrest.domain.Category;
import com.training.spring5webfluxrest.repositories.CategoryRepository;
import com.training.spring5webfluxrest.services.CategoryService;
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
import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = {CategoryController.class})
class CategoryControllerTest {


    @MockBean
    CategoryRepository categoryRepository;

    @MockBean
    CategoryService categoryService;

    @Autowired
    CategoryController categoryController;

    WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient.bindToController(categoryController).build();
    }

    @Test
    void getCategories() {

        BDDMockito.given(categoryRepository.findAll())
                .willReturn(Flux.just(CATEGORY_1, CATEGORY_2));

        webTestClient.get().uri(CATEGORY_API_URL)
                .exchange()
                .expectBodyList(Category.class)
                .hasSize(2);
    }

    @Test
    void getCategoryById() {

        CATEGORY_1.setId(ID_1);
        BDDMockito.given(categoryRepository.findById(ID_1))
                .willReturn(Mono.just(CATEGORY_1));

        var result = webTestClient.get()
                .uri(CATEGORY_API_URL.concat(ID_1))
                .exchange()
                .expectBody(Category.class)
                .returnResult()
                .getResponseBody();
        assert result != null;
        assertEquals(ID_1, result.getId());
        assertEquals( CATEGORY_STR_1,result.getDescription());
    }

    @Test
    void createCategory() {
        BDDMockito.given(categoryRepository.saveAll(Mockito.any(Publisher.class)))
                .willReturn(Flux.just(CATEGORY_1));

        Mono<Category> categoryMono =Mono.just(CATEGORY_1);

        webTestClient.post()
                .uri(CATEGORY_API_URL)
                .body(categoryMono, Category.class)
                .exchange()
                .expectStatus()
                .isCreated();
    }
}