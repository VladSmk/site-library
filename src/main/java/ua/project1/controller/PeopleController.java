package ua.project1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.project1.dao.PeopleDAO;
import ua.project1.model.People;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PeopleDAO peopleDAO;
    @Autowired
    public PeopleController(PeopleDAO peopleDAO) {
        this.peopleDAO = peopleDAO;
    }


    @GetMapping()
    public String peoples(){
        return "people/index";
    }

    @GetMapping("/new")
    public String create_new_people (Model model) {
        model.addAttribute("people", new People());
        return "people/new";
    }
    @PostMapping("/newPeople")
    public String create_new_people (@ModelAttribute("people") People people) {
        peopleDAO.add_people(people);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit_people (@PathVariable("id") int id, Model model) {
        return "people/edit";
    }

}
