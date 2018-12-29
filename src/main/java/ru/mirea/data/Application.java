package ru.mirea.data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;


import java.io.IOException;

@SpringBootApplication
@EnableZuulProxy
public class Application {
/*
    @Autowired
    ItemService itemService;
    @Autowired
    CartService cartService;*/

    public static void main(String []args) throws IOException{
        SpringApplication.run(Application.class);
    }


/*

    public String getPetDB(){
        return "8083";
    }

    public String getCartDB(){
        return cartService.getDB();
    }

*/


}
