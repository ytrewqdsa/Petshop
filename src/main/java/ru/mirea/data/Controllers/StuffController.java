package ru.mirea.data.Controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.mirea.data.MainClasses.Stuff;
import ru.mirea.data.Services.StuffService;

import java.util.List;

@Controller
public class StuffController {

    @Autowired
    StuffService stuffService;

    @RequestMapping (value = "/stuffs", method = RequestMethod.GET)
    @ResponseBody
    public List<Stuff> Stuffs() { return stuffService.getStuffs(); }

    @RequestMapping(value = "/stuff/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Stuff getStuff(@PathVariable int id) { return stuffService.getStuff(id); }
}
