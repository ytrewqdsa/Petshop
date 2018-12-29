package ru.mirea.data.Items;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import ru.mirea.data.Items.Services.ItemService;

import java.util.List;

@RestController
@SpringBootApplication
public class ItemApplication {

    @Autowired
    ItemService itemService;

    @RequestMapping(value = "/items", method = RequestMethod.GET)
    @ResponseBody
    public List<ObjectNode> items() {
        return itemService.getItems();
    }

    @RequestMapping (value = "/pets", method = RequestMethod.GET)
    @ResponseBody
    public List<ObjectNode> pets() {
        return itemService.getPets();
    }

    @RequestMapping(value = "/pet/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List<ObjectNode> getPet(@PathVariable int id) { return itemService.getPet(id); }

    @RequestMapping (value = "/stuffs", method = RequestMethod.GET)
    @ResponseBody
    public List<ObjectNode> Stuffs() { return itemService.getStuffs(); }

    @RequestMapping(value = "/pet/{id_person}/{id}/{name}/{price}/{currency}", method = RequestMethod.PUT)
    @ResponseBody
    public String putPet(@PathVariable int id_person, @PathVariable int id, @PathVariable String name,
                         @PathVariable long price, @PathVariable String currency) { return itemService.putPet(id_person, id, name, price, currency); }

    @RequestMapping(value = "/stuff/id_person}/{id}/{name}/{price}/{currency}", method = RequestMethod.PUT)
    @ResponseBody
    public String putStuff(@PathVariable int id_person, @PathVariable int id, @PathVariable String name,
                           @PathVariable long price, @PathVariable String currency) { return itemService.putStuff(id_person, id, name, price, currency); }

    @RequestMapping(value = "/stuff/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List<ObjectNode> getStuff(@PathVariable int id) { return itemService.getStuff(id); }

    public static void main(String []args){
        SpringApplication.run(ItemApplication.class, args);
    }
}
