package com.social.configuration;

import com.social.controllers.UserController;
import com.social.entities.BasicUser;
import com.social.services.BasicUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.authentication.UserServiceBeanDefinitionParser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class SpringSecurityConfigTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BasicUserService basicUserService;

    @Test
    public void testLoginWithValidParameters_ShouldLogin() throws Exception {
        this.mvc
                .perform(formLogin().user("test@test.bg").password("123456"))
                .andExpect(status().is3xxRedirection())
                .andExpect(authenticated().withUsername("test@test.bg"));
    }

    @Test
    public void testLoginWithInvalidParameters_ShouldNotLogin() throws Exception {
        this.mvc
                .perform(formLogin().user("test@test.bg").password("wrongPassowrd"))
                .andExpect(status().is3xxRedirection())
                .andExpect(unauthenticated());
    }

    @Configuration
    static class TestSpringConfig extends WebSecurityConfigurerAdapter{

        @Autowired
        protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
           auth
                   .inMemoryAuthentication()
                   .withUser("test@test.bg")
                   .password("123456")
                   .roles("USER");
        }
    }
}