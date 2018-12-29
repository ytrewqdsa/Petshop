package ru.mirea.data.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import ru.mirea.data.Auth.Services.AuthService;

import java.sql.ResultSet;

@RestController
@SpringBootApplication
public class AuthApplication {

    @Autowired
    AuthService authService;

    @RequestMapping(value = "/User/{id_person}", method = RequestMethod.PUT)
    @ResponseBody
    public String createUser(@PathVariable int id_person)  {
        return authService.createUser(id_person);
    }

    @RequestMapping(value = "/User/{id_person}/{password}", method = RequestMethod.PUT)
    @ResponseBody
    public String createPass(@PathVariable int id_person, @PathVariable String password)  {
        return authService.createPass(id_person, password);
    }

    @RequestMapping(value = "/User/{id_person}", method = RequestMethod.GET)
    @ResponseBody
    public String getUser(@PathVariable int id_person)  {
        return authService.getUser(id_person);
    }

    @RequestMapping(value = "/title/{id_person}/{title}", method = RequestMethod.PUT)
    @ResponseBody
    public String UpdateTitle(@PathVariable int id_person, @PathVariable boolean title)  {
        return authService.updateTItle(id_person, title);
    }

    @RequestMapping(value = "/auth/{id_person}", method = RequestMethod.GET)
    @ResponseBody
    public String getALL(@PathVariable int id_person) {
        return authService.getALL(id_person);
    }

    @RequestMapping(value = "/title/{id_person}", method = RequestMethod.GET)
    @ResponseBody
    public Boolean titleCH(@PathVariable int id_person)  {
        return authService.check(id_person);
    }

    public static void main(String []args){
        SpringApplication.run(AuthApplication.class, args);
    }
}
