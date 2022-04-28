package kamis199735.springframework.recipeproject.services;

import kamis199735.springframework.recipeproject.commands.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService {

	Set<UnitOfMeasureCommand> listAllUoms();
}
