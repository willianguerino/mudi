package br.com.alura.mvc.mudi;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

	@Autowired
	private DataSource dataSource;

	/**
	 * Realiza a configuracao da cadeia de seguranca
	 * 
	 * @param http
	 * @return
	 * @throws Exception
	 */
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((authorizeRequests) -> authorizeRequests
				.antMatchers("/home/**")
					.permitAll()
				.anyRequest()
					.authenticated())
				.formLogin(form -> form
						.loginPage("/login")
						.defaultSuccessUrl("/usuario/pedido", true)
						.permitAll()
				)
				.logout(logout -> logout.logoutSuccessUrl("/home"))
				.csrf().disable();
		return http.build();
	}

	/**
	 * Faz a autenticacao
	 * @param dataSource
	 * @return {@link UserDetailsManager}
	 */
	@Bean
	public UserDetailsManager users(DataSource dataSource) {
		
		/*
		 * UserDetails userDetails = User .withUsername("maria")
		 * .password(passwordEncoder().encode("willian")) .roles("ADM") .build();
		 */
		

		JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
		//users.createUser(userDetails).;
		return users;
	}

	//@Bean
	/*
	 * public UserDetailsService userDetailsService() { UserDetails userDetails =
	 * User .withUsername("willian") .password(passwordEncoder().encode("maria"))
	 * .roles("ADM") .build();
	 * 
	 * return new User;
	 */
		
//	}
	/**
	 * 
	 * @return {@link PasswordEncoder}
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
