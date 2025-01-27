package lk.ijse.gdse68.greenshadowbackend.config;
//08

import lk.ijse.gdse68.greenshadowbackend.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:63342")
@EnableWebSecurity
@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private JwtFilter jwtFilter;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
    //mona req da gnne monad gnne nate
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth.requestMatchers(
                                "/api/v1/auth/authenticate", //url ek hoyagnne methnin
                                "/api/v1/auth/register").permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class).build();
    }


}

//auth.requestMatchers(
//        "/api/v1/auth/authenticate", //url ek hoyagnne methnin
//        "/api/v1/auth/register"
//
//       ).======>>permitAll() ekt gnnawa me wage req nm withark

//header eke token ekk awill ek authenticate krpuwa tamyi authenticate req kiynne
//end poin ekk ayi reg wenne user kenek register krgnn one hinda end point ekk denne //login ekt open krl tiygnne log wenn ona hinda
//user hamontm gnn be sytem ekt gnn api athulen den widiyt