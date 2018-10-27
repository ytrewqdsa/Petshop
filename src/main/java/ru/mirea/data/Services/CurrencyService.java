package ru.mirea.data.Services;

import org.springframework.stereotype.Component;
import ru.mirea.data.MainClasses.Currency;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CurrencyService {

    List<Currency> currencies = new ArrayList<>();

    @PostConstruct
    public void init(){
        currencies.add(0, new Currency("Rubles", 700, 600, 10));
        currencies.add(1, new Currency("Euro", 10, 11,1));
        currencies.add(2, new Currency("USD", 8, 10, 1));
    }

    public Currency getCurrency(String cur){
        Currency currency = null;
        for (Iterator<Currency> iterator = currencies.iterator(); iterator.hasNext(); ){
            Currency elem = iterator.next();
            if (elem.getType().equals(cur))
                currency = elem;
        }
        return currency;
    }

}
