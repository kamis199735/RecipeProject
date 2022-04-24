package kamis199735.springframework.recipeproject.services;

import kamis199735.springframework.recipeproject.domain.Recipe;
import kamis199735.springframework.recipeproject.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RecipeServiceImplTest {

	RecipeService recipeService;

	@Mock
	RecipeRepository recipeRepository;

	@BeforeEach
	void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);

		recipeService = new RecipeServiceImpl(recipeRepository);
	}

	@Test
	void getRecipes() {
		Recipe recipe = new Recipe();
		HashSet recipesData = new HashSet();
		recipesData.add(recipe);

		Mockito.when(recipeRepository.findAll()).thenReturn(recipesData);
		Set<Recipe> recipes = (Set<Recipe>) recipeRepository.findAll();

		assertEquals(recipes.size(),1);
		Mockito.verify(recipeRepository, Mockito.times(1)).findAll();
	}
}