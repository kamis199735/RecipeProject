package kamis199735.springframework.recipeproject.services;

import kamis199735.springframework.recipeproject.commands.IngredientCommand;
import kamis199735.springframework.recipeproject.converters.IngredientCommandToIngredient;
import kamis199735.springframework.recipeproject.converters.IngredientToIngredientCommand;
import kamis199735.springframework.recipeproject.converters.UnitOfMeasureCommandToUnitOfMeasure;
import kamis199735.springframework.recipeproject.converters.UnitOfMeasureToUnitOfMeasureCommand;
import kamis199735.springframework.recipeproject.domain.Ingredient;
import kamis199735.springframework.recipeproject.domain.Recipe;
import kamis199735.springframework.recipeproject.repositories.IngredientRepository;
import kamis199735.springframework.recipeproject.repositories.RecipeRepository;
import kamis199735.springframework.recipeproject.repositories.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class IngredientServiceImplTest {


	@Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

	private final IngredientCommandToIngredient ingredientCommandToIngredient;
	private final IngredientToIngredientCommand ingredientToIngredientCommand;
	@Mock
	RecipeRepository recipeRepository;

	@Mock
	IngredientRepository ingredientRepository;


	IngredientService ingredientService;

	//init converters
	public IngredientServiceImplTest() {

		this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
		this.ingredientCommandToIngredient = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		ingredientService = new IngredientServiceImpl(ingredientToIngredientCommand, ingredientCommandToIngredient, recipeRepository, unitOfMeasureRepository, ingredientRepository);
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
		verify(recipeRepository, Mockito.times(1)).findById(Mockito.anyLong());




	}


	@Test
	public void testSaveRecipeCommand() throws Exception {
		//given
		IngredientCommand command = new IngredientCommand();
		command.setId(3L);
		command.setRecipeId(2L);

		Optional<Recipe> recipeOptional = Optional.of(new Recipe());

		Recipe savedRecipe = new Recipe();
		savedRecipe.addIngredient(new Ingredient());
		savedRecipe.getIngredients().iterator().next().setId(3L);

		when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
		when(recipeRepository.save(any())).thenReturn(savedRecipe);

		//when
		IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

		//then
		assertEquals(Long.valueOf(3L), savedCommand.getId());
		verify(recipeRepository, times(1)).findById(anyLong());
		verify(recipeRepository, times(1)).save(any(Recipe.class));

	}


	@Test
	public void testDeleteById() throws Exception {
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		Ingredient ingredient = new Ingredient();
		ingredient.setId(3L);
		recipe.addIngredient(ingredient);

		when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));

		ingredientService.deleteIngredientByRecipeIdAndIngredientId(1L,3L);

		verify(recipeRepository, times(1)).findById(anyLong());
		verify(recipeRepository, times(1)).save(any());
	}
}