package com.watchdog.config;

/**
 * Created by jmullen on 10/4/16.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource; //RED DATASOURCE IS NOT WRONG

    //This method defines which pages are available/restricted
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests().anyRequest().authenticated();
        http
                .authorizeRequests()
                .antMatchers("/user_home").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers("/device_manager").access("hasAnyRole('ROLE_ADMIN')")
                .and()
                .formLogin().failureUrl("/login?error")
                .defaultSuccessUrl("/user_home")
                .loginPage("/login")
                .permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/")
                .permitAll()
                .and().csrf().disable();
    }

    //Authentication queries to pull user info from database
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication().dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("SELECT USER_EMAIL, USER_PASSWORD, enabled FROM user WHERE USER_EMAIL = ?")
                .authoritiesByUsernameQuery("select user.USER_EMAIL, permissions.ROLE from user, permissions "
                                                            + "where user.USER_EMAIL = ?");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
