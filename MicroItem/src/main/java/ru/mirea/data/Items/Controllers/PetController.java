//package ru.mirea.data.Items.Controllers;
//import ru.mirea.data.Items.Services.ItemService;
//import com.fasterxml.jackson.databind.node.ObjectNode;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import java.util.List;
//
//@RestController
//@RequestMapping("/app")
//public class PetController {
//
//     @Autowired
//     ItemService itemService;
//
//     @RequestMapping (value = "/pets", method = RequestMethod.GET)
//     @ResponseBody
//     public List<ObjectNode> pets() { return itemService.getPets(); }
//
//
//     @RequestMapping(value = "/pet/{id}", method = RequestMethod.GET)
//     @ResponseBody
//     public List<ObjectNode> getPet(@PathVariable int id) { return itemService.getPet(id); }
//
//}
