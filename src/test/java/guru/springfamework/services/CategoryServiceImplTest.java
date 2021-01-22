package guru.springfamework.services;

import guru.springfamework.api.v1.mappers.CategoryMapper;
import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.domain.Category;
import guru.springfamework.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class CategoryServiceImplTest {

    @Mock
    CategoryRepository categoryRepository;

    CategoryService categoryService;

    public static final String NAME = "fruits";
    public static final Long ID = 2L;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);

        categoryService = new CategoryServiceImpl(categoryRepository, CategoryMapper.INSTANCE );
    }

    @Test
    void listAllCategories() {

        Category category1 = new Category();
        category1.setId(1L);
        Category category2 = new Category();
        category2.setId(2L);

        List<Category> categorys = new ArrayList<>();
        categorys.add(category1);
        categorys.add(category2);

        when(categoryRepository.findAll()).thenReturn(categorys);

        List<CategoryDTO> categoryDTOS = categoryService.listAllCategories();

        assertEquals(2, categoryDTOS.size());



    }

    @Test
    void findCategoryByName() {

        Category category = new Category();
        category.setName(NAME);
        category.setId(ID);

        when(categoryRepository.findCategoryByName(anyString())).thenReturn(category);

        CategoryDTO categoryDTO = categoryService.findCategoryByName(NAME);

        assertEquals(NAME, categoryDTO.getName());
        assertEquals(ID, categoryDTO.getId());
    }
}