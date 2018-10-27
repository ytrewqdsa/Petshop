package ru.mirea.data.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mirea.data.MainClasses.Balance;
import java.util.HashMap;
import java.util.Map;

@Component
public class BalanceService {

    @Autowired
    CurrencyService currencyService;

    private Map<Integer, Balance> people = new HashMap<>();

    public String updateBalance(int id, String cur, long bal){
        int fl = 0;
        for (Map.Entry<Integer, Balance> entry : people.entrySet()){
            if (id == entry.getValue().getId()){
                getPerson(id).setBalance(entry.getValue().getBalance()+bal);
                fl = 1;
            }
        }
        if (fl == 0)
            people.put(id, new Balance(id, bal, currencyService.getCurrency(cur)));
        return "Your balance has been recharged";
    }

    public String getBalanceToCon(int value){
        return "Balance of " + people.get(value).getId() + " is " + people.get(value).getBalance() + " " + people.get(value).getCurr().getType();
    }

    public long getBalance(int value){
        return people.get(value).getBalance();
    }

    public Balance getPerson (int value){
        return people.get(value);
    }

}