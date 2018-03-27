package com.emmanuelirem.studentassistant.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    final
    private DataSource dataSource;

    @Autowired
    public SecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select registration_number,password, enabled from users where registration_number=?")
                .authoritiesByUsernameQuery("select registration_number, role from user_roles where registration_number=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/webjars/**", "/css/**","/js/**","/h2-console/**","/","/register/**","/login**")
                .permitAll()
                .antMatchers("/register/**").anonymous()
                .anyRequest().authenticated()
                .antMatchers("/lecturer/**")
                .hasAuthority("LECTURER")
                .antMatchers("/student/**")
                .hasAuthority("STUDENT")
                .antMatchers("/Api/**")
                .authenticated()
                .and()
                .formLogin()
                .successHandler( new MySimpleUrlAuthenticationSuccessHandler())
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.exceptionHandling().accessDeniedPage("/403");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}