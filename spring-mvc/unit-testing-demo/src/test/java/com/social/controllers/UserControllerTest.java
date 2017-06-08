package com.social.controllers;

import com.social.models.bindingModels.RegistrationModel;
import com.social.services.BasicUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@ActiveProfiles("test")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BasicUserService userService;

    @Test
    public void registerUser() throws Exception {
        mockMvc
                .perform(post("/register")
                        .param("username", "test@abv.bg")
                        .param("password", "123456")
                        .param("confirmPassword", "123456")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"))
                .andExpect(redirectedUrl("/"))
                .andExpect(model().hasNoErrors());

        ArgumentCaptor<RegistrationModel> captor = ArgumentCaptor.forClass(RegistrationModel.class);
        verify(userService).register(captor.capture());
        RegistrationModel bu = captor.getValue();
        assertEquals("test@abv.bg", bu.getUsername());
    }

    @Test
    public void registerGivenInvalidPasswords_ShouldNotRegister() throws Exception {
        this.mockMvc
                .perform(post("/register")
                        .param("password", "123456")
                        .param("confirmPassword", "123")
                )
                .andExpect(status().isOk())
                .andExpect(view().name("register"));

        verifyZeroInteractions(this.userService);
    }

    @Test
    public void registerGivenInvalidPassword_ShouldNotRegister() throws Exception {
        this.mockMvc
                .perform(post("/register")
                        .param("password", "123")
                )
                .andExpect(status().isOk())
                .andExpect(view().name("register"))
                .andExpect(model().attributeHasFieldErrors("registrationModel", "password"))
                .andExpect(model().hasErrors());

        verifyZeroInteractions(this.userService);
    }
}