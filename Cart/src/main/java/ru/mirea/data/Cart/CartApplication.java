package ru.mirea.data.Cart;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.web.bind.annotation.*;
import ru.mirea.data.Cart.Interface.ICart;
import ru.mirea.data.Cart.Services.CartService;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

@RestController
@SpringBootApplication
public class CartApplication implements ICart{

    @Autowired
    CartService cartService;

    @RequestMapping(value = "/cart/{id_person}", method = RequestMethod.GET)
    @ResponseBody
    public List<ObjectNode> fullCart(@PathVariable int id_person) throws IOException { return cartService.getFullCart(id_person); }

    /*   @RequestMapping (value = "/cart/{id}", method = RequestMethod.GET)
       @ResponseBody
       public ObjectNode cart(@PathVariable int id) { return sqlConnector.getCart(id); }
   */
    @RequestMapping (value = "/pet/{id}/{id_person}/{quantity}", method = RequestMethod.PUT)
    @ResponseBody
    public String putPet(@PathVariable int id, @PathVariable int id_person, @PathVariable int quantity) { return cartService.addPet(id, id_person, quantity); }

    @RequestMapping (value = "/stuff/{id}/{id_person}/{quantity}", method = RequestMethod.PUT)
    @ResponseBody
    public String putStuff(@PathVariable int id, @PathVariable int id_person, @PathVariable int quantity) { return cartService.addStuff(id, id_person, quantity); }

    @RequestMapping (value = "/cart/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable int id) { return cartService.deleteCart(id); }

    @RequestMapping (value = "/cart/balance/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String pay(@PathVariable int id) { return cartService.payCart(id); }

    @RequestMapping (value = "/balance/{id}/{bal}/{cur}", method = RequestMethod.PUT)
    @ResponseBody
    public String putBalance(@PathVariable int id, @PathVariable long bal, @PathVariable int cur){return cartService.updateBalance(id, bal, cur);}

    @RequestMapping (value = "/balance/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List<ObjectNode> getBalance(@PathVariable int id){return cartService.getBalanceToCon(id);}

    public static void main(String []args){
        SpringApplication.run(CartApplication.class);
    }
}
