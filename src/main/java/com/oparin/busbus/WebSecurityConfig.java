//package com.oparin.busbus;
//
//import org.springframework.security.authorization.AuthenticatedReactiveAuthorizationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception{
//        http.authorizeRequests()
//                .antMatchers("/product").hasRole("ADMIN")
//                .antMatchers("/profile").hasRole("ADMIN")
//                .antMatchers("/routes").hasRole("USER")
//
//                .antMatchers("/users").permitAll()
//                .and().formLogin();
//
//
//    }
//
////    @Override
////    protected  void configure(AuthenticationManagerBuilder auth) throws Exception{
////
////    }
//
//}
