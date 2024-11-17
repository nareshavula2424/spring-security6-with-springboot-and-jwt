package com.naresh.spring_security.config;

import com.naresh.spring_security.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class ConfigSecurity {

    @Autowired
    public UserDetailsService userDetailsService;

     @Autowired
     public JwtFilter jwtFilter;

      @Bean
      public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
         // normal way
         http.csrf(cutomizier-> cutomizier.disable());
          http.authorizeHttpRequests(request->request.requestMatchers(new AntPathRequestMatcher("/register")).permitAll());
          http.authorizeHttpRequests(request->request.requestMatchers(new AntPathRequestMatcher("/login")).permitAll());
          http.authorizeHttpRequests(request->request.anyRequest().authenticated());
         //http.formLogin(Customizer.withDefaults());
         http.httpBasic(Customizer.withDefaults());
         http.sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
         http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
          // using lambda expression
//          return  http
//                  .csrf(customizer->customizer.disable())
//                  .authorizeHttpRequests(request->request.requestMatchers("/register","/login").permitAll()
//                          .anyRequest()
//                          .authenticated())
//                  .httpBasic(Customizer.withDefaults())
//                  .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                  .build();
    }
//     @Bean
//    public UserDetailsService userDetailsService(){
//         UserDetails user1 = User
//                 .withDefaultPasswordEncoder()
//                 .username("naresh")
//                 .password("n@123")
//                 .build();
//         UserDetails user2 = User
//                 .withDefaultPasswordEncoder()
//                 .username("lakshman")
//                 .password("l@123")
//                 .build();
//         return new InMemoryUserDetailsManager(user1,user2);
//    }
//     @Bean
//     public PasswordEncoder passwordEncoder(){
//         return new BCryptPasswordEncoder();
//     }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
         return daoAuthenticationProvider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
          return config.getAuthenticationManager();
    }

}
