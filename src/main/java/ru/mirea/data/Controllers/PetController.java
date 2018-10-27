package ru.mirea.data.Controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.mirea.data.MainClasses.Pet;
import ru.mirea.data.Services.PetService;

import java.util.List;

@Controller
public class PetController {

     @Autowired
     PetService petService;

     @RequestMapping (value = "/pets", method = RequestMethod.GET)
     @ResponseBody
     public List<Pet> pets() { return petService.getPets(); }

     @RequestMapping(value = "/pet/{id}", method = RequestMethod.GET)
     @ResponseBody
     public Pet getPet(@PathVariable int id) { return petService.getPet(id); }

}
