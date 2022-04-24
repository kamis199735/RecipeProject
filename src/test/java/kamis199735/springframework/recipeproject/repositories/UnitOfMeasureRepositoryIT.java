package kamis199735.springframework.recipeproject.repositories;

import jdk.nashorn.internal.ir.Optimistic;
import kamis199735.springframework.recipeproject.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryIT {

	@Autowired
	UnitOfMeasureRepository unitOfMeasureRepository;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void findUnitOfMeasureByDescription() {

		Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findUnitOfMeasureByDescription("Teaspoon");

		assertEquals("Teaspoon", uomOptional.get().getDescription());

	}

	@Test
	public void findUnitOfMeasureByDescriptionCup() {

		Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findUnitOfMeasureByDescription("Cup");

		assertEquals("Cup", uomOptional.get().getDescription());

	}
}