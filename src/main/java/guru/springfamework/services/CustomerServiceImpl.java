package guru.springfamework.services;

import guru.springfamework.api.v1.mappers.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper){
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }


    @Override
    public List<CustomerDTO> listAllCustomers() {
        List<CustomerDTO> customerDTOS = customerRepository.findAll().stream()
                .map(customer -> customerMapper.customerToCustomerDTO(customer))
                .collect(Collectors.toList());

        return customerDTOS;
    }

    @Override
    public CustomerDTO getCustomerByName(String name) {
        return customerMapper.customerToCustomerDTO(customerRepository.findCustomerByFirstName(name));
    }

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {

        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        Customer customerSaved = customerRepository.save(customer);
        return customerMapper.customerToCustomerDTO(customerSaved);

    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO, Long id) {

        Optional<Customer> customer = customerRepository.findById(id);

        if(! customer.isPresent()){
            return null;
        }

       Customer customerGet = customer.get();
        customerGet.setId(id);

        Customer customerSaved = customerRepository.save(customer.get());

CustomerDTO customerDTO1 = customerMapper.customerToCustomerDTO(customerSaved);

return customerDTO1;

    }


}
