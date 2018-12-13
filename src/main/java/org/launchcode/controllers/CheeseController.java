package org.launchcode.classes.cheesemvc.controllers;

import org.launchcode.classes.cheesemvc.models.Category;
import org.launchcode.classes.cheesemvc.models.Cheese;
import org.launchcode.classes.cheesemvc.models.data.CategoryDao;
import org.launchcode.classes.cheesemvc.models.data.CheeseDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("cheese")
public class CheeseController {

    @Autowired
    private CheeseDao cheeseDao;

    @Autowired
    private CategoryDao categoryDao;

    private static String area = "cheese";

    // Request path: /cheese
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("cheeses", cheeseDao.findAll());
        model.addAttribute("title", "My Cheeses");
        model.addAttribute("area", area);

        return "cheese/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        model.addAttribute("area", area);
        model.addAttribute(new Cheese());
        model.addAttribute("categories", categoryDao.findAll());
        return "cheese/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(Model model, @ModelAttribute @Valid Cheese newCheese, Errors errors, @RequestParam int categoryId) {

        if (errors.hasErrors()){
            model.addAttribute("title", "Add Cheese");
            model.addAttribute("area", area);
            model.addAttribute("categories", categoryDao.findAll());
            return "cheese/add";
        }

        Category cat = categoryDao.findById(categoryId).get();
        // Category cat = categoryDao.findOne(categoryId);
        newCheese.setCategory(cat);
        cheeseDao.save(newCheese);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model) {
        model.addAttribute("cheeses", cheeseDao.findAll());
        model.addAttribute("area", area);
        model.addAttribute("title", "Remove Cheese");
        return "cheese/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam int[] cheeseIds) {

        for (int cheeseId : cheeseIds) {
            cheeseDao.deleteById(cheeseId);
        }

        return "redirect:";
    }

    @RequestMapping(value = "category", method = RequestMethod.GET)
    public String category(Model model, @RequestParam int id) {

        Category cat = categoryDao.findById(id).get();
        List<Cheese> cheeses = cat.getCheeses();
        model.addAttribute("cheeses", cheeses);
        model.addAttribute("title", "Cheeses in Category: " + cat.getName());
        model.addAttribute("area", area);
        return "cheese/index";
    }

}
