//package dmitriy.tsoy.russia.vitaSoftTest.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
////    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Override
//    protected
//    void configure(HttpSecurity http) throws Exception {
//        http.
//                authorizeRequests()
//                .antMatchers("/user/**").hasAuthority("ADMIN")
//                .antMatchers("/appId").hasAuthority("USER")
//                .antMatchers("/**").hasAnyAuthority("USER", "ADMIN", "OPERATOR")
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login").defaultSuccessUrl("/application").permitAll()
//                .and()
//                .logout().logoutUrl("/logout").logoutSuccessUrl("/login").permitAll()
//                .and()
//                .csrf().disable();
//    }
//}
