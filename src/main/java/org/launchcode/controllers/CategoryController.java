package org.launchcode.classes.cheesemvc.controllers;

import org.launchcode.classes.cheesemvc.models.Category;
import org.launchcode.classes.cheesemvc.models.data.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    CategoryDao categoryDao;

    private static String area = "category";

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model, @RequestParam(defaultValue = "1") int id) {
        model.addAttribute("title", "Categories");
        model.addAttribute("area", area);
        model.addAttribute("categories", categoryDao.findAll());
        return "category/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute(new Category());
        model.addAttribute("title", "Add Category");
        model.addAttribute("area", area);
        return "category/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid Category category, Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Category");
            model.addAttribute("area", area);
            return "category/add";
        }

        categoryDao.save(category);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCategoryForm(Model model) {
        model.addAttribute("categories", categoryDao.findAll());
        model.addAttribute("area", area);
        model.addAttribute("title", "Remove Category");
        return "category/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCategoryForm(@RequestParam int[] categoryIds) {

        for (int categoryId : categoryIds) {
            categoryDao.deleteById(categoryId);
        }

        return "redirect:";
    }

}
