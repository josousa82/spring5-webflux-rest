package com.training.spring5webfluxrest.domain;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Vendor {

    @Id
    private String id;

    private String firstName;
    private String lastName;

}
