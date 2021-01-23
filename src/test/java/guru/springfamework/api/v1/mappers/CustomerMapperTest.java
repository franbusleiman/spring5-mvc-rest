package guru.springfamework.api.v1.mappers;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

class CustomerMapperTest {

    CustomerMapper customerMapper;


    private static final String FIRSTNAME = "Tom";
    private static final String LASTNAME = "Bettiol";


    @Test
    void customerToCustomerDTO() {


        Customer customer = new Customer();

        customer.setFirstName(FIRSTNAME);
        customer.setLastName(LASTNAME);

        CustomerDTO customerDTO = customerMapper.INSTANCE.customerToCustomerDTO(customer);


        assertEquals(FIRSTNAME, customerDTO.getFirstName());
        assertEquals(LASTNAME, customerDTO.getLastName());
    }

    @Test
    void customerDTOToCustomer(){

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(FIRSTNAME);
        customerDTO.setLastName(LASTNAME);

        Customer customer = customerMapper.INSTANCE.customerDTOToCustomer(customerDTO);

        assertEquals(FIRSTNAME, customer.getFirstName());
        assertEquals(LASTNAME, customer.getLastName());
    }
}