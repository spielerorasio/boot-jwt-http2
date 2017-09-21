package com.orasio.example.boot.jwt;

import com.orasio.example.boot.jwt.filter.JWTAuthenticationFilter;
import com.orasio.example.boot.jwt.filter.JWTLoginFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Bean
    JWTLoginFilter jwtLoginFilter() throws Exception {
        return new JWTLoginFilter("/login", authenticationManager() );
    }

    @Bean
    JWTAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        return new JWTAuthenticationFilter();
    }


    @Override
    //also to connect openid take a look at http://www.baeldung.com/spring-security-openid-connect
    //or https://github.com/spring-projects/spring-security/tree/master/samples/javaconfig/openid
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/public/").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .anyRequest().authenticated()
                .and()
                // We filter the api/login requests
                .addFilterBefore(jwtLoginFilter(), UsernamePasswordAuthenticationFilter.class)
                // And filter other requests to check the presence of JWT in header
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Create a default account
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("password")
                .roles("ADMIN");
    }

//    @Bean
//    UndertowEmbeddedServletContainerFactory embeddedServletContainerFactory() {
//        UndertowEmbeddedServletContainerFactory factory = new UndertowEmbeddedServletContainerFactory();
//        factory.addBuilderCustomizers(
//                builder -> builder.setServerOption(UndertowOptions.ENABLE_HTTP2, true));
//        return factory;
//    }
}