package kamis199735.springframework.recipeproject.repositories;

import kamis199735.springframework.recipeproject.commands.IngredientCommand;
import kamis199735.springframework.recipeproject.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Optional;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {

	Optional<Ingredient> findIngredientByRecipeIdAndId(Long recipeId, Long id);

}
