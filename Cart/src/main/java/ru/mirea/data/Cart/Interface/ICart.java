package ru.mirea.data.Cart.Interface;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.List;

public interface ICart {
    public List<ObjectNode> fullCart(@PathVariable int id_person) throws IOException;
    public String putPet(@PathVariable int id, @PathVariable int id_person, @PathVariable int quantity);
    public String putStuff(@PathVariable int id, @PathVariable int id_person, @PathVariable int quantity);
    public String delete(@PathVariable int id);
    public String pay(@PathVariable int id);
    public List<ObjectNode> getBalance(@PathVariable int id);
}
