package guru.springfamework.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@AllArgsConstructor
@Component
public class CategoriesDTO {

    List<CategoryDTO> categories;
}
