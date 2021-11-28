package ru.otus.mainserver.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final DeniedHandler deniedHandler;

    @Override
    public void configure( WebSecurity web ) {
        web.ignoring().antMatchers( "/" );
    }

    @Override
    public void configure( HttpSecurity http ) throws Exception {
        http.csrf().disable().httpBasic().and()
                    .authorizeRequests()
                    .antMatchers("/user/**")
                    .permitAll()
                .and()
                    .authorizeRequests()
                    .antMatchers( "/book","/genre", "/author" )
                    .hasAnyAuthority("ROLE_USER", "ROLE_ADMIN", "ROLE_LIBRARIAN")
                .and()
                    .authorizeRequests()
                    .antMatchers("/book/create", "/book/update", "/genre/create", "/genre/update", "/author/create", "/author/update")
                    .hasAnyAuthority("ROLE_LIBRARIAN")
                .and()
                    .authorizeRequests()
                    .antMatchers("/**")
                    .denyAll()
                .and()
                    // Включает Form-based аутентификацию
                    .formLogin()
                .and()
                    .logout()
                .and()
                    .exceptionHandling().accessDeniedHandler(deniedHandler);
    }

    @SuppressWarnings("deprecation")
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
