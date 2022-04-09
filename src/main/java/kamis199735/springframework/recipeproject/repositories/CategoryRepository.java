package kamis199735.springframework.recipeproject.repositories;

import kamis199735.springframework.recipeproject.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {

	Optional<Category> findCategoryByDescription(String description);

}
