package ru.mirea.data.Controllers;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.mirea.data.SQLConnector;
import ru.mirea.data.Services.PetService;

import java.util.List;

@Controller
public class PetController {

     @Autowired
     PetService petService;
     @Autowired
     SQLConnector sqlConnector;

     @RequestMapping (value = "/pets", method = RequestMethod.GET)
     @ResponseBody
     public List<ObjectNode> pets() { return sqlConnector.getPets(); }


     @RequestMapping(value = "/pet/{id}", method = RequestMethod.GET)
     @ResponseBody
     public List<ObjectNode> getPet(@PathVariable int id) { return sqlConnector.getPet(id); }


     @RequestMapping(value = "/pet/{id}/{name}/{price}/{currency}", method = RequestMethod.PUT)
     @ResponseBody
     public String putPet(@PathVariable int id, @PathVariable String name,
                          @PathVariable long price, @PathVariable String currency) {
          return sqlConnector.putPet(id, name, price, currency);
     }

}
