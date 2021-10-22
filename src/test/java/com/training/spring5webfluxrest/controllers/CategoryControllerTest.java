package com.training.spring5webfluxrest.controllers;

import com.training.spring5webfluxrest.domain.Category;
import com.training.spring5webfluxrest.repositories.CategoryRepository;
import com.training.spring5webfluxrest.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
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
import static org.mockito.ArgumentMatchers.any;

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
    void getCategoriesTest() {

        BDDMockito.given(categoryRepository.findAll())
                .willReturn(Flux.just(CATEGORY_1, CATEGORY_2));

        webTestClient.get().uri(CATEGORY_API_URL)
                .exchange()
                .expectBodyList(Category.class)
                .hasSize(2);
    }

    @Test
    void getCategoryByIdTest() {

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
        assertEquals(CATEGORY_STR_1, result.getDescription());
    }

    @Test
    void createCategoryTest() {
        BDDMockito.given(categoryRepository.saveAll(any(Publisher.class)))
                .willReturn(Flux.just(CATEGORY_1));

        Mono<Category> categoryMono = Mono.just(CATEGORY_1);

        webTestClient.post()
                .uri(CATEGORY_API_URL)
                .body(categoryMono, Category.class)
                .exchange()
                .expectStatus()
                .isCreated();
    }

    @Test
    void updateCategoryTest() {

        CATEGORY_2.setId(ID_1);
        CATEGORY_2.setDescription("Updated category 2");

        BDDMockito.given(categoryRepository.save(any(Category.class)))
                .willReturn(Mono.just(CATEGORY_2));

        var catToUpdateMono = Mono.just(Category
                .builder()
                .description("Updated category 2")
                .build());

        webTestClient.put()
                .uri(CATEGORY_API_URL + ID_1)
                .body(catToUpdateMono, Category.class)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Category.class)
                .value(category -> assertEquals("Updated category 2", category.getDescription()));
    }

    @Test
    void pathCategoryTest() {

        CATEGORY_3.setId(ID_1);
        CATEGORY_4.setDescription("Updated category 4");

        BDDMockito.given(categoryRepository.findById(any(String.class)))
                .willReturn(Mono.just(CATEGORY_4));

        BDDMockito.given(categoryRepository.save(any(Category.class)))
                .willReturn(Mono.just(CATEGORY_4));

        var catToUpdateMono = Mono.just(Category
                .builder()
                .description("Updated category 4")
                .build());

        webTestClient.patch()
                .uri(CATEGORY_API_URL + ID_1)
                .body(catToUpdateMono, Category.class)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Category.class)
                .value(category -> assertEquals("Updated category 4", category.getDescription()));
    }
}