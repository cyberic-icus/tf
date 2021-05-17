package com.boots.config

import com.boots.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import java.lang.Exception


@Configuration
@EnableWebSecurity
open class WebSecurityConfig : WebSecurityConfigurerAdapter() {
    @Autowired
    var userService: UserService? = null
    @Bean
    open fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Throws(Exception::class)
    override fun configure(httpSecurity: HttpSecurity) {
        httpSecurity
            .csrf()
            .disable()
            .authorizeRequests() //Доступ только для не зарегистрированных пользователей
            .antMatchers("/registration").not()
            .fullyAuthenticated() //Доступ только для пользователей с ролью Администрато
            .antMatchers("files").fullyAuthenticated()
            .antMatchers("entries").fullyAuthenticated()
            .antMatchers("/admin").permitAll() //Доступ разрешен всем пользователей
            //Все остальные страницы требуют аутентификации
            .anyRequest().authenticated()
            .and() //Настройка для входа в систему
            .formLogin()
            .loginPage("/login") //Перенарпавление на главную страницу после успешного входа
            .defaultSuccessUrl("/", true)
            .permitAll()
            .failureUrl("/registration")
            .and()
            .logout()
            .permitAll()
            .logoutSuccessUrl("/")
    }

    @Autowired
    @Throws(Exception::class)
    protected fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder())
    }

    @Bean
    open fun successHandler(): AuthenticationSuccessHandler {
        val handler = SimpleUrlAuthenticationSuccessHandler()
        handler.setUseReferer(true)
        return handler
    }
}