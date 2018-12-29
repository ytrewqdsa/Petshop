package ru.mirea.data.Auth.Services;

import org.json.JSONObject;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.sql.*;
import java.util.Base64;

@Component
public class AuthService {

    Connection connection = null;
    String driverName = "org.sqlite.JDBC";
    String CartDB = "jdbc:sqlite://Users/alex/Petshop.db";
    private int id = 1;
    private String header = new JSONObject()
            .put("alg", "HS256")
            .put("typ", "JWT").toString();

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

    private void init(){
        CreateAuthTable();
        CreatePassTable();
    }

    private void CreateAuthTable (){
        String sql = "CREATE TABLE IF NOT EXISTS auth (\n"
                + "	id int PRIMARY KEY,\n"
                + " id_person INTEGER NOT NULL,\n"
                + "	title BIT\n"
                + ");";
        try (Statement statement = connection.createStatement()){
            statement.execute(sql);
            System.out.println("Table auth is created successfully");
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    private void CreatePassTable(){
        String sql = "CREATE TABLE IF NOT EXISTS pass (\n"
                + "	id int PRIMARY KEY,\n"
                + " id_person INTEGER NOT NULL,\n"
                + "	pass VARCHAR\n"
                + ");";
        try (Statement statement = connection.createStatement()){
            statement.execute(sql);
            System.out.println("Table auth is created successfully");
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    public String createUser(int id_person){
        String sql = "INSERT INTO auth VALUES (?, ?, false);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, ++id);
            preparedStatement.setInt(2, id_person);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return "User " + id_person + " has been created";
    }

    public String createPass(int id_person, String pass){
        String sql = "INSERT INTO pass VALUES (?, ?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, ++id);
            preparedStatement.setInt(2, id_person);
            preparedStatement.setString(3, pass);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return "Password for " + id_person + " has been created";
    }

    public String getUser(int id_person){
        String sql = "SELECT * FROM auth WHERE id_person = ?";
        boolean closed = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, id_person);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.isClosed())
                closed = true;
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
            if (closed){
                return "Go to" + " http://localhost:8081/auth/User/YOUR_ID/YOUR_PASSWORD";
            }
            else
                return "User " + id_person + " is exist";
    }

    public String getALL(int id_person){
        String sql = "SELECT a.id_person, a.title, p.pass FROM auth a" +
                " INNER JOIN pass p ON p.id_person = a.id_person AND a.id_person = ?;";
        ResultSet resultSet;
        String result = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, id_person);
            resultSet = preparedStatement.executeQuery();
            result = CreateJSONForALL(resultSet);
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return result;
    }

    public String updateTItle(int id_person, boolean title){
        String sql = "UPDATE auth SET title = ? WHERE id_person = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setBoolean(1, title);
            preparedStatement.setInt(2, id_person);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return "Title for " + id_person + " has been established";
    }

    public boolean check(int id_person) {
        boolean title = false;
        String sql = "SELECT title FROM auth where id_person = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, id_person);
            ResultSet resultSet = preparedStatement.executeQuery();
            title = resultSet.getBoolean(1);
            System.out.println(title);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return title;
    }

    private String Decode(String payload) {
        String eH = Base64.getEncoder().encodeToString(header.getBytes());
        String eP = Base64.getEncoder().encodeToString(payload.getBytes());
        String s = eH + "." + eP;
        return Base64.getEncoder().encodeToString(s.getBytes());
    }

    private String Encode(String sign){
        String eP = Base64.getEncoder().encodeToString(sign.getBytes());
        System.out.println(eP);
        return eP;
    }

    private String CreateJSONForALL(ResultSet resultSet){
        String result = null;
        String title = null;
        try {
            if (!resultSet.getBoolean(2)){
                title = "User";
            }
            else
                title = "Admin";
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        try {
            while (resultSet.next()){
                result = new JSONObject()
                        .put("name", resultSet.getInt(1))
                        .put("title", title)
                        .put("password", resultSet.getString(3)).toString();
            }
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return result;
    }

}
