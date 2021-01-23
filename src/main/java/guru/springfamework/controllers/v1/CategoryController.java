package guru.springfamework.controllers.v1;


import guru.springfamework.api.v1.model.CategoriesDTO;
import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;


    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;

    }

   @GetMapping
    public ResponseEntity<CategoriesDTO> getCategories(){


        return new ResponseEntity<CategoriesDTO>(new CategoriesDTO(categoryService.listAllCategories()), HttpStatus.OK);
   }

   @GetMapping("/" + "{name}")
    public ResponseEntity<CategoryDTO> getCategoryByName(@PathVariable String name){


        return new ResponseEntity<CategoryDTO>(categoryService.findCategoryByName(name), HttpStatus.OK);
   }

   @PostMapping
    public ResponseEntity<CategoryDTO> saveCategory(@RequestBody CategoryDTO categoryDTO){

    return new ResponseEntity<CategoryDTO>(categoryService.saveCategory(categoryDTO), HttpStatus.OK);

}
}