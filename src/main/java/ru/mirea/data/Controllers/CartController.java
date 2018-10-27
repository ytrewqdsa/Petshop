package ru.mirea.data.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.mirea.data.MainClasses.Item;
import ru.mirea.data.Services.CartService;

import java.util.List;

@Controller
public class CartController {

    @Autowired
    CartService cartService;

    @RequestMapping (value = "/cart", method = RequestMethod.GET)
    @ResponseBody
    public List<Item> fullCart() { return cartService.getFullCart(); }

    @RequestMapping (value = "/cart/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String cart(@PathVariable int id) { return cartService.getCart(id); }

    @RequestMapping (value = "/pet/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public String putPet(@PathVariable int id) { return cartService.addPet(id); }

    @RequestMapping (value = "/stuff/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public String putStuff(@PathVariable int id) { return cartService.addStuff(id); }

    @RequestMapping (value = "/cart/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deletePet(@PathVariable int id) { return cartService.deleteCart(id); }

    @RequestMapping (value = "/cart/balance/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String pay(@PathVariable int id) { return cartService.payCart(id); }

}
