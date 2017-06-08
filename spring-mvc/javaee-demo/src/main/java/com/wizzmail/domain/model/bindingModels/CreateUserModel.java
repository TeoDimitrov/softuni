package com.wizzmail.domain.model.bindingModels;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CreateUserModel {

    @Pattern(regexp = "^[A-Za-z0-9.]{3,20}$", message = "The username should contain letters, digits or . \n The size should be between 3 and 20 symbols")
    private String username;

    @Size(min = 5, max = 30, message = "The first name should be at least 5 symbols")
    private String firstName;

    @Size(min = 5, max = 30, message = "The last name should be at least 5 symbols")
    private String lastName;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*,.])[A-Za-z\\d!@#$%^&*,.]{8,}$", message = "The password should contain a capital letter, a number and a sign [!@#$%^&*,.]")
    private String password;

    private String repeatPassword;

    public CreateUserModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
