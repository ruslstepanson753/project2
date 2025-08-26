package ru.alishev.springcourse.models;


import javax.validation.constraints.*;

public class Person {

    private int personId;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String fullName;

    @Min(value = 0, message = "Age should be greater than 0")
    private int yearBorn;

    public Person() {
    }

    public Person(String fullName, int yearBorn) {
        this.fullName = fullName;
        this.yearBorn = yearBorn;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public @NotEmpty(message = "Name should not be empty") @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters") String getFullName() {
        return fullName;
    }

    public void setFullName(@NotEmpty(message = "Name should not be empty") @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters") String fullName) {
        this.fullName = fullName;
    }

    @Min(value = 0, message = "Age should be greater than 0")
    public int getYearBorn() {
        return yearBorn;
    }

    public void setYearBorn(@Min(value = 0, message = "Age should be greater than 0") int yearBorn) {
        this.yearBorn = yearBorn;
    }
}
