package com.watchdog.dao;

/**
 * Created by jmullen on 10/7/16.
 */
import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceDao implements UserDetailsService {

    protected final Log logger = LogFactory.getLog(getClass());

    ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
    UserDao userDao = ctx.getBean("userDaoImpl", UserDao.class); //first parameter is the id found in the spring.xml file

    @Override
    public UserDetails loadUserByUsername(final String email)
            throws UsernameNotFoundException {

        logger.info("loadUserByUsername email = "+ email);

//        if(!email.equals("pankaj")){
//            throw new UsernameNotFoundException(email + " not found");
//        }

        //creating dummy user details, should do JDBC operations
        return new UserDetails() {

            private static final long serialVersionUID = 2059202961588104658L;

            @Override
            public boolean isEnabled() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public String getUsername() {
                return email;
            }

            @Override
            public String getPassword() {
                return userDao.getPasswordByEmail(email);
            }

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                List<SimpleGrantedAuthority> auths = new java.util.ArrayList<SimpleGrantedAuthority>();
                auths.add(new SimpleGrantedAuthority("admin"));
                return auths;
            }
        };
    }

}