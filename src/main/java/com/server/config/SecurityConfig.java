package com.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //@Qualifier("userServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /*
    @Autowired
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;
    */

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("user").password("$2a$10$ziJKCTIBnsu6SELx0WsFmuiDkSiqHEz.AoVckNEHIE9Kq6Jdkvjj.").roles("admin")
                .and()
                .withUser("admin").password("$2a$10$ziJKCTIBnsu6SELx0WsFmuiDkSiqHEz.AoVckNEHIE9Kq6Jdkvjj.").roles("admin");
    }

    /*  @Override
      public void configure(WebSecurity web) throws Exception {
          web
                  .ignoring()
                  .antMatchers(  "/css/**","/js/**");
      }
  */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login").anonymous()
                .antMatchers("/admin/**").hasAuthority("admin")
                .antMatchers("/user").hasAnyAuthority("admin", "user")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                // .successHandler(myAuthenticationSuccessHandler)
                .usernameParameter("name")
                .passwordParameter("password")
                .and()
                .logout()
                .and()
                .csrf()
                .disable();

    }
}
