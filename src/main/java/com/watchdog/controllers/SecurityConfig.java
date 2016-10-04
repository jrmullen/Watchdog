package com.watchdog.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("root").password("password").roles("USER"); //.and()
                //.withUser("root").password("password").roles("USER", "ADMIN");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                    .antMatchers("/webjars/bootstrap/3.3.1/**").permitAll()
                    .antMatchers("/js/**").permitAll()
                    .antMatchers("/css/**").permitAll()
                    .antMatchers("/images/**").permitAll()
                    .antMatchers("/login*").anonymous()
                    .antMatchers("/register*").anonymous()
                    .antMatchers("/fragments/**").permitAll()
                    .antMatchers("/").permitAll()
                    .anyRequest().authenticated()
                    .and()

                .formLogin()
                    .loginPage("/login.html")
                    .defaultSuccessUrl("/user_home.html")
                    .failureUrl("/login.html?error=true")
                    .and()
                .logout()
                    .logoutSuccessUrl("/");
    }
}
