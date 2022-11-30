package com.gs.studyManyUseFrame.config;

import com.gs.studyManyUseFrame.filter.ExceptionHandlerFilter;
import com.gs.studyManyUseFrame.filter.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    private ExceptionHandlerFilter exceptionHandlerFilter;

//    @Autowired
//    public void configGlobal(AuthenticationManagerBuilder auth) throws Exception{
//        // 일치하는 자격증명을 위해 사용자를 로드할 위치를 알수 있도록
//        // AuthenticationManager를 구성한다.
//        // BCryptPasswordEncoder를 이용
//        auth.userDetailsService(jwtUserDetailsService)
//                .passwordEncoder(passwordEncoder());
//    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().disable()
                .csrf().disable()
                .authorizeRequests().antMatchers(

                ).permitAll()
                //다른 모든 요청은 인증을 한다
                .anyRequest().authenticated().and()
                //상태없는 세션을 이용하며, 세션에 사용자의 상태를 저장하지 않는다
//                .exceptionHandling().authenticationEntryPoint();
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().formLogin().disable()
                .headers().frameOptions().disable();

//        모든 요청에 토큰을 검증하는 필터를 추가한다.
//        http.addFilterBefore();
        http.addFilterBefore(exceptionHandlerFilter, JwtRequestFilter.class);
//        super.configure(http);
    }
}