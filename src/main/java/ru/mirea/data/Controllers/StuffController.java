package ru.mirea.data.Controllers;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.mirea.data.SQLConnector;
import ru.mirea.data.Services.StuffService;

import java.util.List;

@Controller
public class StuffController {

    @Autowired
    StuffService stuffService;
    @Autowired
    SQLConnector sqlConnector;


    @RequestMapping (value = "/stuffs", method = RequestMethod.GET)
    @ResponseBody
    public List<ObjectNode> Stuffs() { return sqlConnector.getStuffs(); }

    @RequestMapping(value = "/stuff/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List<ObjectNode> getStuff(@PathVariable int id) { return sqlConnector.getStuff(id); }


    @RequestMapping(value = "/stuff/{id}/{name}/{price}/{currency}", method = RequestMethod.PUT)
    @ResponseBody
    public String putStuff(@PathVariable int id, @PathVariable String name,
                         @PathVariable long price, @PathVariable String currency) { return sqlConnector.putStuff(id, name, price, currency); }
}
