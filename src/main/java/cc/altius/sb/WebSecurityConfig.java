/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb;

import cc.altius.sb.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author akil
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(this.customUserDetailsService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

//    @Bean
//    public static NoOpPasswordEncoder encoder() {
//        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
//    }
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/assets/**", "/images/**", "/css/**", "/js/**", "/audio/**", "/img/**", "/.well-known/**", "/favicon.ico", "/*").permitAll()
                .antMatchers("/takers/resume.htm**").permitAll()
                .antMatchers("/testSession/take.htm**").fullyAuthenticated()
                .antMatchers("/home/importData.htm**").permitAll()
                .antMatchers("/home/index.htm**").hasAuthority("ROLE_BF_SHOW_HOME")
                .antMatchers("/admin/roleAdd.htm**").hasAuthority("ROLE_BF_ADMIN")
                .antMatchers("/admin/roleList.htm**").hasAuthority("ROLE_BF_ADMIN")
                .antMatchers("/admin/showRoleEdit.htm**").hasAuthority("ROLE_BF_ADMIN")
                .antMatchers("/admin/roleEdit.htm**").hasAuthority("ROLE_BF_ADMIN")
                .antMatchers("/admin/canCreateRoles.htm**").hasAnyAuthority("ROLE_BF_ADMIN")
                .antMatchers("/admin/showCanCreateRoles.htm**").hasAnyAuthority("ROLE_BF_ADMIN")
                .antMatchers("/admin/userAdd.htm**").hasAuthority("ROLE_BF_CREATE_USER")
                .antMatchers("/report/reportNoofTakerAttemptedQuestion.htm**").permitAll()
                .antMatchers("/admin/showUserEdit.htm**").hasAuthority("ROLE_BF_EDIT_USER")
                .antMatchers("/admin/userEdit.htm**").hasAuthority("ROLE_BF_EDIT_USER")
                .antMatchers("/question/editQuestion.htm**").hasAuthority("ROLE_BF_LIST_QUESTIONS")
                .antMatchers("/question/submitQuestion.htm**").hasAuthority("ROLE_BF_EDIT_USER")
                .antMatchers("/admin/userList.htm**").hasAuthority("ROLE_BF_LIST_USER")
                .antMatchers("/question/questionAdd.htm**").hasAuthority("ROLE_BF_CREATE_QUESTIONS")
                .antMatchers("/question/listQuestion.htm**").hasAuthority("ROLE_BF_LIST_QUESTIONS")
                .antMatchers("/admin/failedAttemptsReset.htm**").hasAuthority("ROLE_BF_EDIT_USER")
                .antMatchers("/home/changePassword.htm**").hasAuthority("ROLE_BF_SHOW_HOME")
                .antMatchers("/home/updateExpiredPassword.htm**").hasAuthority("ROLE_BF_PASSWORD_EXPIRED")
                .antMatchers("/admin/resetPassword.htm**").hasAuthority("ROLE_BF_EDIT_USER")
                .antMatchers("/admin/reloadApplicationLayer.htm**").hasAuthority("ROLE_BF_RELOAD_APP_LAYER")
                .antMatchers("/questionOptions/addQuestionOptions.htm**").hasAuthority("ROLE_BF_ADD_QUESTION_OPTIONS")
                .antMatchers("/questionOptions/listQuestionOptions.htm**").hasAuthority("ROLE_BF_LIST_QUESTION_OPTIONS")
                .antMatchers("/questionOptions/editQuestionOptions.htm**").hasAuthority("ROLE_BF_EDIT_QUESTION_OPTIONS")
                .antMatchers("/testResults/view.htm**").hasAuthority("ROLE_BF_TEST_RESULT")
                .antMatchers("/report/exportRaw.htm**").hasAuthority("ROLE_BF_TEST_RESULT")
                .antMatchers("/report/wisdomTakerList.htm**").hasAuthority("ROLE_BF_TEST_RESULT")
                .antMatchers("/testResults/questionsSetCompleted**").hasAuthority("ROLE_BF_QUESTION_SET_COMPLETED")
                .antMatchers("/testResults/coronavirus.htm**").hasAuthority("ROLE_BF_CORONAVIRUS")
                .antMatchers("/testResults/coronavirusInsights.htm**").hasAuthority("ROLE_BF_CORONAVIRUS_INSIGHTS")
                .antMatchers("/testResults/codecore.htm**").hasAuthority("ROLE_BF_CODECORE")
                .antMatchers("/testResults/questionsSetPending.htm**").hasAuthority("ROLE_BF_QUESTION_SET_PENDING")
                .antMatchers("/takers/noOfQuestionAttemptedByTaker.htm**").hasAuthority("ROLE_BF_CREATE_QUESTIONS")
                .antMatchers("/takers/editNoOfQuestionAttemptedByTaker.htm**").hasAuthority("ROLE_BF_CREATE_QUESTIONS")
                .antMatchers("/test/test1.htm**").permitAll()
                .antMatchers("/ajax/testSession/take.htm**").permitAll()
                .antMatchers("/testSession/complete.htm**").permitAll()
                .antMatchers("/pdf/newPdf.htm**").permitAll()
                .antMatchers("/pdf/comprehensiveDossier.htm**").permitAll()
                .antMatchers("/pdf/comprehensiveDossier.htm**").hasAuthority("ROLE_BF_LIST_QUESTIONS")
                .antMatchers("/pdf/adminPrivateDossier.htm**").permitAll()
                .antMatchers("/home/test.htm").permitAll()
                .antMatchers("/takers/securitypassurl.htm**").permitAll()
                .antMatchers("/api/getTaker**").permitAll()
                .antMatchers("/takers/register.htm**").permitAll()
                .antMatchers("/takers/checkEmailId.htm**").permitAll()
                .antMatchers("/testSession/medicalProfessional.htm**").permitAll()
                //                .antMatchers("/home/takerLogin.htm").permitAll()
                .anyRequest().fullyAuthenticated()
                .anyRequest().denyAll()
                .and()
                .exceptionHandling().accessDeniedPage("/errors/accessDenied.htm")
                .and()
                .formLogin()
                .loginPage("/home/login.htm").defaultSuccessUrl("/home/index.htm").permitAll()
                .loginPage("/takers/resume.htm").defaultSuccessUrl("/home/index.htm").permitAll()
                .loginPage("/testSession/take.htm").defaultSuccessUrl("/home/index.htm").permitAll()
                //                .defaultSuccessUrl("/taker/securitypassurl.htm").permitAll()
                .and()
                .logout().permitAll()
                .and().csrf().ignoringAntMatchers("/api/**");

    }
    

}
