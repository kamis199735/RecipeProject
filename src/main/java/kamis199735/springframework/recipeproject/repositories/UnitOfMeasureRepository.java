package kamis199735.springframework.recipeproject.repositories;

import kamis199735.springframework.recipeproject.domain.Category;
import kamis199735.springframework.recipeproject.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {

	Optional<UnitOfMeasure> findUnitOfMeasureByDescription(String description);
}
