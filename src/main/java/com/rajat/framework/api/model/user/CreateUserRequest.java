package com.rajat.framework.api.model.user;

public class CreateUserRequest {

    private final String firstName;
    private final String lastName;
    private final String email;
    private final Integer age;
    private final Boolean isActive;

    public CreateUserRequest(String firstName,
                             String lastName,
                             String email,
                             Integer age,
                             Boolean isActive) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.isActive = isActive;
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
}