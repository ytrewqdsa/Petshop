package ru.mirea.data.Controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.mirea.data.SQLConnector;

import java.util.List;

@Controller
public class ItemsController {
    @Autowired
    SQLConnector sqlConnector;


    @RequestMapping(value = "/items", method = RequestMethod.GET)
    @ResponseBody
    public List<ObjectNode> items() { return sqlConnector.getItems(); }


}
