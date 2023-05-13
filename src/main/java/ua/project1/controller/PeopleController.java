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
    public String peoples(Model model){
        model.addAttribute("people_list", peopleDAO.getPeopleList());
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
        model.addAttribute("people", peopleDAO.getPeopleById(id));
        return "people/edit";
    }
    @PatchMapping("/editPeople/{id}")
    public String edit_people (@ModelAttribute("people") People people,
                               @PathVariable("id") int id) {
        people.setId(id);
        peopleDAO.editPeople(people);
        return "redirect:/people/"+people.getId();
    }

    @GetMapping("/{id}")
    public String look_people (@PathVariable("id") int id, Model model) {
        model.addAttribute("people", peopleDAO.getPeopleById(id));
        return "people/review";
    }

    @DeleteMapping("/{id}/delete")
    public String delete_people (@PathVariable("id") int id) {
        peopleDAO.deletePeople(id);
        return "redirect:/people";
    }

}
