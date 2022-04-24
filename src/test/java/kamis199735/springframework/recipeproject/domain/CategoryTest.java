package kamis199735.springframework.recipeproject.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

	Category category;

	@BeforeEach
	void setUp() {
		category = new Category();
	}

	@Test
	void getRecipes() {
	}

	@Test
	void getId() {
		Long idValue = 4L;

		category.setId(idValue);

		assertEquals(idValue, category.getId());
	}

	@Test
	void getDescription() {
	}
}