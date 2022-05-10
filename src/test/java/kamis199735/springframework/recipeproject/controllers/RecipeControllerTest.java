package kamis199735.springframework.recipeproject.controllers;

import kamis199735.springframework.recipeproject.commands.RecipeCommand;
import kamis199735.springframework.recipeproject.domain.Recipe;
import kamis199735.springframework.recipeproject.exceptions.NotFoundException;
import kamis199735.springframework.recipeproject.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class RecipeControllerTest {

	@Mock
	RecipeService recipeService;

	RecipeController controller;

	MockMvc mockMvc;

	@BeforeEach
	void setUp() throws Exception{
		MockitoAnnotations.openMocks(this);
		controller=new RecipeController(recipeService);
		mockMvc= MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	void testGetRecipe() throws Exception {
		Recipe recipe = new Recipe();
		recipe.setId(1L);

    	Mockito.when(recipeService.findById(Mockito.anyLong())).thenReturn(recipe);

		mockMvc.perform(get("/recipe/1/show"))
				.andExpect(status().isOk())
				.andExpect(view().name("recipe/show"))
				.andExpect(model().attributeExists("recipe"));
	}


	@Test
	void testGetNewRecipeForm() throws Exception {


		RecipeCommand command = new RecipeCommand();

		mockMvc.perform(get("/recipe/new"))
				.andExpect((status().isOk()))
				.andExpect(view().name("recipe/recipeform"))
				.andExpect(model().attributeExists("recipe"));

	}

	@Test
	void testGetRecipeNotFound() throws Exception {
		Recipe recipe = new Recipe();
		recipe.setId(1L);

		when(recipeService.findById(anyLong())).thenThrow(NotFoundException.class);

		mockMvc.perform(get("/recipe/1/show"))
				.andExpect(status().isNotFound());
	}

	@Test
	void testPostNewRecipeForm() throws Exception{
		RecipeCommand command = new RecipeCommand();
		command.setId(2L);
		Mockito.when(recipeService.saveRecipeCommand(Mockito.any())).thenReturn(command);

		mockMvc.perform(post("/recipe")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("id","")
				.param("description", "some stromg"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/recipe/2/show"));
	}

	@Test
	void testGetUpdateView() throws Exception {
		RecipeCommand command = new RecipeCommand();
		command.setId(2L);

		Mockito.when(recipeService.findCommandById(Mockito.any())).thenReturn(command);

		mockMvc.perform(get("/recipe/1/update"))
				.andExpect(status().isOk())
				.andExpect(view().name("recipe/recipeform"))
				.andExpect(model().attributeExists("recipe"));
	}

	@Test
	void testDeleteAction() throws Exception{
		mockMvc.perform(get("/recipe/1/delete"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/"));

		Mockito.verify(recipeService, Mockito.times(1)).deleteById(anyLong());
	}
}
