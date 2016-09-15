package com.watchdog.business;

/**
 * Created by jmullen on 9/14/16.
 */
public class User {

    private int id;
    private int permissionId;
    private String firstName;
    private String lastName;
    private String email;
    private char[] password;
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
