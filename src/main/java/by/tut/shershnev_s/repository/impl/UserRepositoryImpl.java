package by.tut.shershnev_s.repository.impl;

import by.tut.shershnev_s.repository.UserRepository;
import by.tut.shershnev_s.repository.model.User;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl extends GeneralRepositoryImpl<User> implements UserRepository {

    private static UserRepository instance;

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepositoryImpl();
        }
        return instance;
    }

    public int deleteTable(Connection connection) throws SQLException {
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "DROP TABLE IF EXISTS user;"
                );
        ) {
            return preparedStatement.executeUpdate();
        }
    }

    public int createTable(Connection connection) throws SQLException {
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "CREATE TABLE IF NOT EXISTS user(id INT(11) AUTO_INCREMENT PRIMARY KEY NOT \n" +
                                "NULL, username VARCHAR(40), password VARCHAR(40), is_active \n" +
                                "TINYINT(1), user_group_id  INT(11),  age INT(11), FOREIGN KEY\n" +
                                "(user_group_id) REFERENCES user_group(id) ON DELETE CASCADE);"
                );
        ) {
            return preparedStatement.executeUpdate();
        }
    }

    @Override
    public User add(Connection connection, User user) throws SQLException {
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO user(id, username, password, is_active, user_group_id, age) VALUES(?,?,?,?,?,?)"
                );
        ) {
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setBoolean(4, user.getisActive());
            preparedStatement.setInt(5, user.getUserGroupID());
            preparedStatement.setInt(6, user.getAge());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Adding user failed, no rows affected.");
            }
        }
        return user;
    }

    @Override
    public User get(Connection connection, Serializable id) throws SQLException {
        return null;
    }

    @Override
    public void update(Connection connection, User user) throws SQLException {

    }

    @Override
    public int delete(Connection connection, Serializable id) throws SQLException {
        try (
                PreparedStatement statement = connection.prepareStatement(
                        "DELETE FROM user WHERE age<?"
                );
        ) {
            statement.setInt(1, (Integer) id);
            return statement.executeUpdate();
        }
    }

    @Override
    public List<User> findAll(Connection connection) throws SQLException {
        try (
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM user")
        ) {
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                User user = getUser(rs);
                users.add(user);
            }
            return users;
        }
    }

    private User getUser(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String username = rs.getString("username");
        String password = rs.getString("password");
        boolean isActive = rs.getBoolean("is_active");
        int userGroupID = rs.getInt("user_group_id");
        int age = rs.getInt("age");
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setIsActive(isActive);
        user.setUserGroupID(userGroupID);
        user.setAge(age);
        return user;
    }

}
