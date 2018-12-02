package ru.mirea.data.Controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.mirea.data.SQLConnector;
import ru.mirea.data.Services.BalanceService;

import java.util.List;

@Controller
public class BalanceController {

    @Autowired
    BalanceService balanceService;

    @Autowired
    SQLConnector sqlConnector;

    @RequestMapping (value = "/balance/{id}/{bal}/{cur}", method = RequestMethod.PUT)
    @ResponseBody
    public String putBalance(@PathVariable int id, @PathVariable long bal, @PathVariable int cur){
        return sqlConnector.updateBalance(id, bal, cur);
    }

    @RequestMapping (value = "/balance/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List<ObjectNode> getBalance(@PathVariable int id){
        return sqlConnector.getBalanceToCon(id);
    }

}
