package kamis199735.springframework.recipeproject.commands;


import kamis199735.springframework.recipeproject.domain.Difficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.*;

import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {
	private Long id;

	@javax.validation.constraints.NotBlank
	@Size(min = 3, max = 255)
	private String description;
	@Min(1)
	@Max(999)
	private Integer prepTime;
	@Min(1)
	@Max(999)
	private Integer cookTime;
	@Min(1)
	@Max(999)
	private Integer servings;
	private String source;
	@URL
	private String url;

	@javax.validation.constraints.NotBlank
	private String directions;
	private Byte[] image;
	private Set<IngredientCommand> ingredients = new HashSet<>();
	private Difficulty difficulty;
	private NotesCommand notes;
	private Set<CategoryCommand> categories = new HashSet<>();
}
