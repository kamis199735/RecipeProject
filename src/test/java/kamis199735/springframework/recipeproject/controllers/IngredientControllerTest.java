package kamis199735.springframework.recipeproject.controllers;

import kamis199735.springframework.recipeproject.commands.IngredientCommand;
import kamis199735.springframework.recipeproject.commands.RecipeCommand;
import kamis199735.springframework.recipeproject.services.IngredientService;
import kamis199735.springframework.recipeproject.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class IngredientControllerTest {

	@Mock
	RecipeService recipeService;

	@Mock
	IngredientService ingredientService;

	MockMvc mockMvc;

	IngredientController controller;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		controller = new IngredientController(recipeService, ingredientService);
		mockMvc= MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void testListIngredients() throws Exception {
		//given
		RecipeCommand recipeCommand = new RecipeCommand();
		Mockito.when(recipeService.findCommandById(Mockito.anyLong())).thenReturn(recipeCommand);

		//when
		mockMvc.perform(get("/recipe/1/ingredient"))
				.andExpect(status().isOk())
				.andExpect(view().name("recipe/ingredient/list"))
				.andExpect(model().attributeExists("recipe"));

		//then
		Mockito.verify(recipeService, Mockito.times(1)).findCommandById(Mockito.anyLong());
	}

	@Test
	public void testShowIngredient() throws Exception {
		//given
		IngredientCommand ingredientCommand = new IngredientCommand();

		//when
		Mockito.when(ingredientService.findByRecipeIdAndIngredientId(Mockito.anyLong(), Mockito.anyLong())).thenReturn(ingredientCommand);

		//then
		mockMvc.perform(get("/recipe/1/ingredient/2/show"))
				.andExpect(status().isOk())
				.andExpect(view().name("recipe/ingredient/show"))
				.andExpect(model().attributeExists("ingredient"));
	}
}
