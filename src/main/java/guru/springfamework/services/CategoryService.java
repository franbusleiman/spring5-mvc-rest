package guru.springfamework.services;

import guru.springfamework.api.v1.model.CategoryDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> listAllCategories();
    CategoryDTO findCategoryByName(String name);
    CategoryDTO saveCategory(CategoryDTO categoryDTO);
}
