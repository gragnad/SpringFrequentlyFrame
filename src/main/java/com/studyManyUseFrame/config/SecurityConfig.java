package com.studyManyUseFrame.config;

import com.studyManyUseFrame.filter.ExceptionHandlerFilter;
import com.studyManyUseFrame.filter.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
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
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }

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

        //폼 로그인 기능과 베이직 인증 비활성화
        http.formLogin().disable()
                .httpBasic().disable();

        //CORS 사용자 설정
        http.cors().disable();

        //CSRF 방지 지원 기능 비활성화
        http.csrf().disable();

        //윕 경로 보안 설정
        http.authorizeRequests()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/api/**").access("permitAll")
                .antMatchers("/commonApi/**").access("permitAll")
                .antMatchers("/**").access("permitAll")
                .anyRequest().authenticated();

//        모든 요청에 토큰을 검증하는 필터를 추가한다.
//        http.addFilterBefore();
//        http.addFilterBefore(exceptionHandlerFilter, JwtRequestFilter.class);
//        super.configure(http);
    }
}
