package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static guru.springfamework.controllers.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CustomerControllerTest {
    @Mock
    CustomerService customerService;

    CustomerController customerController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);

        customerController = new CustomerController(customerService);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();

    }

    @Test
    void getCustomers() throws Exception {

        CustomerDTO customer = new CustomerDTO();
        customer.setId(1L);

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(2L);

        List<CustomerDTO> customerDTOList = new ArrayList<>();
        customerDTOList.add(customer);
        customerDTOList.add(customerDTO);

        when(customerService.listAllCustomers()).thenReturn(customerDTOList);

        mockMvc.perform(get("/api/v1/customers")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));
    }

    @Test
    void getCustomerByFirstName() throws Exception{
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(1L);
        customerDTO.setFirstName("John");
        customerDTO.setLastName("Cena");

        when(customerService.getCustomerByName(anyString())).thenReturn(customerDTO);

        mockMvc.perform(get("/api/v1/customers/John")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("John")));

    }
    @Test
    void saveCustomer() throws Exception{

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("John");
        customerDTO.setLastName("Cena");

        when(customerService.saveCustomer(any())).thenReturn(customerDTO);

        mockMvc.perform(post("/api/v1/customers")
        .contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(customerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("John")));
    }
}