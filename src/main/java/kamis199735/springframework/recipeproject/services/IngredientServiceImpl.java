package kamis199735.springframework.recipeproject.services;

import kamis199735.springframework.recipeproject.commands.IngredientCommand;
import kamis199735.springframework.recipeproject.converters.IngredientCommandToIngredient;
import kamis199735.springframework.recipeproject.converters.IngredientToIngredientCommand;
import kamis199735.springframework.recipeproject.domain.Ingredient;
import kamis199735.springframework.recipeproject.domain.Recipe;
import kamis199735.springframework.recipeproject.repositories.IngredientRepository;
import kamis199735.springframework.recipeproject.repositories.RecipeRepository;
import kamis199735.springframework.recipeproject.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {
	private final IngredientToIngredientCommand ingredientToIngredientCommand;
	private final RecipeRepository recipeRepository;
	private final UnitOfMeasureRepository unitOfMeasureRepository;
	private final IngredientCommandToIngredient ingredientCommandToIngredient;
	private final IngredientRepository ingredientRepository;

	public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand,  IngredientCommandToIngredient ingredientCommandToIngredient,
								 RecipeRepository recipeRepository,
								 UnitOfMeasureRepository unitOfMeasureRepository, IngredientRepository ingredientRepository)
								 {
		this.ingredientToIngredientCommand = ingredientToIngredientCommand;
		this.ingredientCommandToIngredient = ingredientCommandToIngredient;
		this.recipeRepository = recipeRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
		this.ingredientRepository=ingredientRepository;

	}

	@Override
	public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {

		Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

		if (!recipeOptional.isPresent()){
			//todo impl error handling
			log.error("recipe id not found. Id: " + recipeId);
		}

		Recipe recipe = recipeOptional.get();

		Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
				.filter(ingredient -> ingredient.getId().equals(ingredientId))
				.map( ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();

		if(!ingredientCommandOptional.isPresent()){
			//todo impl error handling
			log.error("Ingredient id not found: " + ingredientId);
		}

		return ingredientCommandOptional.get();
	}

	@Override
	@Transactional
	public IngredientCommand saveIngredientCommand(IngredientCommand command) {
		Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());

		if(!recipeOptional.isPresent()){

			//todo toss error if not found!
			log.error("Recipe not found for id: " + command.getRecipeId());
			return new IngredientCommand();
		} else {
			Recipe recipe = recipeOptional.get();

			Optional<Ingredient> ingredientOptional = recipe
					.getIngredients()
					.stream()
					.filter(ingredient -> ingredient.getId().equals(command.getId()))
					.findFirst();

			if(ingredientOptional.isPresent()){
				Ingredient ingredientFound = ingredientOptional.get();
				ingredientFound.setDescription(command.getDescription());
				ingredientFound.setAmount(command.getAmount());
				ingredientFound.setUnitOfMeasure(unitOfMeasureRepository
						.findById(command.getUnitOfMeasure().getId())
						.orElseThrow(() -> new RuntimeException("UOM NOT FOUND"))); //todo address this
			} else {
				//add new Ingredient
				Ingredient ingredient = ingredientCommandToIngredient.convert(command);
				ingredient.setRecipe(recipe);
				recipe.addIngredient(ingredient);
			}

			Recipe savedRecipe = recipeRepository.save(recipe);

			Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredients().stream()
					.filter(recipeIngredients -> recipeIngredients.getId().equals(command.getId()))
					.findFirst();

			//check by description
			if(!savedIngredientOptional.isPresent()){
				//not totally safe... But best guess
				savedIngredientOptional = savedRecipe.getIngredients().stream()
						.filter(recipeIngredients -> recipeIngredients.getDescription().equals(command.getDescription()))
						.filter(recipeIngredients -> recipeIngredients.getAmount().equals(command.getAmount()))
						.filter(recipeIngredients -> recipeIngredients.getUnitOfMeasure().getId().equals(command.getUnitOfMeasure().getId()))
						.findFirst();
			}

			//to do check for fail
			return ingredientToIngredientCommand.convert(savedIngredientOptional.get());

		}

	}

	@Override
	@Transactional
	public void deleteIngredientByRecipeIdAndIngredientId(Long recipeId, Long id) {

		Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

		if(recipeOptional.isPresent()) {

			Recipe recipe = recipeOptional.get();

			recipe.getIngredients().stream().filter(ingredient -> ingredient.getId().equals(id)).forEach(ingredient -> ingredient.setRecipe(null));

			recipe.getIngredients().removeIf(ingredient -> ingredient.getId().equals(id));

			recipeRepository.save(recipe);
		}

	}
}
