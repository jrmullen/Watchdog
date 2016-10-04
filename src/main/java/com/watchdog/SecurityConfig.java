package com.watchdog;


import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource ds;

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("root").password("password").roles("USER"); //.and()
                //.withUser("root").password("password").roles("USER", "ADMIN");

        //In progress usersbyUserNameQuery currently throwing errors
       /** auth
                .jdbcAuthentication()
                .dataSource(ds)
                // Will need to modify this or write custom query(?) to select user_email instead of username
                // See here for method documentation: http://docs.spring.io/autorepo/docs/spring-security/3.2.4.RELEASE/apidocs/org/springframework/security/core/userdetails/jdbc/JdbcDaoImpl.html#getUsersByUsernameQuery()
                .usersByUsernameQuery("select user_email,password from user where user_email=?")
                //.usersByUsernameQuery("select username,password, enabled from users where username=?")
                .withUser("root").password("password").roles("USER"); **/

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
