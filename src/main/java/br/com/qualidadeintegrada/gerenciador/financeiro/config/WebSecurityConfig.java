package br.com.qualidadeintegrada.gerenciador.financeiro.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	
	@Bean(name="userDetailsService")	
	public UserDetailsService userDetailsService() {
		
		JdbcDaoImpl jdbcDaoImpl = new JdbcDaoImpl();
		jdbcDaoImpl.setDataSource(dataSource);
		jdbcDaoImpl.setUsersByUsernameQuery("select username,password,enabled from usuario where username=?");
		jdbcDaoImpl.setAuthoritiesByUsernameQuery("select username,role from usuario_roles where username=?");
		
		return jdbcDaoImpl;
		
	}
	
	@Autowired
	UserDetailsService userDetailsService;
	
	
	@Bean(name="passwordEncoder")
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests().antMatchers("/css/unauth/**").permitAll()
				.antMatchers("/", "/home")
				.access("hasRole('ROLE_USER')")
				.anyRequest()
				.authenticated()
				.and()
				.formLogin()
				.loginPage("/login")				
				.permitAll()
				.and()				
				.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login");
	}	
}
