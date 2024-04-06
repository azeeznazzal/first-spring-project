package com.abdalazeez.firstspringproject.model;



import lombok.*;

@Data
@AllArgsConstructor
@Builder
public class Person {
    private int id;
    private String name;
    private String city;
    private String email;
    private String Phone;
}
