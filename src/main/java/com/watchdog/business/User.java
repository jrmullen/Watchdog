package com.watchdog.business;

import com.watchdog.PasswordService;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by jmullen on 9/14/16.
 */
public class User {

    PasswordService ps = new PasswordService();
    private int id;
    private int permissionId;

    @Size(min = 1, max = 30)
    private String firstName;

    @Size(min = 1, max = 30)
    private String lastName;

    @Email
    @Size(min = 7, max = 50)
    private String email;

//    @Size(min = 8, max = 30)
//    private char[] password;

    @Size(min = 8, max = 50)
    private String password;

//    @Size(min = 8, max = 30)
//    private char[] passwordConfirm;

//    @AssertTrue()
//    public boolean isDifferentPass() {
//        return !passwordConfirm.equals(password) ? false : true;
//    }

    @NotNull //redundant?
    @Size(min = 8, max = 50)
    private String passwordConfirm;

    private void checkPassword() {
        if (this.password == null || this.passwordConfirm == null) {
            return;
        } else if (!this.password.equals(passwordConfirm)) {
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

//    public char[] getPassword() {
//        return password;
//    }

    public String getPassword() {
        return password;
    }

//    public void setPassword(char[] password) {
//        this.password = password;
//    }

    public void setPassword(String password) throws Exception {
        this.password = ps.encrypt(password);
        checkPassword();
    }

    //    public char[] getPasswordConfirm() {
//        return passwordConfirm;
//    }
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    //    public void setPasswordConfirm(char[] passwordConfirm) {
//        this.passwordConfirm = passwordConfirm;
//    }
    public void setPasswordConfirm(String passwordConfirm) throws Exception {
        this.passwordConfirm = ps.encrypt(passwordConfirm);
        checkPassword();
    }
}
