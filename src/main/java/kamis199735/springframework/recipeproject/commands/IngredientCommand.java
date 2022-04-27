package kamis199735.springframework.recipeproject.commands;

import kamis199735.springframework.recipeproject.domain.UnitOfMeasure;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Setter
@Getter
@NoArgsConstructor
public class IngredientCommand {

	private Long id;
	private Long recipeId;
	private BigDecimal amount;
	private String description;
	private UnitOfMeasureCommand unitOfMeasure;

}
