package ru.mirea.data.Services;
import org.springframework.stereotype.Component;
import ru.mirea.data.MainClasses.Pet;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class PetService {

    private HashMap<Integer, Pet> pets = new HashMap<>();
    private List<Pet> allpets;

    public List<Pet> getPets(){
        allpets = new ArrayList<>(pets.values());
        return allpets;
    }

    public Pet getPet(int value){
        return pets.get(value);
    }

    HashMap<Integer, Pet> getMap(){
        return pets;
    }

    public String putPet(int id, String name, long price, String currency){
        pets.put(id, new Pet(id, name, price, currency));
        return pets.get(id).getName() + "has been successfully added";
    }

    @PostConstruct
    private void init(){
        pets.put(1, new Pet(1,"cat",15000, "Rubles"));
        pets.put(2, new Pet(2,"dog",20000, "Rubles"));
        pets.put(3, new Pet(3,"carrot", 300,"Rubles"));
        pets.put(4, new Pet(4,"chihuahua", 40000,"Rubles"));
        pets.put(5, new Pet(5,"fish", 10000,"Rubles"));
        pets.put(6, new Pet(6, "crocodile", 50000, "Euro"));
    }

}
