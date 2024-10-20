package uk.co.a6software.spring_security_form_login_example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                // explicitly allow the /public page for everyone
                                .requestMatchers("/public").permitAll()
                                // but everything else requires authentication (by default)
                                .anyRequest().authenticated()
                )
                .formLogin(formLogin ->
                        formLogin
                                // so we must explicitly allow access to /login for everyone
                                .loginPage("/login").permitAll()
                                // otherwise this would default redirect to /
                                .defaultSuccessUrl("/private", true)
                )
                .logout(logout ->
                        logout
                                // again, need to explicitly set AND allow access to this url
                                // for everyone
                                .logoutSuccessUrl("/login?logout").permitAll())
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // for config, see https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/in-memory.html
        // for deprecation warning, see https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/core/userdetails/User.html
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        UserDetails user = users.username("billy").password("password").roles("USER").build();
        return new InMemoryUserDetailsManager(user);
    }
}
