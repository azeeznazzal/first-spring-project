package com.abdalazeez.firstspringproject.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class User{
    @NotNull
    private int id;
    @NotBlank
    private String name;
    @NotNull
    private int age;
    @Email
    private String email;

    public User(int i, String hans, int i1, String mail) {
        this.name = hans;
        this.id = i;
        this.age = i1;
        this.email = mail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
