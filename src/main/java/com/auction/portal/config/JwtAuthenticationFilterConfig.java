/*
 * package com.stk.mrkt.ojhastkmrktcompanydetailservice.config;
 * 
 * import javax.servlet.Filter;
 * 
 * import org.springframework.boot.web.servlet.FilterRegistrationBean; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.security.authentication.AuthenticationManager; import
 * org.springframework.security.config.annotation.web.configuration.
 * WebSecurityConfigurerAdapter;
 * 
 * import com.stk.mrkt.ojhastkmrktcompanydetailservice.constants.
 * CompanyDetailConstants; import
 * com.stk.mrkt.ojhastkmrktprofileservice.config.JWTAuthorizationFilter;
 * 
 * import lombok.extern.slf4j.Slf4j;
 * 
 * @Slf4j
 * 
 * @Configuration public class JwtAuthenticationFilterConfig extends
 * WebSecurityConfigurerAdapter {
 * 
 * @Bean
 * 
 * @Override public AuthenticationManager authenticationManagerBean() throws
 * Exception { return super.authenticationManagerBean(); }
 * 
 * @Bean public Filter jwtAuthenticationFilter() throws Exception { return new
 * JWTAuthorizationFilter(authenticationManagerBean()); }
 * 
 * @Bean public FilterRegistrationBean<Filter> jwtAuthenticationFilterBean()
 * throws Exception { logger.info("Register Jwt Authentication Filter Bean");
 * FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
 * registration.setFilter(jwtAuthenticationFilter()); registration.setOrder(1);
 * registration.addUrlPatterns(CompanyDetailConstants.API_CONTEXT_ROOT + "/**");
 * registration.setName("jwtAuthenticationFilter"); return registration; } }
 */