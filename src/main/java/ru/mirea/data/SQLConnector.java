package ru.mirea.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//@Component
public class SQLConnector {
//Тут был Дима:3
  //  Connector connector;

    String driverName = "org.sqlite.JDBC";
    Connection connection = null;
    String petDB = "jdbc:sqlite://Users/alex/Petshop.db";
    volatile int id_cart = 0;
    volatile int id_pet = 0;

/*
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
            System.out.println("Connection has been established.");
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        init();
//        connector.give(connection);
    }

    public void init(){
        CreateTableOfItems();
        CreateTableOfBalance();
        CreateTableOfCart();
    }

//  create Methods

    public void CreateTableOfItems(){
//        Connection connection = connector.get();
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
 //       connector.give(connection);
    }

    public void CreateTableOfBalance(){
//        Connection connection = connector.get();
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
//        connector.give(connection);
    }

    public void CreateTableOfCart(){
//        Connection connection = connector.get();
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
//        connector.give(connection);
    }
*/

//  get methods
/*
    public List<ObjectNode> getPets(){
        String sql = "SELECT * FROM item WHERE item.type = ?;";
//        Connection connection = connector.get();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, "pet");
            ResultSet resultSet = preparedStatement.executeQuery();
//            connector.give(connection);
            return CreateJSONForItem(resultSet);
        }catch (SQLException e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public List<ObjectNode> getItems(){
        String sql = "SELECT * FROM item;";
//        Connection connection = connector.get();
        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
//            connector.give(connection);
            return CreateJSONForItem(resultSet);
        }catch (SQLException e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public List<ObjectNode> getStuffs(){
//        Connection connection = connector.get();
        String sql = "SELECT * FROM item WHERE item.type = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, "stuff");
            ResultSet resultSet = preparedStatement.executeQuery();
//            connector.give(connection);
            return CreateJSONForItem(resultSet);
        }catch (SQLException e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public List<ObjectNode> getPet(int id){
//        Connection connection = connector.get();
        String sql = "SELECT * FROM item WHERE type = ? AND id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, "pet");
            preparedStatement.setInt(2, id);
            ResultSet resultSet = preparedStatement.executeQuery();
//            connector.give(connection);
            return CreateJSONForItem(resultSet);
        }catch (SQLException e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public List<ObjectNode> getStuff(int id){
//        Connection connection = connector.get();
        String sql = "SELECT * FROM item WHERE type = ? AND id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, "stuff");
            preparedStatement.setInt(2, id);
            ResultSet resultSet = preparedStatement.executeQuery();
//            connector.give(connection);
            return CreateJSONForItem(resultSet);
        }catch (SQLException e){
            System.err.println(e.getMessage());
            return null;
        }
    }*/
/*

    public List<ObjectNode> getFullCart(int id_person){
//        Connection connection = connector.get();
        String sql = "SELECT i.name, i.price, ca.quantity FROM item i " +
                "INNER JOIN cart ca ON i.id = ca.id_item AND ca.id_person = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, id_person);
            ResultSet resultSet = preparedStatement.executeQuery();
//            connector.give(connection);
            return CreateJSONForFullCart(resultSet);
        }catch (SQLException e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public List<ObjectNode> getCart(int id){
//        Connection connection = connector.get();
        String sql = "SELECT * FROM cart WHERE id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
//            connector.give(connection);
            return CreateJSONForCart(resultSet);
        }catch (SQLException e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public List<ObjectNode> getBalanceToCon(int id){
//        Connection connection = connector.get();
        String sql = "SELECT * FROM balance WHERE id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
//            connector.give(connection);
            return CreateJSONForBalance(resultSet);
        }catch (SQLException e){
            System.err.println(e.getMessage());
            return null;
        }
    }
*/

/*
//  put Methods

    public String putPet(int id, String name, long price, String currency){
//        Connection connection = connector.get();
        String sql = "INSERT INTO item VALUES(?, ?, ?, ?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, "pet");
            preparedStatement.setString(3, name);
            preparedStatement.setLong(4, price);
            preparedStatement.setString(5, currency);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
//        connector.give(connection);
        return name + " has been successfully added";
    }

    public String putStuff(int id, String name, long price, String currency){
//        Connection connection = connector.get();
        String sql = "INSERT INTO item VALUES(?, ?, ?, ?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, "stuff");
            preparedStatement.setString(3, name);
            preparedStatement.setLong(4, price);
            preparedStatement.setString(5, currency);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
//        connector.give(connection);
        return name + " has been successfully added";
    }

    public String addPet(int id_pet, int id_person, int quantity){
//        Connection connection = connector.get();
        String sql = "INSERT INTO cart VALUES (?, ?, ?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, id_cart++);
            preparedStatement.setInt(2, id_pet);
            preparedStatement.setInt(3, id_person);
            preparedStatement.setInt(4, quantity);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
 //       connector.give(connection);
        return "Pet has been successfully added to cart";
    }

    public String addStuff(int id_stuff, int id_person, int quantity){
//        Connection connection = connector.get();
        String sql = "INSERT INTO cart VALUES (?, ?, ?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, id_cart++);
            preparedStatement.setInt(2, id_stuff);
            preparedStatement.setInt(3, id_person);
            preparedStatement.setInt(4, quantity);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
//        connector.give(connection);
        return "Stuff has been successfully added to cart";
    }
*/

// update Method
/*

    public int updateBalanc(int id, long balance, int id_cur){
//        Connection connection = connector.get();
        String sql = "UPDATE balance SET bal = ? WHERE id = ? AND id_cur = ?;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1 , balance);
            preparedStatement.setInt(2, id);
            preparedStatement.setInt(3, id_cur);
            preparedStatement.executeUpdate();
//            connector.give(connection);
            return 1;
        }catch (SQLException e){
            System.err.println(e.getMessage());
            return -1;
        }
    }

    public String updateBalance(int id, long balance, int id_cur){
//        Connection connection = connector.get();
        String sql = "INSERT INTO balance VALUES(?, ?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(2, balance);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(3, id_cur);
            preparedStatement.executeUpdate();
//            connector.give(connection);
            return "Balance has ";
        }catch (SQLException e){
            System.err.println(e.getMessage());
            return null;
        }
    }
*/

//  pay Method
/*

    public String payCart(int id_person){
//        Connection connection = connector.get();
        long result = 0;
        String s = null;
        String sql = "SELECT bal.bal - SUM(i.price * ca.quantity) FROM cart ca " +
                "INNER JOIN item i ON i.id = ca.id_item AND ca.id_person = ? " +
                "LEFT JOIN balance bal ON bal.id = ca.id_person " +
                "GROUP BY ca.id_person;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, id_person);
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
//        connector.give(connection);
        return s;
    }
*/

//  delete Methods
/*

    public String deleteCart(int id_person){
///        Connection connection = connector.get();
        String sql = "DELETE FROM cart WHERE id_person = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, id_person);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return "Item has been successfully removed";
    }
*/

// createJSON Methods
/*
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

 *//*       ObjectNode objectNode = mapper.createObjectNode();
        objectNode.putPOJO("item", itemsArray);*//*
        return items;
//        return objectNode;
    }*/
/*

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
*/
/*        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.putPOJO("balance", itemsArray);
        return objectNode;*//*

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
*/
/*        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.putPOJO("fullcart", itemsArray);
        return objectNode;*//*

    }
*/

}
