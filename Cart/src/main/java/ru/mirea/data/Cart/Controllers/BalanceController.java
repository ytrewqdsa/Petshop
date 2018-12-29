/*
package ru.mirea.data.Cart.Controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.mirea.data.Cart.Services.CartService;

import java.util.List;

@RestController
public class BalanceController {

    @Autowired
    CartService cartService;

    @RequestMapping (value = "/balance/{id}/{bal}/{cur}", method = RequestMethod.PUT)
    @ResponseBody
    public String putBalance(@PathVariable int id, @PathVariable long bal, @PathVariable int cur){return cartService.updateBalance(id, bal, cur);}

    @RequestMapping (value = "/balance/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List<ObjectNode> getBalance(@PathVariable int id){return cartService.getBalanceToCon(id);}

}
*/
