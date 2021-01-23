package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.api.v1.model.CustomersDTO;
import guru.springfamework.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;

    }
        @GetMapping
                public ResponseEntity<CustomersDTO> getCustomers(){

            return new ResponseEntity<CustomersDTO>(new CustomersDTO(customerService.listAllCustomers()), HttpStatus.OK);
        }

        @GetMapping("/{firstName}")
    public ResponseEntity<CustomerDTO> getCustomerByFirstName(@PathVariable String firstName){


        return new ResponseEntity<CustomerDTO>(customerService.getCustomerByName(firstName), HttpStatus.OK);
        }

@PostMapping
    public ResponseEntity<CustomerDTO> saveCustomer(@RequestBody CustomerDTO customerDTO){

        return new ResponseEntity<CustomerDTO>(customerService.saveCustomer(customerDTO), HttpStatus.OK);

}

@PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable int id, @RequestBody CustomerDTO customerDTO){

        return new ResponseEntity<CustomerDTO>(customerService.updateCustomer(customerDTO, Long.valueOf(id)), HttpStatus.OK);
}





}
