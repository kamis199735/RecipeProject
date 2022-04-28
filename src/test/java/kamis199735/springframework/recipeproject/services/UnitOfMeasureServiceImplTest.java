package kamis199735.springframework.recipeproject.services;

import kamis199735.springframework.recipeproject.commands.UnitOfMeasureCommand;
import kamis199735.springframework.recipeproject.converters.UnitOfMeasureToUnitOfMeasureCommand;
import kamis199735.springframework.recipeproject.domain.UnitOfMeasure;
import kamis199735.springframework.recipeproject.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UnitOfMeasureServiceImplTest {

	UnitOfMeasureService service;

	UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();

	@Mock
	UnitOfMeasureRepository unitOfMeasureRepository;


	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		service = new UnitOfMeasureServiceImpl(unitOfMeasureRepository, unitOfMeasureToUnitOfMeasureCommand);
	}

	@Test
	void listAllUoms() {

		//given
		Set<UnitOfMeasure> unitOfMeasures = new HashSet<>();
		UnitOfMeasure uom1 = new UnitOfMeasure();
		uom1.setId(1L);
		unitOfMeasures.add(uom1);

		UnitOfMeasure uom2 = new UnitOfMeasure();
		uom2.setId(2L);
		unitOfMeasures.add(uom2);

		Mockito.when(unitOfMeasureRepository.findAll()).thenReturn(unitOfMeasures);

		//when
		Set<UnitOfMeasureCommand> commands = service.listAllUoms();

		//then
		assertEquals(2, commands.size());
		Mockito.verify(unitOfMeasureRepository, Mockito.times(1)).findAll();

	}
}