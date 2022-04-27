package kamis199735.springframework.recipeproject.services;

import kamis199735.springframework.recipeproject.converters.RecipeCommandToRecipe;
import kamis199735.springframework.recipeproject.converters.RecipeToRecipeCommand;
import kamis199735.springframework.recipeproject.domain.Recipe;
import kamis199735.springframework.recipeproject.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RecipeServiceImplTest {

	RecipeService recipeService;

	@Mock
	RecipeRepository recipeRepository;

	@Mock
	RecipeCommandToRecipe recipeCommandToRecipe;

	@Mock
	RecipeToRecipeCommand recipeToRecipeCommand;

	@BeforeEach
	void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);

		recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
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

	@Test
	void getRecipeByIdTest() throws Exception {
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		Optional<Recipe> recipeOptional = Optional.of(recipe);

		Mockito.when(recipeRepository.findById(Mockito.anyLong())).thenReturn(recipeOptional);

		Recipe recipeReturned = recipeService.findById(1L);

		assertNotNull(recipeReturned);
		Mockito.verify(recipeRepository, Mockito.times((1))).findById(Mockito.anyLong());
		Mockito.verify(recipeRepository, Mockito.never()).findAll() ;
	}

	@Test
	void testDeleteById() throws Exception {

		Long idToDelete = Long.valueOf(2L);
		recipeService.deleteById(idToDelete);

		Mockito.verify(recipeRepository, Mockito.times(1)).deleteById(Mockito.anyLong());
	}
}