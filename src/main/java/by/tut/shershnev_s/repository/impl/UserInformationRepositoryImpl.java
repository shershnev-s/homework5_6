package by.tut.shershnev_s.repository.impl;

import by.tut.shershnev_s.repository.UserInformationRepository;
import by.tut.shershnev_s.repository.model.UserInformation;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserInformationRepositoryImpl extends GeneralRepositoryImpl<UserInformation> implements UserInformationRepository {

    private static UserInformationRepository instance;

    public static UserInformationRepository getInstance() {
        if (instance == null) {
            instance = new UserInformationRepositoryImpl();
        }
        return instance;
    }

    public int deleteTable(Connection connection) throws SQLException {
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "DROP TABLE IF EXISTS user_information;"
                );
        ) {
            return preparedStatement.executeUpdate();
        }
    }

    public int createTable(Connection connection) throws SQLException {
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "CREATE TABLE user_information(user_id INT(11) PRIMARY KEY NOT NULL, \n" +
                                "address VARCHAR(100),telephone VARCHAR(40), FOREIGN KEY(user_id) \n" +
                                "REFERENCES user(id) ON DELETE CASCADE);"
                );
        ) {
            return preparedStatement.executeUpdate();
        }
    }

    @Override
    public UserInformation add(Connection connection, UserInformation userInformation) throws SQLException {
        return null;
    }

    @Override
    public UserInformation get(Connection connection, Serializable id) throws SQLException {
        return null;
    }

    @Override
    public void update(Connection connection, UserInformation userInformation) throws SQLException {

    }

    @Override
    public int delete(Connection connection, Serializable id) throws SQLException {
        return 0;
    }

    @Override
    public List<UserInformation> findAll(Connection connection) throws SQLException {
        return null;
    }
}
