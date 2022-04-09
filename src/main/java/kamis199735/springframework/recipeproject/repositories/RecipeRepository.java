package kamis199735.springframework.recipeproject.repositories;

import kamis199735.springframework.recipeproject.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}
