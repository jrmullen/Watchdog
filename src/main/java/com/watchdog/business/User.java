package com.watchdog.business;

import com.watchdog.security.PasswordService;
import org.hibernate.validator.constraints.Email;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.FieldError;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jmullen on 9/14/16.
 */

public class User {

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private int id;
    private int permissionId;

    @Size(min = 1, max = 30)
    private String firstName;

    @Size(min = 1, max = 30)
    private String lastName;

    @Email
    @Size(min = 7, max = 50)
    private String email;

    @Size(min = 8, max = 30)
    private char[] password;


    @Size(min = 8, max = 30)
    private char[] passwordConfirm;

    private String encodedPassword;

    public User() {

    }

    @AssertTrue
    public boolean isSamePassword(char[] password, char[] passwordConfirm) {
        if(!(Arrays.equals(password, passwordConfirm))) {
            return false;
        } else {
            return true;
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

    public void setPassword(char[] password) {
        this.password = password;
    }

    public char[] getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(char[] passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getEncodedPassword() {
        return encodedPassword;
    }

    public void setEncodedPassword(char[] password) {
        String str = String.valueOf(password);
        this.encodedPassword = passwordEncoder.encode(str);
    }

}
