package SQL;

import model.UserProfile;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataBaseHandler {

    private Connection connection;
    private static final String user = "root";
    private static final String password = "123";
    private static final String url = "jdbc:mysql://localhost:3306/users";
    public DataBaseHandler(){
        try {
            Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
            DriverManager.registerDriver(driver);
            this.connection = DriverManager.getConnection(url, user, password);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public List<UserProfile> getAllUsers(){
        try(Statement statement = this.connection.createStatement()){
            ArrayList<UserProfile> users = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM testtable");
            while (resultSet.next()){
                users.add(new UserProfile(resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(3)));
                        resultSet.getString(4);
            }
            if (users.isEmpty())
                return Collections.emptyList();
            return users;
        } catch (SQLException e){
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public void addUser(UserProfile user){
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO testtable(login, password, email) VALUES(?,?,?)")){
            statement.setObject(1, user.getLogin());
            statement.setObject(2, user.getPassword());
            statement.setObject(3, user.getEmail());
            statement.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
