package ru.mirea.data.Items.Services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ItemService {


    String driverName = "org.sqlite.JDBC";
    Connection connection = null;
 //   String petDB = "jdbc:sqlite://Users/alex/Item.db";
    String petDB = "jdbc:sqlite://Users/alex/Petshop.db";
    volatile int id_cart = 0;
    volatile int id_pet = 0;

    @PostConstruct
    public void connect(){
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            System.out.println("Can't get class. No driver found");
            e.printStackTrace();
            return;
        }
        try {
            connection = DriverManager.getConnection(petDB);
            System.out.println("Connection with itemDB has been established.");
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        init();
//        connector.give(connection);
    }

    public void init(){
        CreateTableOfItems();
    }

    public void CreateTableOfItems(){
        String sql = "CREATE TABLE IF NOT EXISTS item (\n"
                + "	id integer PRIMARY KEY,\n"
                + "type varchar(10) NOT NULL,\n"
                + "	name varchar (50) NOT NULL,\n"
                + "	price INTEGER NOT NULL,\n"
                + "	currency varchar (10) NOT NULL\n"
                + ");";
        try (Statement statement = connection.createStatement()){
            statement.execute(sql);
            System.out.println("Table item is created successfully");
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    //  get methods

    public boolean getDB(){
        return true;
    }

    public List<ObjectNode> getPets(){
        String sql = "SELECT * FROM item WHERE item.type = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, "pet");
            ResultSet resultSet = preparedStatement.executeQuery();
            return CreateJSONForItem(resultSet);
        }catch (SQLException e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public List<ObjectNode> getItems(){
        String sql = "SELECT * FROM item;";
        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            return CreateJSONForItem(resultSet);
        }catch (SQLException e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public List<ObjectNode> getStuffs(){
        String sql = "SELECT * FROM item WHERE item.type = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, "stuff");
            ResultSet resultSet = preparedStatement.executeQuery();
            return CreateJSONForItem(resultSet);
        }catch (SQLException e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public List<ObjectNode> getPet(int id){
        String sql = "SELECT * FROM item WHERE type = ? AND id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, "pet");
            preparedStatement.setInt(2, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return CreateJSONForItem(resultSet);
        }catch (SQLException e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public List<ObjectNode> getStuff(int id){
        String sql = "SELECT * FROM item WHERE type = ? AND id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, "stuff");
            preparedStatement.setInt(2, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return CreateJSONForItem(resultSet);
        }catch (SQLException e){
            System.err.println(e.getMessage());
            return null;
        }
    }


    public String putPet(int id_person, int id, String name, long price, String currency){
        RestTemplate restTemplate = new RestTemplate();
        String sql;
        String s = "";
        String getIUrl = "http://localhost:8081/auth/title/" + id_person;
        ResponseEntity<String> response = restTemplate.getForEntity(getIUrl, String.class);
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode root = mapper.readTree(response.getBody());
            s = root.toString();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        Boolean title = Boolean.valueOf(s);
        if(title) {
            try {
                sql = "INSERT INTO item VALUES(?, ?, ?, ?, ?);";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setInt(1, id);
                    preparedStatement.setString(2, "pet");
                    preparedStatement.setString(3, name);
                    preparedStatement.setLong(4, price);
                    preparedStatement.setString(5, currency);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }
            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
            }
            return name + " has been successfully added";
        }
        else{
            return "Go to" + " http://localhost:8084/auth/User/YOURID/YOURPASSWORD";
        }
    }

    public String putStuff(int id_person, int id, String name, long price, String currency){
        RestTemplate restTemplate = new RestTemplate();
        String sql;
        String s = "";
        String getIUrl = "http://localhost:8081/auth/title/" + id_person;
        ResponseEntity<String> response = restTemplate.getForEntity(getIUrl, String.class);
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode root = mapper.readTree(response.getBody());
            s = root.toString();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        Boolean title = Boolean.valueOf(s);
        if(title) {
            try {
                sql = "INSERT INTO item VALUES(?, ?, ?, ?, ?);";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setInt(1, id);
                    preparedStatement.setString(2, "stuff");
                    preparedStatement.setString(3, name);
                    preparedStatement.setLong(4, price);
                    preparedStatement.setString(5, currency);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }
            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
            }
            return name + " has been successfully added";
        }
        else{
            return "Go to" + " http://localhost:8084/auth/User/YOURID/YOURPASSWORD";
        }
    }

    public List<ObjectNode> CreateJSONForItem(ResultSet resultSet){
        ObjectMapper mapper = new ObjectMapper();
        //       ArrayNode itemsArray = mapper.createArrayNode();
        List<ObjectNode> items = new ArrayList<>();
        try {
            while (resultSet.next()){
                ObjectNode item = mapper.createObjectNode();
                item.put("id", resultSet.getInt("id"));
                item.put("type", resultSet.getString("type"));
                item.put("name", resultSet.getString("name"));
                item.put("price", resultSet.getLong("price"));
                item.put("currency", resultSet.getString("currency"));
                //              itemsArray.add(item);
                items.add(item);
            }
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }

 /*       ObjectNode objectNode = mapper.createObjectNode();
        objectNode.putPOJO("item", itemsArray);*/
        return items;
//        return objectNode;
    }

}
