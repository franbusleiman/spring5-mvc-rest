package guru.springfamework.services;

import guru.springfamework.api.v1.mappers.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class CustomerServiceImplTest {

    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    private static final Long ID = 1L;
    private static final String FIRSTNAME = "John";
    private static final String LASTNAME = "Wik";


    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);

        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    void listAllCustomers() {

        Customer customer1 = new Customer();
        customer1.setId(1L);
        Customer customer2 = new Customer();
        customer2.setId(2L);

        List<Customer> customers = new ArrayList<>();
        customers.add(customer1);
        customers.add(customer2);

        when(customerRepository.findAll()).thenReturn(customers);

        List<CustomerDTO> customerDTOS = customerService.listAllCustomers();

        assertEquals(2, customerDTOS.size());
    }


    @Test
    void getCustomerByName() {

        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstName(FIRSTNAME);
        customer.setLastName(LASTNAME);

        when(customerRepository.findCustomerByFirstName(anyString())).thenReturn(customer);

        CustomerDTO customerDTO = customerService.getCustomerByName("John");

        assertEquals(ID, customerDTO.getId());
        assertEquals(FIRSTNAME, customerDTO.getFirstName());
        assertEquals(LASTNAME, customerDTO.getLastName());


    }

    @Test
    void saveCustomer() {

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(FIRSTNAME);
        customerDTO.setId(ID);

        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);

        when(customerRepository.save(any())).thenReturn(customer);

        CustomerDTO customer1 = customerMapper.customerToCustomerDTO(customer);

        CustomerDTO customerDTO1 = customerService.saveCustomer(customerDTO);

        Assertions.assertEquals(customerDTO.getId(), customerDTO1.getId());
    }
}