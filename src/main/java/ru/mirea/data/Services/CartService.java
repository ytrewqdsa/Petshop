package ru.mirea.data.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mirea.data.MainClasses.Item;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CartService {

    @Autowired
    private PetService petService;
    @Autowired
    private StuffService stuffService;
    @Autowired
    private BalanceService balanceService;

    private HashMap<Integer, Item> cart = new HashMap<>();

    public String deleteCart(int value){
        cart.entrySet().removeIf(e -> e.getKey().equals(value));
        return "Item has been successfully removed from cart";
    }

    public List<Item> getFullCart(){
        return new ArrayList<>(cart.values());
    }

    public String addStuff(int value){
        cart.put(value, stuffService.getMap().get(value));
        return cart.get(value).getName() + " has been successfully added to cart";
    }

    public String addPet(int value){
        cart.put(value, petService.getMap().get(value));
        return cart.get(value).getName() + " has been successfully added to cart";
    }

    public String getCart(int value){
        return cart.get(value).getName();
    }

    public HashMap<Integer, Item> getCart() {
        return cart;
    }

    public String payCart(int value){
        long sum = 0;
        for (Map.Entry<Integer, Item> entry : cart.entrySet()){
            switch (entry.getValue().getCurrency()) {
                case "Rubles":
                    sum += entry.getValue().getPrice();
                    break;
                case "Euro":
                    sum += entry.getValue().getPrice() * balanceService.currencyService.currencies.get(0).getCurToEUR();
                    break;
                case "USD":
                    sum += entry.getValue().getPrice() * balanceService.currencyService.currencies.get(0).getCurToUSD();
                    break;
            }
        }
        long balance = balanceService.getBalance(value);
        if (balanceService.getPerson(value).getCurr().getType().equals("Euro"))
            balance *= balanceService.currencyService.currencies.get(0).getCurToEUR();
        else if (balanceService.getPerson(value).getCurr().getType().equals("USD"))
            balance *= balanceService.currencyService.currencies.get(0).getCurToUSD();
        if(balance >= sum){
            balance -= sum;
            cart.clear();
            if (balanceService.getPerson(value).getCurr().getType().equals("Euro"))
                balance /= balanceService.currencyService.currencies.get(0).getCurToEUR();
            else if (balanceService.getPerson(value).getCurr().getType().equals("USD"))
                balance /= balanceService.currencyService.currencies.get(0).getCurToUSD();
            balanceService.getPerson(value).setBalance(balance);
            return "You have successfully paid the cart. Your actual balance is: " + balance;
        }
        else
            return "Your balance is too low to complete transaction";
    }

}
