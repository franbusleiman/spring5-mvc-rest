package guru.springfamework.services;

import guru.springfamework.api.v1.mappers.CategoryMapper;
import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.domain.Category;
import guru.springfamework.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper){
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<CategoryDTO> listAllCategories() {


    List<CategoryDTO> categoryDTOS = categoryRepository.findAll().stream()
            .map(category -> categoryMapper.categoryToCategoryDTO(category))
            .collect(Collectors.toList());
    return categoryDTOS;
    }

    @Override
    public CategoryDTO findCategoryByName(String name) {
        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(categoryRepository.findCategoryByName(name));

        return categoryDTO;
    }

    @Override
    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {

        Category category = categoryMapper.categoryDTOToCategory(categoryDTO);
        Category categorySaved = categoryRepository.save(category);
        return categoryMapper.categoryToCategoryDTO(categorySaved);

    }
}
