package by.tut.shershnev_s.repository.impl;

import by.tut.shershnev_s.repository.UserGroupRepository;
import by.tut.shershnev_s.repository.model.UserGroup;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserGroupRepositoryImpl  extends GeneralRepositoryImpl <UserGroup> implements UserGroupRepository {

    private static UserGroupRepository instance;

    public static UserGroupRepository getInstance() {
        if (instance == null) {
            instance = new UserGroupRepositoryImpl();
        }
        return instance;
    }

    public int deleteTable(Connection connection) throws SQLException {
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "DROP TABLE IF EXISTS user_group;"
                );
        ) {
            return preparedStatement.executeUpdate();
        }
    }

    public int createTable(Connection connection) throws SQLException {
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "CREATE TABLE user_group(id INT(11) AUTO_INCREMENT PRIMARY KEY NOT NULL, name \n" +
                                "VARCHAR(40));"
                );
        ) {
            return preparedStatement.executeUpdate();
        }
    }

    @Override
    public UserGroup add(Connection connection, UserGroup userGroup) throws SQLException {
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO user_group(id, name) VALUES(?,?)"
                );
        ) {
            preparedStatement.setInt(1, userGroup.getId());
            preparedStatement.setString(2, userGroup.getName());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows==0){
                throw new SQLException("Creating user group failed, no rows affected.");
            }
        }
        return userGroup;
    }

    @Override
    public UserGroup get(Connection connection, Serializable id) throws SQLException {
        return null;
    }

    @Override
    public void update(Connection connection, UserGroup userGroup) throws SQLException {

    }

    @Override
    public int delete(Connection connection, Serializable id) throws SQLException {
        return 0;
    }

    @Override
    public List<UserGroup> findAll(Connection connection) throws SQLException {
        return null;
    }
}
