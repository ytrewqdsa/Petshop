/*
package ru.mirea.data.Cart.Controllers;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.mirea.data.Cart.Services.CartService;

import java.util.List;

@RestController
public class CartController {

    @Autowired
    CartService cartService;

    @RequestMapping (value = "/cart/{id_person}", method = RequestMethod.GET)
    @ResponseBody
    public List<ObjectNode> fullCart(@PathVariable int id_person) { return cartService.getFullCart(id_person); }

 */
/*   @RequestMapping (value = "/cart/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ObjectNode cart(@PathVariable int id) { return sqlConnector.getCart(id); }
*//*

    @RequestMapping (value = "/pet/{id}/{id_person}/{quantity}", method = RequestMethod.PUT)
    @ResponseBody
    public String putPet(@PathVariable int id, @PathVariable int id_person, @PathVariable int quantity) { return cartService.addPet(id, id_person, quantity); }

    @RequestMapping (value = "/stuff/{id}/{id_person}/{quantity}", method = RequestMethod.PUT)
    @ResponseBody
    public String putStuff(@PathVariable int id, @PathVariable int id_person, @PathVariable int quantity) { return cartService.addStuff(id, id_person, quantity); }

    @RequestMapping(value = "/pet/{id}/{name}/{price}/{currency}", method = RequestMethod.PUT)
    @ResponseBody
    public String putPet(@PathVariable int id, @PathVariable String name,
                         @PathVariable long price, @PathVariable String currency) { return cartService.putPet(id, name, price, currency); }

    @RequestMapping(value = "/stuff/{id}/{name}/{price}/{currency}", method = RequestMethod.PUT)
    @ResponseBody
    public String putStuff(@PathVariable int id, @PathVariable String name,
                           @PathVariable long price, @PathVariable String currency) { return cartService.putStuff(id, name, price, currency); }

    @RequestMapping (value = "/cart/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable int id) { return cartService.deleteCart(id); }

    @RequestMapping (value = "/cart/balance/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String pay(@PathVariable int id) { return cartService.payCart(id); }

}
*/
