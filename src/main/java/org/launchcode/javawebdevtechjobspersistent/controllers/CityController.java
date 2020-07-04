package org.launchcode.javawebdevtechjobspersistent.controllers;

import org.launchcode.javawebdevtechjobspersistent.models.City;
import org.launchcode.javawebdevtechjobspersistent.models.data.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("cities")
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @GetMapping()
    public String displayAllCities(Model model) {
        model.addAttribute("title", "All Cities");
        model.addAttribute("cities", cityRepository.findAll());
        return "cities/index";
    }

    @GetMapping("add")
    public String displayAddCityForm(Model model) {
        model.addAttribute("title", "Add City");
        model.addAttribute(new City());
        return "cities/add";
    }

    @PostMapping("add")
    public String processAddCityForm(@ModelAttribute @Valid City newCity,
                                         Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "cities/add";
        }
        cityRepository.save(newCity);
        return "redirect:";
    }

    @GetMapping("view/{cityId}")
    public String displayViewCity(Model model, @PathVariable int cityId) {
        Optional optCity = cityRepository.findById(cityId);
        if (optCity.isPresent()) {
            City city = (City) optCity.get();
            model.addAttribute("city", city);
            return "cities/view";
        } else {
            return "redirect:../";
        }
    }

    @GetMapping("delete")
    public String renderDeleteCityForm(Model model) {
        model.addAttribute("title", "Delete Cities");
        model.addAttribute("cities", cityRepository.findAll());
        return "cities/delete";
    }

    @PostMapping("delete")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] cityIds) {
        if (cityIds != null) {
            for (int id : cityIds) {
                cityRepository.deleteById(id);
            }
        }
        return "redirect:";
    }


}
