package com.kiwianatours.ktbooking.web.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.inject.Inject;

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
import com.kiwianatours.ktbooking.repository.TourRepository;


/**
 * Test class for the TourResource REST controller.
 *
 * @see TourResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class })
@ActiveProfiles("dev")
public class TourResourceTest {
    
    private static final Long DEFAULT_ID = new Long(1L);

    private static final String DEFAULT_NAME = "Auckland";

    private static final String UPD_NAME = "Hamilton";
    
    private static final String DEFAULT_SHORT_DESCRIPTION = "Great trip";

    private static final String UPD_SHORT_DESCRIPTION  = "Nice trip";
    
    private static final String DEFAULT_DESCRIPTION = "Swimming";

    private static final String UPD_DESCRIPTION = "Football";
    
    private static boolean DEFAULT_ACTIVE = false;
    
    private static boolean UPD_ACTIVE = true;
        
    

    @Inject
    private TourRepository tourRepository;

    private MockMvc restTourMockMvc;

    private Tour tour;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TourResource tourResource = new TourResource();
        ReflectionTestUtils.setField(tourResource, "tourRepository", tourRepository);

        this.restTourMockMvc = MockMvcBuilders.standaloneSetup(tourResource).build();

        tour = new Tour();
        tour.setId(DEFAULT_ID);
        tour.setName(DEFAULT_NAME);
        tour.setShortDescription(DEFAULT_SHORT_DESCRIPTION);
        tour.setDescription(DEFAULT_DESCRIPTION);
        tour.setActivated(DEFAULT_ACTIVE);
    }

    @Test
    public void testCRUDTour() throws Exception {

        // Create Tour
        restTourMockMvc.perform(post("/app/rest/tours")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(tour)))
                .andExpect(status().isOk());

        // Read Tour
        restTourMockMvc.perform(get("/app/rest/tours/{id}", DEFAULT_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(DEFAULT_ID.intValue()))
                .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
                .andExpect(jsonPath("$.shortDescription").value(DEFAULT_SHORT_DESCRIPTION))
                .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
                .andExpect(jsonPath("$.activated").value(DEFAULT_ACTIVE));


        // Update Tour
        tour.setName(UPD_NAME);
        tour.setShortDescription(UPD_SHORT_DESCRIPTION);
        tour.setDescription(UPD_DESCRIPTION);
        tour.setActivated(UPD_ACTIVE);


        restTourMockMvc.perform(post("/app/rest/tours")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(tour)))
                .andExpect(status().isOk());

        // Read updated Tour
        restTourMockMvc.perform(get("/app/rest/tours/{id}", DEFAULT_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(DEFAULT_ID.intValue()))
                .andExpect(jsonPath("$.name").value(UPD_NAME))
                .andExpect(jsonPath("$.shortDescription").value(UPD_SHORT_DESCRIPTION))
                .andExpect(jsonPath("$.description").value(UPD_DESCRIPTION))
                .andExpect(jsonPath("$.activated").value(UPD_ACTIVE));

        // Delete Tour
        restTourMockMvc.perform(delete("/app/rest/tours/{id}", DEFAULT_ID)
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Read nonexisting Tour
        restTourMockMvc.perform(get("/app/rest/tours/{id}", DEFAULT_ID)
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound());

    }
}
