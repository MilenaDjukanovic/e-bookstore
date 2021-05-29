package com.mixienixie.ebookstore.security.configurations;

import com.mixienixie.ebookstore.repo.authority.UserRepository;
import com.mixienixie.ebookstore.security.filters.JwtTokenFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.http.HttpServletResponse;

/**
 * Security configuration for the web application
 *
 * @author ndjordjieski
 */
@EnableWebSecurity
@AllArgsConstructor
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class EBookstoreSecurityConfig extends WebSecurityConfigurerAdapter{

    /** User Repository */
    private final UserRepository userRepository;
    /** JWT Token Util */
    private final JwtTokenFilter jwtTokenFilter;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
        authenticationManagerBuilder.userDetailsService(username -> this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found")));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        // Disable CORS and disable CSRF
        httpSecurity = httpSecurity.cors().disable();
        httpSecurity = httpSecurity.csrf().disable();

        // Set session management to stateless
        httpSecurity = httpSecurity
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();

        // Set unauthorized requests exception handler
        httpSecurity = httpSecurity
                .exceptionHandling()
                .authenticationEntryPoint((request, response, exception) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED, exception.getMessage()))
                .and();

        httpSecurity.authorizeRequests()
                // Public endpoints
                .antMatchers("/api/public/**").permitAll()

                // Internal endpoints
                .antMatchers(HttpMethod.GET, "/api/author/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/author/search").permitAll()
                .antMatchers(HttpMethod.GET, "/api/book/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/book/search").permitAll()

                .anyRequest().authenticated();

        httpSecurity.addFilterBefore(
                jwtTokenFilter,
                UsernamePasswordAuthenticationFilter.class
        );
    }

//    @Bean
//    public CorsFilter corsFilter(){
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration configuration = new CorsConfiguration();
//
//        configuration.setAllowCredentials(true);
//        configuration.addAllowedOrigin("*");
//        configuration.addAllowedHeader("*");
//        configuration.addAllowedMethod("*");
//
//        source.registerCorsConfiguration("/**", configuration);
//        return new CorsFilter(source);
//    }

    /**
     * Exposes the password encoder as a bean
     * @return passwordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * Exposes the authentication manager as a bean
     *
     * @return authenticationManagerBean
     * @throws Exception exception
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
