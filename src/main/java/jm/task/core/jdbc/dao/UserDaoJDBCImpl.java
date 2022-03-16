package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.*;

public class UserDaoJDBCImpl implements UserDao {
    private Util util = new Util();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (PreparedStatement preparedStatement = util.getConnection()
                .prepareStatement("CREATE TABLE users (id INT AUTO_INCREMENT primary key," +
                        " NAME varchar(255),LASTNAME varchar(255), AGE INT )");
             PreparedStatement commit = util.getConnection().prepareStatement("COMMIT")) {
            preparedStatement.execute();
            commit.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (PreparedStatement preparedStatement = util.getConnection()
                .prepareStatement("DROP TABLE if exists users");
             PreparedStatement commit = util.getConnection().prepareStatement("COMMIT")) {
            preparedStatement.execute();
            commit.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try ( PreparedStatement preparedStatement = util
                .getConnection().prepareStatement("INSERT INTO users (NAME, LASTNAME, AGE) VALUES ("
                        + "'" + name + "'" + " , " + "'" + lastName + "'" + "," + age + ")");
              PreparedStatement commit = util.getConnection().prepareStatement("COMMIT")) {
            preparedStatement.execute();
            commit.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement preparedStatement = util.getConnection()
                .prepareStatement("DELETE FROM users WHERE id =" + id);
             PreparedStatement commit = util.getConnection().prepareStatement("COMMIT")) {
            preparedStatement.execute();
            commit.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (PreparedStatement preparedStatement = util.getConnection().prepareStatement("SELECT * FROM users");
             PreparedStatement commit = util.getConnection().prepareStatement("COMMIT")) {
            ResultSet rSet = preparedStatement.executeQuery();
            commit.executeUpdate();
            while (rSet.next()) {
                User user = new User(rSet.getInt(1)
                        ,rSet.getString(2),
                        rSet.getString(3),
                        (byte) rSet.getInt(4));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try (PreparedStatement preparedStatement = util.getConnection().prepareStatement("DELETE FROM users");
             PreparedStatement commit = util.getConnection().prepareStatement("COMMIT")) {
            preparedStatement.execute();
            commit.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection()  {
        util.closeConnection();
    }
}
