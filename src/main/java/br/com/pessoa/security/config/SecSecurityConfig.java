package br.com.pessoa.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecSecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String ADMIN = "ADMIN";

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("guest1").password("{noop}guest1").roles(ADMIN)
                .and()
                .withUser("guest2").password("{noop}guest2").roles(ADMIN);
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("*/pessoas/**").hasAnyRole()
                .and()
                .logout().invalidateHttpSession(Boolean.TRUE)
                .clearAuthentication(Boolean.TRUE)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/pessoas")
                .and()
                .csrf().disable()
                .formLogin().disable();

    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/source", "/usuarios");
    }
}
