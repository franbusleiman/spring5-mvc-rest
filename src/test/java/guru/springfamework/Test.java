package guru.springfamework;

import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.services.CategoryService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class Test {

    @Autowired
    CategoryService categoryService;

@org.junit.jupiter.api.Test
        void getList() {
    List<CategoryDTO> categories = categoryService.listAllCategories();

    assertEquals(4, categories.size());

}




}
