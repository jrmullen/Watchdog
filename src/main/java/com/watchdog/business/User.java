package com.watchdog.business;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by jmullen on 9/14/16.
 */
public class User {

    private int id;
    private int permissionId;

    @NotNull
    @Size(min = 1, max = 64)
    private String firstName;

    @NotNull
    @Size(min = 1, max = 64)
    private String lastName;

    @NotNull
    @Size(min = 7, max = 254)
    private String email;

    @NotNull
    @Size(min = 8, max = 50)
    private char[] password;

    @NotNull
    @Size(min = 8, max = 50)
    private char[] passwordConfirm;

    public void setId(int id) {
        this.id = id;
    }
    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public void setPasswordConfirm(char[] passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public int getId() {
        return id;
    }

    public int getPermissionId() {
        return permissionId;
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

    public char[] getPassword() {
        return password;
    }

    public char[] getPasswordConfirm() {
        return passwordConfirm;
    }
}
