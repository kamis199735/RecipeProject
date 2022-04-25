package kamis199735.springframework.recipeproject.commands;

import kamis199735.springframework.recipeproject.domain.Recipe;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NotesCommand {

	private Long id;
	private String recipeNotes;


}
