package seg3x02.tempconverterapi.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun userDetailsService(): UserDetailsService {
        val user1 = User.withUsername("user1")
            .password("{noop}pass1")
            .roles("USER")
            .build()

        val user2 = User.withUsername("user2")
            .password("{noop}pass2")
            .roles("USER")
            .build()

        return InMemoryUserDetailsManager(user1, user2)
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeRequests { authz ->
                authz.anyRequest().authenticated() // Require authentication for all requests
            }
            .httpBasic() // Use Basic Authentication
        return http.build()
    }
}
