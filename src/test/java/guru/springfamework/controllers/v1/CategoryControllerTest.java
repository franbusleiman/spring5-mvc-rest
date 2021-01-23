package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static guru.springfamework.controllers.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class CategoryControllerTest {

    CategoryController categoryController;

    @Mock
    CategoryService categoryService;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);

        categoryController = new CategoryController(categoryService);



        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();


    }

    @Test
    void getCategories() throws Exception {

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(1L);

        CategoryDTO categoryDTO1 = new CategoryDTO();
        categoryDTO1.setId(2L);

        List<CategoryDTO> categories = new ArrayList<>();

        categories.add(categoryDTO);
        categories.add(categoryDTO1);

        when(categoryService.listAllCategories()).thenReturn(categories);

        mockMvc.perform(get("/api/v1/categories")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categories", hasSize(2)));
    }

    @Test
    void getCategoryByName() throws Exception{

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(1L);
        categoryDTO.setName("Fruits");

        when(categoryService.findCategoryByName(anyString())).thenReturn(categoryDTO);

        mockMvc.perform(get("/api/v1/categories/Fruits")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Fruits")));

    }
    @Test
    void saveCategory() throws Exception{
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName("Fruits");

        when(categoryService.saveCategory(any())).thenReturn(categoryDTO);

        mockMvc.perform(post("/api/v1/categories")
        .contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(categoryDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Fruits")));
    }
}