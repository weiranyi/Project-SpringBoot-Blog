package hello.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @ClassName: WebSecurityConfig
 * @Description: 为了安全，默认拦截所有请求
 * @Author: https://github.com/weiranyi
 * @date: 2021/7/25 11:32 下午
 * @Version: V1.0
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 关闭跨站伪造防御，开启时访问可能出现403
        http.csrf().disable();
        http
                .authorizeRequests()
                .antMatchers("/", "/auth/**").permitAll();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        // 鉴权后，会自动设置cookie
        return authenticationManager();
    }


}
