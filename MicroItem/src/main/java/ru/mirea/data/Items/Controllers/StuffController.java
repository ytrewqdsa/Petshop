/*
package ru.mirea.data.Items.Controllers;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.mirea.data.Items.Services.ItemService;
import java.util.List;

@RestController
@RequestMapping("/app")
public class StuffController {

    @Autowired
    ItemService itemService;


    @RequestMapping (value = "/stuffs", method = RequestMethod.GET)
    @ResponseBody
    public List<ObjectNode> Stuffs() { return itemService.getStuffs(); }

    @RequestMapping(value = "/stuff/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List<ObjectNode> getStuff(@PathVariable int id) { return itemService.getStuff(id); }



}
*/
