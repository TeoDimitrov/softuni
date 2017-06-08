package com.social.controllers;

import com.social.exception.BikeNotFoundException;
import com.social.models.viewModels.BikeViewModel;
import com.social.services.BikeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BikeController.class)
//@ActiveProfiles("test")
@EnableSpringDataWebSupport
public class BikeControllerTest {

    public static final long INVALID_BIKE_ID = -1;

    public static final long BIKE_ID = 1;

    public static final String MODEL = "BMX";

    public static final int EXPECTED_PAGE_SIZE = 2;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BikeService bikeService;

    @Mock
    private Pageable pageable;

    @Captor
    private ArgumentCaptor<Pageable> captor;

    @Before
    public void setUp() throws Exception {
        //Arrange
        BikeViewModel bikeViewModel = new BikeViewModel();
        bikeViewModel.setId(BIKE_ID);
        bikeViewModel.setModel(MODEL);
        Page<BikeViewModel> bikePage = new PageImpl(Arrays.asList(bikeViewModel, bikeViewModel));
        when(this.bikeService.findById(BIKE_ID)).thenReturn(bikeViewModel);
        when(this.bikeService.findById(INVALID_BIKE_ID)).thenThrow(new BikeNotFoundException());
        when(this.bikeService.findAll(this.pageable)).thenReturn(bikePage);
    }

    @Test
    @WithMockUser
    public void showBikeGivenValidBikeModel_ShouldReturnBikePage() throws Exception {
        //Act
        this.mvc
                .perform(get("/bikes/show/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("bike-show"))
                .andExpect(model().attribute("bike", hasProperty("id", is(BIKE_ID))))
                .andExpect(model().attribute("bike", hasProperty("model", is(MODEL))))
                .andExpect(model().attribute("bike", hasProperty("gears", is(0))));

        verify(this.bikeService, times(1)).findById(BIKE_ID);
        verifyNoMoreInteractions(this.bikeService);
    }

    @Test
    @WithMockUser
    public void showBikeGivenInvalidId_ShouldRedirectExceptionView() throws Exception {
        this
                .mvc
                .perform(get("/bikes/show/-1"))
                .andExpect(status().is4xxClientError())
                .andExpect(view().name("exceptions/bike-not-found"));
    }

    @Test
    @WithMockUser
    public void getBikes_ShouldReturnValidPage() throws Exception {
        this.mvc
                .perform(get("/bikes"))
                .andExpect(status().isOk())
                .andExpect(view().name("bikes"));
                //.andExpect(model().attribute("total", is(EXPECTED_PAGE_SIZE)));

        verify(this.bikeService, times(1)).findAll(captor.capture());
    }


    @Test
    @WithMockUser(username = "test@test.bg", password = "123456", roles = "USER")
    public void getBikesWithUser_ShouldAccessIt() throws Exception {
        this.mvc
                .perform(get("/bikes"))
                .andExpect(status().isOk())
                .andExpect(view().name("bikes"))
                .andExpect(authenticated().withUsername("test@test.bg").withRoles("USER"));
    }

    @Test
    public void getBikesNoUser_ShouldNotAccessBikePage() throws Exception {
        this.mvc
                .perform(get("/bikes"))
                .andExpect(status().is4xxClientError())
                .andExpect(unauthenticated());

    }

    @Test
    @WithMockUser
    public void showRestBike() throws Exception {
        this.mvc
                .perform(get("/bikes/rest/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is((int)BIKE_ID)))
                .andExpect(jsonPath("$.model", is(MODEL)));
    }
}