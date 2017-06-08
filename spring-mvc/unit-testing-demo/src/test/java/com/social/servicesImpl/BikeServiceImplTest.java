package com.social.servicesImpl;

import com.social.entities.Bike;
import com.social.exception.BikeNotFoundException;
import com.social.models.viewModels.BikeViewModel;
import com.social.repositories.BikeRepository;
import com.social.services.BikeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class BikeServiceImplTest {

    public static final long VALID_ID = 1;

    public static final long INVALID_ID = -1;

    public static final String MODEL = "BMX";

    public static final long EXPECTED_GEARS = 0;

    @Autowired
    private ModelMapper modelMapper;

    @MockBean
    private BikeRepository bikeRepository;

    @Autowired
    private BikeService bikeService;

    @Before
    public void setUp() throws Exception {
        Bike bike = new Bike();
        bike.setId(VALID_ID);
        bike.setModel(MODEL);
        when(this.bikeRepository.findOne(VALID_ID)).thenReturn(bike);
    }

    @Test
    public void findByIdGivenValidBike_ShouldReturnValidModel() throws Exception {
        //Act
        BikeViewModel bikeViewModel = this.bikeService.findById(VALID_ID);

        //Assert Id
        assertEquals(VALID_ID, bikeViewModel.getId());
        //Assert Model
        assertEquals(MODEL, bikeViewModel.getModel());
        //Assert Gears
        assertEquals(EXPECTED_GEARS, bikeViewModel.getGears());
        //Assert IsSold
        assertFalse(bikeViewModel.isSold());
    }

    @Test
    public void findByIdGivenValidBike_ShouldCallRepositoryFindById() throws Exception {
        this.bikeService.findById(VALID_ID);

        //Assert Database Call
        verify(this.bikeRepository, times(1)).findOne(VALID_ID);
    }

    @Test(expected = BikeNotFoundException.class)
    public void findByIdGivenInvalidBike_ShouldThrowException() throws Exception {
        //Act
        this.bikeService.findById(INVALID_ID);
    }
}