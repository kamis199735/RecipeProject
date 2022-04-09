package kamis199735.springframework.recipeproject.controllers;

import kamis199735.springframework.recipeproject.domain.Category;
import kamis199735.springframework.recipeproject.domain.UnitOfMeasure;
import kamis199735.springframework.recipeproject.repositories.CategoryRepository;
import kamis199735.springframework.recipeproject.repositories.UnitOfMeasureRepository;
import kamis199735.springframework.recipeproject.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"","/","/index"})
    public String getIndexPage(Model model){

        model.addAttribute("recipes",recipeService.getRecipes());


        return "index";
    }

}
