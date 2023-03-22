package com.example.demo.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.dao.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
                .antMatchers("/login", "/register").permitAll()
                .antMatchers("/home").hasAnyRole("MANAGER", "DY_MANAGER", "WORKER")
                .antMatchers("/delete/**").hasRole("MANAGER")
                .antMatchers("/edit/**").hasAnyAuthority("MANAGER", "DY_MANAGER")
                //.antMatchers("/openPage").permitAll()
                //.antMatchers("/createUserBeforeLogin").permitAll()
                //.antMatchers("/createRecordBeforeLogin").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                //.loginPage("/login")
                .successForwardUrl("/home")
                .defaultSuccessUrl("/home")
                //.usernameParameter("username")
                //.passwordParameter("password")
                .permitAll()
                .and()
            .logout()
                .permitAll();
    }
}
