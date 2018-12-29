package ru.mirea.data.Cart.Services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
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
public class CartService {

    Connection connection = null;
    String driverName = "org.sqlite.JDBC";
    String CartDB = "jdbc:sqlite://Users/alex/Petshop.db";
    volatile int id_cart = 1;
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
            connection = DriverManager.getConnection(CartDB);
            System.out.println("Connection with cartDB has been established.");
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }

        init();
    }


    private void getItems() throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        String getIUrl = "http://localhost:8081/item/items";
        ResponseEntity<String> response = restTemplate.getForEntity(getIUrl, String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        JsonNode name = root.get(0);
        System.out.println(name);
    }

    private void init(){
        CreateTableOfBalance();
        CreateTableOfCart();
    /*    List<ObjectNode> objectNode = itemApplication.items();
        for (int i = 0; i < objectNode.size(); i++){
            System.out.println(objectNode.get(i));
        }*/
    }

//  create Methods
    private void CreateTableOfBalance(){
        String sql = "CREATE TABLE IF NOT EXISTS balance (\n"
                + "	id int PRIMARY KEY,\n"
                + "	bal INTEGER,\n"
                + "	id_cur int NOT NULL\n"
                + ");";
        try (Statement statement = connection.createStatement()){
            statement.execute(sql);
            System.out.println("Table balance is created successfully");
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    private void CreateTableOfCart(){
        String sql = "CREATE TABLE IF NOT EXISTS cart (\n"
                + "	id int PRIMARY KEY,\n"
                + "	id_item int NOT NULL,\n"
                + "	id_person int NOT NULL,\n"
                + "	quantity int NOT NULL\n"
                + ");";
        try (Statement statement = connection.createStatement()){
            statement.execute(sql);
            System.out.println("Table cart is created successfully");
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

//  get methods

    public List<ObjectNode> getFullCart(int id_person) throws IOException {
//        getItems();
        String r = "SELECT id_item FROM cart ca WHERE ca.id_person = ?";
        String sql = "SELECT i.name, i.price, ca.quantity FROM item i " +
                "INNER JOIN cart ca ON i.id = ca.id_item AND ca.id_person = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, id_person);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println(resultSet.getInt(1) + resultSet.getString(1));
            resultSet.getString(1);
            return CreateJSONForFullCart(resultSet);
        }catch (SQLException e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public List<ObjectNode> getCart(int id){
        String sql = "SELECT * FROM cart WHERE id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return CreateJSONForCart(resultSet);
        }catch (SQLException e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public List<ObjectNode> getBalanceToCon(int id){
        String sql = "SELECT * FROM balance WHERE id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return CreateJSONForBalance(resultSet);
        }catch (SQLException e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    //  put Methods

    public String addPet(int id_pet, int id_person, int quantity){
        String sql = "INSERT INTO cart VALUES (?, ?, ?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, ++id_cart);
            preparedStatement.setInt(2, id_pet);
            preparedStatement.setInt(3, id_person);
            preparedStatement.setInt(4, quantity);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return "Pet has been successfully added to cart";
    }

    public String addStuff(int id_stuff, int id_person, int quantity){
        String sql = "INSERT INTO cart VALUES (?, ?, ?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, ++id_cart);
            preparedStatement.setInt(2, id_stuff);
            preparedStatement.setInt(3, id_person);
            preparedStatement.setInt(4, quantity);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return "Stuff has been successfully added to cart";
    }

    public int updateBalanc(int id, long balance, int id_cur){
        String sql = "UPDATE balance SET bal = ? WHERE id = ? AND id_cur = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1 , balance);
            preparedStatement.setInt(2, id);
            preparedStatement.setInt(3, id_cur);
            preparedStatement.executeUpdate();
            return 1;
        }catch (SQLException e){
            System.err.println(e.getMessage());
            return -1;
        }
    }

    public String updateBalance(int id, long balance, int id_cur){
        String sql = "INSERT INTO balance VALUES(?, ?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(2, balance);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(3, id_cur);
            preparedStatement.executeUpdate();
            return "Balance has ";
        }catch (SQLException e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public String payCart(int id_person){
        long result = 0;
        /*String itemDB = application.getPetDB()*/;
        String s = null;
        String sql = "SELECT bal.bal - SUM(i.price * ca.quantity) FROM cart ca " +
                "INNER JOIN ? i ON i.id = ca.id_item AND ca.id_person = ? " +
                "LEFT JOIN balance bal ON bal.id = ca.id_person " +
                "GROUP BY ca.id_person;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, " Item.item ");
            preparedStatement.setInt(2, id_person);
            ResultSet resultSet = preparedStatement.executeQuery();
            result = resultSet.getInt(1);
            if (result >= 0) {
                deleteCart(id_person);
                updateBalanc(id_person, result, 1);
                s = "Cart has been successfully paid";
            }
            else
                s =  "You cannot afford this purchase";
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return s;
    }

    public String deleteCart(int id_person){
        String sql = "DELETE FROM cart WHERE id_person = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, id_person);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return "Item has been successfully removed";
    }

    public List<ObjectNode> CreateJSONForBalance(ResultSet resultSet){
        ObjectMapper mapper = new ObjectMapper();
//        ArrayNode itemsArray = mapper.createArrayNode();
        List<ObjectNode> items = new ArrayList<>();
        try {
            while (resultSet.next()){
                ObjectNode item = mapper.createObjectNode();
                item.put("id", resultSet.getInt("id"));
                item.put("bal", resultSet.getLong("bal"));
                item.put("id_cur", resultSet.getInt("id_cur"));
//                itemsArray.add(item);
                items.add(item);
            }
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return items;
/*        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.putPOJO("balance", itemsArray);
        return objectNode;*/
    }

    public List<ObjectNode> CreateJSONForCart(ResultSet resultSet){
        ObjectMapper mapper = new ObjectMapper();
        //    ArrayNode itemsArray = mapper.createArrayNode();
        List<ObjectNode> items = new ArrayList<>();
        try {
            while (resultSet.next()){
                ObjectNode item = mapper.createObjectNode();
                item.put("id", resultSet.getInt("id"));
                item.put("id_item", resultSet.getInt("id_item"));
                item.put("id_person", resultSet.getInt("id_person"));
                item.put("quantity", resultSet.getInt("quantity"));
                //          itemsArray.add(item);
                items.add(item);
            }
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return items;
//        ObjectNode objectNode = mapper.createObjectNode();
//        objectNode.putPOJO("cart", itemsArray);
//        return objectNode;
    }

    public List<ObjectNode> CreateJSONForFullCart(ResultSet resultSet){
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode itemsArray = mapper.createArrayNode();
        List<ObjectNode> items = new ArrayList<>();
        try {
            while (resultSet.next()){
                ObjectNode item = mapper.createObjectNode();
                item.put("name", resultSet.getString("name"));
                item.put("price", resultSet.getLong("price"));
                item.put("quantity", resultSet.getInt("quantity"));
//                itemsArray.add(items);
                items.add(item);
            }
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return items;
/*        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.putPOJO("fullcart", itemsArray);
        return objectNode;*/
    }

}
