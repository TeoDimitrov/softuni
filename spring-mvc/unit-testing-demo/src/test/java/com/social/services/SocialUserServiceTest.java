package com.social.services;

import com.social.entities.Role;
import com.social.entities.SocialUser;
import com.social.repositories.SocialUserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.facebook.api.User;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class SocialUserServiceTest {

    public static final String EXPECTED_EMAIL = "test@abv.bg";

    @Mock
    private User fbUser;

    @MockBean
    private SocialUserRepository socialUserRepository;

    @MockBean
    private RoleService roleService;

    @Autowired
    private SocialUserService socialUserService;

    @Captor
    private ArgumentCaptor<SocialUser> captor;

    @Before
    public void setUp() throws Exception {
        //Arrange
        Role role = new Role();
        role.setAuthority("ROLE_USER");
        when(this.roleService.getDefaultRole()).thenReturn(role);
        when(this.fbUser.getEmail()).thenReturn(EXPECTED_EMAIL);
        when(this.socialUserRepository.findOneByUsername(EXPECTED_EMAIL)).thenReturn(null);
    }

    @Test
    public void registerOrLogin() throws Exception {
        //Act
        this.socialUserService.registerOrLogin(this.fbUser);

        //Assert Database Call
        verify(this.socialUserRepository, times(1)).save(captor.capture());

        //Assert Authentication
        SocialUser loggedInUser = (SocialUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        assertNotNull(loggedInUser);
        assertEquals(EXPECTED_EMAIL, loggedInUser.getUsername());
        assertThat(loggedInUser.getProvider(), is("FACEBOOK"));
    }
}