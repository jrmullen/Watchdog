package com.watchdog.business;

import org.hibernate.validator.constraints.Email;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;

/**
 * Created by jmullen on 9/14/16.
 */

public class User {

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private int id;
    private int permissionId;
    private String encodedPassword;

    @Size(min = 1, max = 30)
    private String firstName;

    @Size(min = 1, max = 30)
    private String lastName;

    @Email
    @Size(min = 7, max = 50)
    private String email;

    @NotNull
    @Size(min = 8, max = 30)
    private char[] password;

    //    @NotNull
//    @Size(min = 8, max = 30)
//    private String password;

    @NotNull
    @Size(min = 8, max = 30)
    private char[] passwordConfirm;

//    @NotNull
//    @Size(min = 8, max = 30)
//    private String passwordConfirm;

//    private void checkPassword() {
//        if (this.password == null || this.passwordConfirm == null) {
//            return;
//        } else if (!this.password.equals(passwordConfirm)) {
//            this.passwordConfirm = null;
//        }
//    }

    private void checkPassword() {
        if (this.password == null || this.passwordConfirm == null) {
            return;
        } else if (!(Arrays.equals(password, passwordConfirm))) {
            this.passwordConfirm = null;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public char[] getPassword() {
        return password;
    }

//    public String getPassword() {
//        return password;
//    }

    public void setPassword(char[] password) {
        this.password = password;
    }

//    public void setPassword(String password) {
//        this.password = password;
//    }

    public char[] getPasswordConfirm() {
        return passwordConfirm;
    }

//    public String getPasswordConfirm() {
//        return passwordConfirm;
//    }

    public void setPasswordConfirm(char[] passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
        checkPassword();
    }

//    public void setPasswordConfirm(String passwordConfirm) {
//        this.passwordConfirm = passwordConfirm;
//        checkPassword();
//    }

    public String getEncodedPassword() {
        return encodedPassword;
    }

    public void setEncodedPassword(char[] password) {
        String str = String.valueOf(password);
        this.encodedPassword = passwordEncoder.encode(str);
    }

}
