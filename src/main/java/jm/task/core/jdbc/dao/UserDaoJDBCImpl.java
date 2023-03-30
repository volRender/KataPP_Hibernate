package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            String query = "CREATE TABLE users (id BIGINT AUTO_INCREMENT, " +
                    "name VARCHAR(15), lastname VARCHAR(25), age TINYINT, PRIMARY KEY (id));";
            statement.executeUpdate(query);
            System.out.println("������� Users ���� �������!");
        } catch (SQLException e) {
            System.out.println("��� ������� ��� ���������� ��� ��� sql-������ �����������");
        }
    }

    public void dropUsersTable() {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            String query = "DROP TABLE users";
            statement.executeUpdate(query);
            System.out.println("������� Users ���� �������!");
        } catch (SQLException e) {
            System.out.println("���� ������� �� ���������� ��� ��� sql-������ �����������");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String query = "INSERT INTO users (name, lastname, age) values(?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
            System.out.println("User � ������ " + name + " �������� � ���� ������");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("������ ��� �������� ������������");
        }
    }

    public void removeUserById(long id) {
        String query = "DELETE FROM users where ID=?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("������ ��� �������� ������������");
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String query = "SELECT id, name, lastname, age FROM users";
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));

                userList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("������ ��� �������� ������ ���� �������������");
        }
        return userList;
    }

    public void cleanUsersTable() {

    }
}
