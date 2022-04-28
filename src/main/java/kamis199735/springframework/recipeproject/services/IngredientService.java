package kamis199735.springframework.recipeproject.services;

import kamis199735.springframework.recipeproject.commands.IngredientCommand;
import kamis199735.springframework.recipeproject.domain.Ingredient;

public interface IngredientService {

	IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);

	IngredientCommand saveIngredientCommand(IngredientCommand command);

	void deleteIngredientByRecipeIdAndIngredientId(Long recipeId, Long id);

}
