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
import com.kiwianatours.ktbooking.domain.Customer;
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

    private static final String DEFAULT_FIRST_NAME = "Baraa";

    private static final String UPD_FIRST_NAME = "Abrar";
    
    private static final String DEFAULT_LAST_NAME = "Faez";

    private static final String UPD_LAST_NAME = "Bakhsh";
    
    private static final LocalDate DEFAULT_BIRTHDAY = new LocalDate(2014-11-11);

    private static final LocalDate UPD_BIRTHDAY = new LocalDate(2014-12-12);
    
    private static final String DEFAULT_PERMISSION_FROM= "Parents";

    private static final String UPD_PERMISSION_FROM = "Father";
    
    private static final String DEFAULT_GENDER = "male";

    private static final String UPD_GENDER = "female";
    
    private static final String DEFAULT_NATIONALITY = "Saudi";

    private static final String UPD_NATIONALITY = "New Zealand";
    
    private static final String DEFAULT_EMAIL = "bakhsh@gmail.com";

    private static final String UPD_EMAIL = "bakhshb@gmail.com";
    
    private static final String DEFAULT_CONTACT_NO = "027777777";

    private static final String UPD_CONTACT_NO  = "0275553855";
    
    private static final String DEFAULT_ADDITIONAL_INFO = "No Comments";

    private static final String UPD_ADDITIONAL_INFO = "Wrong Identity";        
   

    @Inject
    private CustomerRepository customerRepository;

    private MockMvc restCustomerMockMvc;

    private Customer customer;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CustomerResource customerResource = new CustomerResource();
        ReflectionTestUtils.setField(customerResource, "customerRepository", customerRepository);

        this.restCustomerMockMvc = MockMvcBuilders.standaloneSetup(customerResource).build();

        customer = new Customer();
        customer.setId(DEFAULT_ID);
        customer.setFirstName(DEFAULT_FIRST_NAME);
        customer.setLastName(DEFAULT_LAST_NAME);
        customer.setBirthday(DEFAULT_BIRTHDAY);
        customer.setPermissionFrom(DEFAULT_PERMISSION_FROM);
        customer.setGender(DEFAULT_GENDER);
        customer.setNationality(DEFAULT_NATIONALITY);
        customer.setEmail(DEFAULT_EMAIL);
        customer.setContactNo(DEFAULT_CONTACT_NO);
        customer.setAdditionalinfo(DEFAULT_ADDITIONAL_INFO);
       

    }

    @Test
    public void testCRUDCustomer() throws Exception {

        // Create Customer
        restCustomerMockMvc.perform(post("/app/rest/customers")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(customer)))
                .andExpect(status().isOk());

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

        // Update Customer
        customer.setFirstName(UPD_FIRST_NAME);
        customer.setLastName(UPD_LAST_NAME);
        customer.setBirthday(UPD_BIRTHDAY);
        customer.setPermissionFrom(UPD_PERMISSION_FROM);
        customer.setGender(UPD_GENDER);
        customer.setNationality(UPD_NATIONALITY);
        customer.setEmail(UPD_EMAIL);
        customer.setContactNo(UPD_CONTACT_NO);
        customer.setAdditionalinfo(UPD_ADDITIONAL_INFO);

        

        restCustomerMockMvc.perform(post("/app/rest/customers")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(customer)))
                .andExpect(status().isOk());

        // Read updated Customer
        restCustomerMockMvc.perform(get("/app/rest/customers/{id}", DEFAULT_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(DEFAULT_ID.intValue()))
                .andExpect(jsonPath("$.firstName").value(UPD_FIRST_NAME))
                .andExpect(jsonPath("$.lastName").value(UPD_LAST_NAME))
                .andExpect(jsonPath("$.birthday").value(UPD_BIRTHDAY.toString()))
                .andExpect(jsonPath("$.permissionFrom").value(UPD_PERMISSION_FROM))
                .andExpect(jsonPath("$.gender").value(UPD_GENDER))
                .andExpect(jsonPath("$.nationality").value(UPD_NATIONALITY))
                .andExpect(jsonPath("$.email").value(UPD_EMAIL))
                .andExpect(jsonPath("$.contactNo").value(UPD_CONTACT_NO))
                .andExpect(jsonPath("$.additionalinfo").value(UPD_ADDITIONAL_INFO));
        
        
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
