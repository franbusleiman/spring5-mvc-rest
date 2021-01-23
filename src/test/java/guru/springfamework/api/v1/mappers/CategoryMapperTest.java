package guru.springfamework.api.v1.mappers;

import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.domain.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryMapperTest {

    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    private static final Long ID = 2L;
    private static final String NAME = "cheeses";

    @org.junit.jupiter.api.Test
    void categoryToCategoryDTO() {

        Category category = new Category();
        category.setId(ID);
        category.setName(NAME);

        CategoryDTO newDTO = categoryMapper.categoryToCategoryDTO(category);

        assertEquals(ID , newDTO.getId());
        assertEquals(NAME, newDTO.getName());



    }

    @Test
    void categoryDTOToCategory(){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(ID);
        categoryDTO.setName(NAME);

       Category category = categoryMapper.categoryDTOToCategory(categoryDTO);

       assertEquals(ID, category.getId());
       assertEquals(NAME, category.getName());

    }}