package mx.org.angular.react.practicas.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import mx.org.angular.react.practicas.auth.filter.JWTFilter;
import mx.org.angular.react.practicas.auth.filter.JwtValidationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.core.Ordered;
import java.util.Arrays;

@Configuration
public class SecurityConfig {

	private static final String PUBLICADOR = "PUBLICADOR";	
	private static final String LECTOR = "LECTOR";
	
	 	@Autowired
	    private AuthenticationConfiguration authenticationConfiguration;

	    @Bean
	    AuthenticationManager authenticationManager() throws Exception {
	        return authenticationConfiguration.getAuthenticationManager();
	    }

	    @Bean
	    PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	 @Bean
	    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

	         return http.authorizeHttpRequests(authz -> authz
	                .requestMatchers(HttpMethod.GET, "/api/users/getUsers").hasRole(PUBLICADOR)
	                .requestMatchers(HttpMethod.GET, "/api/users/getUser/{id}").hasRole(PUBLICADOR)
	                .requestMatchers(HttpMethod.GET, "/api/advice/getAdvices").hasAnyRole(LECTOR, PUBLICADOR)	                
	                .requestMatchers(HttpMethod.GET, "/api/advice/getAdvices/{id}").hasAnyRole(LECTOR,PUBLICADOR)
	                .requestMatchers(HttpMethod.POST, "/api/users/create").hasRole(PUBLICADOR)
	                .requestMatchers(HttpMethod.POST, "/api/advice/create").hasRole(PUBLICADOR)
	                .requestMatchers(HttpMethod.PUT, "/api/users/update/{id}").hasRole(PUBLICADOR)
	                .requestMatchers(HttpMethod.PUT, "/api/advice/update/{id}").hasRole(PUBLICADOR)
	                .requestMatchers(HttpMethod.DELETE, "/api/users/delete/{id}").hasRole(PUBLICADOR)
	                .requestMatchers(HttpMethod.DELETE, "/api/advice/delete/{id}").hasRole(PUBLICADOR)
	                .anyRequest().authenticated())
	        		 .cors(cors -> cors.configurationSource(configurationSource()))
	                .addFilter(new JWTFilter(authenticationManager()))
	                .addFilter(new JwtValidationFilter(authenticationManager()))
	        		.csrf(config -> config.disable())
	                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	                .build();
	    }
	 
	   @Bean
	    CorsConfigurationSource configurationSource() {
	        CorsConfiguration config = new CorsConfiguration();
	        config.setAllowedOriginPatterns(Arrays.asList("*"));
	        config.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
	        config.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE"));
	        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
	        config.setAllowCredentials(true);

	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", config);
	        return source;
	    }

	    @Bean
	    FilterRegistrationBean<CorsFilter> corsFilter() {
	        FilterRegistrationBean<CorsFilter> corsBean = new FilterRegistrationBean<CorsFilter>(
	                new CorsFilter(this.configurationSource()));
	        corsBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
	        return corsBean;
	    }
}
