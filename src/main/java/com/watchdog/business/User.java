package com.watchdog.business;

import com.watchdog.PasswordService;
import org.hibernate.validator.constraints.Email;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;

/**
 * Created by jmullen on 9/14/16.
 */
public class User implements UserDetails {

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

    @Size(min = 8, max = 30)
    private String password;

//    @Size(min = 8, max = 30)
//    private char[] passwordConfirm;

    @Size(min = 8, max = 30)
    private String passwordConfirm;

    public User(String user, String password, List<GrantedAuthority> authorities) {

    }

    public User() {
        
    }

    @AssertTrue()
    public boolean isDifferentPass() {
        return !passwordConfirm.equals(password) ? false : true;
    }

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

//    public void setPassword(char[] password) {
//        this.password = password;
//    }

//    public void setPassword(String password) throws Exception {
//        this.password = ps.encrypt(password);
//    }

    public void setPassword(String password) {
        this.password = password;
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
    }
}
