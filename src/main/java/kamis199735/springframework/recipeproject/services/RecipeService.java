package kamis199735.springframework.recipeproject.services;

import kamis199735.springframework.recipeproject.commands.RecipeCommand;
import kamis199735.springframework.recipeproject.domain.Recipe;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.Set;

public interface RecipeService {

	Set<Recipe> getRecipes();

	Recipe findById(Long id);

	RecipeCommand saveRecipeCommand(RecipeCommand command);
}
