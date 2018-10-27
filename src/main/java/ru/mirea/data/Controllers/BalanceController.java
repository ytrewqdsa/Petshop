package ru.mirea.data.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.mirea.data.Services.BalanceService;

@Controller
public class BalanceController {

    @Autowired
    BalanceService balanceService;

    @RequestMapping (value = "/balance/{id}/{cur}/{bal}", method = RequestMethod.PUT)
    @ResponseBody
    public String putBalance(@PathVariable int id, @PathVariable String cur, @PathVariable long bal){return balanceService.updateBalance(id, cur, bal);}

    @RequestMapping (value = "/balance/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getBalance(@PathVariable int id){return balanceService.getBalanceToCon(id);}

}
