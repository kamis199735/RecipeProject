package kamis199735.springframework.recipeproject.services;

import kamis199735.springframework.recipeproject.commands.IngredientCommand;
import kamis199735.springframework.recipeproject.converters.IngredientCommandToIngredient;
import kamis199735.springframework.recipeproject.converters.IngredientToIngredientCommand;
import kamis199735.springframework.recipeproject.converters.UnitOfMeasureToUnitOfMeasureCommand;
import kamis199735.springframework.recipeproject.domain.Ingredient;
import kamis199735.springframework.recipeproject.domain.Recipe;
import kamis199735.springframework.recipeproject.repositories.RecipeRepository;
import kamis199735.springframework.recipeproject.repositories.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.*;

public class IngredientServiceImplTest {

	private final IngredientToIngredientCommand ingredientToIngredientCommand;

	@Mock
	RecipeRepository recipeRepository;

	IngredientService ingredientService;

	//init converters
	public IngredientServiceImplTest() {
		this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		ingredientService = new IngredientServiceImpl(ingredientToIngredientCommand, recipeRepository);
	}

	@Test
	public void findByRecipeIdAndIngredientIdHappyPath() throws Exception {
		//given
		Recipe recipe = new Recipe();
		recipe.setId(1L);

		Ingredient ingredient1 = new Ingredient();
		ingredient1.setId(1L);
		Ingredient ingredient2 = new Ingredient();
		ingredient2.setId(2L);
		Ingredient ingredient3 = new Ingredient();
		ingredient3.setId(3L);

		recipe.addIngredient(ingredient1);
		recipe.addIngredient(ingredient2);
		recipe.addIngredient(ingredient3);

		//when
		Mockito.when(recipeRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(recipe));

		IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(1L, 3L);

		//then
		assertEquals(Long.valueOf(3L), ingredientCommand.getId());
		assertEquals(Long.valueOf(1L), ingredientCommand.getRecipeId());
		Mockito.verify(recipeRepository, Mockito.times(1)).findById(Mockito.anyLong());




	}
}