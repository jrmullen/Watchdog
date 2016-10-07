package com.watchdog.business;

/**
 * Created by jmullen on 10/4/16.
 */

import com.watchdog.dao.UserDetailsServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private DataSource dataSource;
//
//
//
////    @Autowired
////    public void setDataSource(DataSource dataSource) {
////        this.dataSource = dataSource;
////    }
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests().anyRequest().authenticated();
//        http
//                .formLogin().failureUrl("/login?error")
//                .defaultSuccessUrl("/")
////                .loginPage("/login")
//                .permitAll()
//                .and()
//                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
//                .permitAll();
//    }
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .jdbcAuthentication().dataSource(dataSource)
//                .usersByUsernameQuery("SELECT email, password, enabled=true FROM users WHERE email = ?")
//                .authoritiesByUsernameQuery("select email, role from user_roles where email = ?");
//    }

    @Configuration
    @EnableWebSecurity
    public class SecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        public void configure(AuthenticationManagerBuilder auth)
                throws Exception {

            // in-memory authentication
            // auth.inMemoryAuthentication().withUser("pankaj").password("pankaj123").roles("USER");

//         using custom UserDetailsService DAO
            auth.userDetailsService(new UserDetailsServiceDao());

            // using JDBC
//        CallbackGenerator.Context ctx = new InitialContext();
//        DataSource ds = (DataSource) ctx
//                .lookup("java:/comp/env/jdbc/MyLocalDB");
//
//        final String findUserQuery = "select email,password,enabled "
//                + "from Employees " + "where email = ?";
//        final String findRoles = "select email,role " + "from Roles "
//                + "where email = ?";
//
//        auth.jdbcAuthentication().dataSource(ds)
//                .usersByUsernameQuery(findUserQuery)
//                .authoritiesByUsernameQuery(findRoles);
        }


//        @Override
//        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//            auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
//        }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        JdbcUserDetailsManager userDetailsService = new JdbcUserDetailsManager();
//        userDetailsService.setDataSource(dataSource);
//        PasswordEncoder encoder = new BCryptPasswordEncoder();
//
//        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
//        auth.jdbcAuthentication().dataSource(dataSource);
//
//        if(!userDetailsService.userExists("user")) {
//            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//            authorities.add(new SimpleGrantedAuthority("USER"));
//            User userDetails = new User("user", encoder.encode("password"), authorities);
//
//            userDetailsService.createUser(userDetails);
//        }
//    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .jdbcAuthentication()
//                .dataSource(dataSource)
//                .withDefaultSchema()
//                .withUser("user").password("password").roles("USER").and()
//                .withUser("admin").password("password").roles("USER", "ADMIN");
//    }

}
