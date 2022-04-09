package kamis199735.springframework.recipeproject.services;

import kamis199735.springframework.recipeproject.domain.Recipe;

import java.util.Set;

public interface RecipeService {

	Set<Recipe> getRecipes();
}
