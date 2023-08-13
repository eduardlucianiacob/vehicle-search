package com.learn2code.vehicle.api.search.config;

import com.learn2code.vehicle.api.search.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//@Configuration
@EnableWebSecurity
//    public class AppConfig extends WebSecurityConfigurerAdapter {
    public class AppConfig {

    @Autowired
    public MyUserDetailsService myUserDetailsService;
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(myUserDetailsService);
//    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//        http.authorizeRequests()
//                        .antMatchers("/api/v1/manufacturers").hasAnyRole("USER", "ADMIN")
////                .antMatchers("/api/v1/vehicle-market-price").hasRole("ADMIN")
//                .anyRequest().authenticated().and().httpBasic();
//    }


    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();
        httpSecurity.authorizeRequests()
                .antMatchers("/api/v1/manufacturers").hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/v1/vehicle-market-price").hasRole("ADMIN")
                .anyRequest().authenticated().and().httpBasic()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}
