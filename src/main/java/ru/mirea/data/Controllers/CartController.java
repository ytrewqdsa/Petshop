package ru.mirea.data.Controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.mirea.data.SQLConnector;
import ru.mirea.data.Services.CartService;

import java.util.List;

@Controller
public class CartController {

    @Autowired
    CartService cartService;
    @Autowired
    SQLConnector sqlConnector;

    @RequestMapping (value = "/cart/{id_person}", method = RequestMethod.GET)
    @ResponseBody
    public List<ObjectNode> fullCart(@PathVariable int id_person) { return sqlConnector.getFullCart(id_person); }

 /*   @RequestMapping (value = "/cart/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ObjectNode cart(@PathVariable int id) { return sqlConnector.getCart(id); }
*/
    @RequestMapping (value = "/pet/{id}/{id_person}/{quantity}", method = RequestMethod.PUT)
    @ResponseBody
    public String putPet(@PathVariable int id, @PathVariable int id_person, @PathVariable int quantity) {
        return sqlConnector.addPet(id, id_person, quantity); }

    @RequestMapping (value = "/stuff/{id}/{id_person}/{quantity}", method = RequestMethod.PUT)
    @ResponseBody
    public String putStuff(@PathVariable int id, @PathVariable int id_person, @PathVariable int quantity) { return sqlConnector.addStuff(id, id_person, quantity); }

    @RequestMapping (value = "/cart/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable int id) { return sqlConnector.deleteCart(id); }

    @RequestMapping (value = "/cart/balance/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String pay(@PathVariable int id) { return sqlConnector.payCart(id); }

}
