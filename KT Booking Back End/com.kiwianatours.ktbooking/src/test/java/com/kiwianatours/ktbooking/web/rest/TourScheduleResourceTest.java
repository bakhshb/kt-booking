package com.kiwianatours.ktbooking.web.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import com.kiwianatours.ktbooking.domain.Tour;
import com.kiwianatours.ktbooking.domain.TourSchedule;
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
    
    private static final Long DEFAULT_ID = new Long(1L);

    private static final String DEFAULT_TOUR_NAME = "Auckland";
       
    private static final LocalDate DEFAULT_DEPARTURE_DATE = new LocalDate(2014,04,25);

    private static final LocalDate UPD_DEPARTURE_DATE = DEFAULT_DEPARTURE_DATE.plusDays(1);
    
    private static final LocalDate DEFAULT_RETURN_DATE = new LocalDate();

    private static final LocalDate UPD_RETURN_DATE = DEFAULT_RETURN_DATE.minusDays(3);
    
    private static final double DEFAULT_PRICE = 100.0;

    private static final double UPD_PRICE = 150.80;
    

    @Inject
    private TourScheduleRepository tourScheduleRepository;

    private MockMvc restTourinfoMockMvc;

    private TourSchedule tourSchedule;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TourScheduleResource tourScheduleResource = new TourScheduleResource();
        ReflectionTestUtils.setField(tourScheduleResource, "tourScheduleRepository", tourScheduleRepository);

        this.restTourinfoMockMvc = MockMvcBuilders.standaloneSetup(tourScheduleResource).build();

       
        tourSchedule = new TourSchedule();
        tourSchedule.setId(DEFAULT_ID);
        Tour tour = new Tour();
        tour.setName(DEFAULT_TOUR_NAME);
        tourSchedule.setTour(tour);
        tourSchedule.setDepartureDate(DEFAULT_DEPARTURE_DATE);
        tourSchedule.setReturnDate(DEFAULT_RETURN_DATE);
        tourSchedule.setPrice(DEFAULT_PRICE);
        
       
    }

    @Test
    public void testCRUDTourinfo() throws Exception {

        // Create Tourinfo
        restTourinfoMockMvc.perform(post("/app/rest/tourschedules")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(tourSchedule)))
                .andExpect(status().isOk());

        // Read Tourinfo
        restTourinfoMockMvc.perform(get("/app/rest/tourschedules/{id}", DEFAULT_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(DEFAULT_ID.intValue()))
                .andExpect(jsonPath("$.depatureDate").value(DEFAULT_DEPARTURE_DATE.toString()))
                .andExpect(jsonPath("$.returnDate").value(DEFAULT_RETURN_DATE.toString()))
                .andExpect(jsonPath("$.price").value(DEFAULT_PRICE));
                

        // Update Tourinfo
        tourSchedule.setDepartureDate(UPD_DEPARTURE_DATE);
        tourSchedule.setReturnDate(UPD_RETURN_DATE);
        tourSchedule.setPrice(UPD_PRICE);
        

        restTourinfoMockMvc.perform(post("/app/rest/tourschedules")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(tourSchedule)))
                .andExpect(status().isOk());

        // Read updated Tourinfo
        restTourinfoMockMvc.perform(get("/app/rest/tourschedules/{id}", DEFAULT_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(DEFAULT_ID.intValue()))
                .andExpect(jsonPath("$.depatureDate").value(UPD_DEPARTURE_DATE.toString()))
                .andExpect(jsonPath("$.returnDate").value(UPD_RETURN_DATE.toString()))
                .andExpect(jsonPath("$.price").value(UPD_PRICE));

        // Delete Tourinfo
        restTourinfoMockMvc.perform(delete("/app/rest/tourschedules/{id}", DEFAULT_ID)
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Read nonexisting Tourinfo
        restTourinfoMockMvc.perform(get("/app/rest/tourschedules/{id}", DEFAULT_ID)
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound());

    }
}
