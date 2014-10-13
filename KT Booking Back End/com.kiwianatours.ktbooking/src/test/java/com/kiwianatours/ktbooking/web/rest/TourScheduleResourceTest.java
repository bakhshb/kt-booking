package com.kiwianatours.ktbooking.web.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.inject.Inject;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.kiwianatours.ktbooking.Application;
import com.kiwianatours.ktbooking.repository.TourBookingRepository;
import com.kiwianatours.ktbooking.repository.TourRepository;
import com.kiwianatours.ktbooking.repository.TourScheduleRepository;


/**
 * Test class for the TourinfoResource REST controller.
 *
 * @see TourScheduleResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class })
@ActiveProfiles("dev")
public class TourScheduleResourceTest {
    
    private static final Long DEFAULT_ID = new Long(3L);
       
    private static final LocalDate DEFAULT_DEPARTURE_DATE = new LocalDate(2014,04,04);

    private static final LocalDate DEFAULT_RETURN_DATE = new LocalDate(2014,05,05);
    
    private static final double DEFAULT_PRICE = 200.0;    
    
    private static final Long TOUR_DEFAULT_ID = new Long(2L);

    @Inject
    private TourScheduleRepository tourScheduleRepository;
    
    @Inject
	private TourBookingRepository tourBookingRepository;
    
    @Inject
    private TourRepository tourRepository;

    private MockMvc restTourScheduleMockMvc;
    
    private MockMvc restTourMockMvc;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TourScheduleResource tourScheduleResource = new TourScheduleResource();
        ReflectionTestUtils.setField(tourScheduleResource, "tourScheduleRepository", tourScheduleRepository);
        ReflectionTestUtils.setField(tourScheduleResource, "tourBookingRepository", tourBookingRepository);
        this.restTourScheduleMockMvc = MockMvcBuilders.standaloneSetup(tourScheduleResource).build();     
        
        TourResource tourResource = new TourResource();
        ReflectionTestUtils.setField(tourResource, "tourRepository", tourRepository);
        ReflectionTestUtils.setField(tourResource, "tourScheduleRepository", tourScheduleRepository);
        this.restTourMockMvc = MockMvcBuilders.standaloneSetup(tourResource).build();
    }

    @Test
    public void testCRUDTourSchedule() throws Exception {

        // Read TourSchedule
        restTourScheduleMockMvc.perform(get("/app/rest/tourschedules/{id}", DEFAULT_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(DEFAULT_ID.intValue()))
                .andExpect(jsonPath("$.departureDate").value(DEFAULT_DEPARTURE_DATE.toString()))
                .andExpect(jsonPath("$.returnDate").value(DEFAULT_RETURN_DATE.toString()))
                .andExpect(jsonPath("$.price").value(DEFAULT_PRICE));
                

        // Delete TourSchedule
        restTourScheduleMockMvc.perform(delete("/app/rest/tourschedules/{id}", DEFAULT_ID)
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Read nonexisting TourSchedule
        restTourScheduleMockMvc.perform(get("/app/rest/tourschedules/{id}", DEFAULT_ID)
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound());
        
     // Delete Tour
        restTourMockMvc.perform(delete("/app/rest/tours/{id}", TOUR_DEFAULT_ID)
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Read nonexisting Tour
        restTourMockMvc.perform(get("/app/rest/tours/{id}", TOUR_DEFAULT_ID)
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound());


    }
}
