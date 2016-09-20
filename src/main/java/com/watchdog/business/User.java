package com.watchdog.business;

import javax.validation.constraints.Size;

/**
 * Created by jmullen on 9/14/16.
 */
public class User {

    private int id;
    private int permissionId;

    @Size(min = 1, max = 64)
    private String firstName;

    @Size(min = 1, max = 64)
    private String lastName;

    @Size(min = 7, max = 254)
    private String email;

//    @Size(min = 8, max = 50)
//    private char[] password;

    @Size(min = 8, max = 50)
    private String password;

    @Size(min = 8, max = 50)
    private char[] passwordConfirm;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public char[] getPassword() {
//        return password;
//    }

    public String getPassword() {
        return password;
    }

//    public void setPassword(char[] password) {
//        this.password = password;
//    }

    public void setPassword(String password) {
        this.password = password;
    }

    public char[] getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(char[] passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
}
