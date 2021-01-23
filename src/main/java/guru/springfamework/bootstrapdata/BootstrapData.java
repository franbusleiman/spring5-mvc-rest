package guru.springfamework.bootstrapdata;

import guru.springfamework.domain.Category;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;

    public BootstrapData(CategoryRepository categoryRepository, CustomerRepository customerRepository){
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;

    }

    @Override
    public void run(String... args) throws Exception {

        Category friut = new Category();
        friut.setName("Fruit");

        Category dried = new Category();
        dried.setName("Dried");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(friut);
        categoryRepository.save(dried);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);


        Customer Tom = new Customer();
        Tom.setFirstName("Tom");
        Tom.setLastName("Bettiol");

        Customer John = new Customer();
        John.setFirstName("John");
        John.setLastName("Thompson");

        customerRepository.save(Tom);
        customerRepository.save(John);

        System.out.println("Categories charged number: " + categoryRepository.count());
        System.out.println("Customers charged number: " + customerRepository.count());
    }
}
