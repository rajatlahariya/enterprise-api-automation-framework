package com.rajat.framework.api.model.user;

import java.time.LocalDateTime;

public final class User {

    private final Integer id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final Integer age;
    private final Boolean isActive;
    private final LocalDateTime createdAt;

    public User(Integer id,
                String firstName,
                String lastName,
                String email,
                Integer age,
                Boolean isActive,
                LocalDateTime createdAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.isActive = isActive;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Integer getAge() {
        return age;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}