package ru.mirea.data.Services;
import org.springframework.stereotype.Component;
import ru.mirea.data.MainClasses.Stuff;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class StuffService {

    private HashMap<Integer, Stuff> stuffs = new HashMap<>();
    private List<Stuff> allstuff;

    public List<Stuff> getStuffs(){
        allstuff = new ArrayList<>(stuffs.values());
        return allstuff;
    }

    public Stuff getStuff(int value){
        return stuffs.get(value);
    }

    HashMap<Integer, Stuff> getMap(){
        return stuffs;
    }

    public String putStuff(int id, String name, long price, String currency){
        stuffs.put(id, new Stuff(id, name, price, currency));
        return stuffs.get(id).getName() + "has been successfully added";
    }

    @PostConstruct
    private void init(){
        stuffs.put(20, new Stuff(20,"shovel", 1500,"Rubles"));
        stuffs.put(21, new Stuff(21,"church", 2000,"Rubles"));
        stuffs.put(22, new Stuff(22,"knife", 150,"Rubles"));
        stuffs.put(23, new Stuff(23,"spoon", 5,"Rubles"));
        stuffs.put(24, new Stuff(24,"scratching-stone", 300,"Rubles"));
    }
}
