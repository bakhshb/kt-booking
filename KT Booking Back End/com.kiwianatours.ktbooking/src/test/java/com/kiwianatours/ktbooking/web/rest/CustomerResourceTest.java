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
import com.kiwianatours.ktbooking.repository.BookingRepository;
import com.kiwianatours.ktbooking.repository.CustomerRepository;


/**
 * Test class for the CustomerResource REST controller.
 *
 * @see CustomerResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class })
@ActiveProfiles("dev")
public class CustomerResourceTest {
    
    private static final Long DEFAULT_ID = new Long(1L);

    private static final String DEFAULT_FIRST_NAME = "test";
    
    private static final String DEFAULT_LAST_NAME = "test";
    
    private static final LocalDate DEFAULT_BIRTHDAY = new LocalDate(1986,04,25);
    
    private static final String DEFAULT_PERMISSION_FROM= "test";
    
    private static final String DEFAULT_GENDER = "male";
    
    private static final String DEFAULT_NATIONALITY = "test";
    
    private static final String DEFAULT_EMAIL = "test@test.com";
    
    private static final String DEFAULT_CONTACT_NO = "02777777";
    
    private static final String DEFAULT_ADDITIONAL_INFO = null;

   

    @Inject
    private CustomerRepository customerRepository;
    
    @Inject
    private BookingRepository bookingRepository;

    private MockMvc restCustomerMockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CustomerResource customerResource = new CustomerResource();
        ReflectionTestUtils.setField(customerResource, "customerRepository", customerRepository);
        ReflectionTestUtils.setField(customerResource, "bookingRepository", bookingRepository);

        this.restCustomerMockMvc = MockMvcBuilders.standaloneSetup(customerResource).build();
        
    }

    @Test
    public void testCRUDCustomer() throws Exception {	

        // Read Customer
        restCustomerMockMvc.perform(get("/app/rest/customers/{id}", DEFAULT_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(DEFAULT_ID.intValue()))
                .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
                .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
                .andExpect(jsonPath("$.birthday").value(DEFAULT_BIRTHDAY.toString()))
                .andExpect(jsonPath("$.permissionFrom").value(DEFAULT_PERMISSION_FROM))
                .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER))
                .andExpect(jsonPath("$.nationality").value(DEFAULT_NATIONALITY))
                .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
                .andExpect(jsonPath("$.contactNo").value(DEFAULT_CONTACT_NO))
                .andExpect(jsonPath("$.additionalinfo").value(DEFAULT_ADDITIONAL_INFO));

        
        // Delete Customer
        restCustomerMockMvc.perform(delete("/app/rest/customers/{id}", DEFAULT_ID)
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Read nonexisting Customer
        restCustomerMockMvc.perform(get("/app/rest/customers/{id}", DEFAULT_ID)
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound());

    }
}
