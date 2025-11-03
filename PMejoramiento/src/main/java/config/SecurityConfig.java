package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth -> auth
				// rutas públicas
				.requestMatchers("/", "/login", "/css/**", "/js/**", "/images/**", "/api/**").permitAll()
				// 🔒 ruta alterna requiere autenticación
				.anyRequest().authenticated())
				// 🔐 Configuración de login
				.formLogin(login -> login.loginPage("/login") // página de login personalizada
						.defaultSuccessUrl("/", true) // redirige al index al iniciar sesión
						.permitAll())
				// 🚪 Configuración de logout
				.logout(logout -> logout.logoutSuccessUrl("/login?logout") // mensaje de cierre de sesión
						.permitAll());

		return http.build();
	}
}
