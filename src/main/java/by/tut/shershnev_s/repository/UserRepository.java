package by.tut.shershnev_s.repository;

import by.tut.shershnev_s.repository.model.User;

import java.sql.Connection;
import java.sql.SQLException;

public interface UserRepository extends GeneralRepository<User> {
    int deleteTable(Connection connection) throws SQLException;

    int createTable(Connection connection) throws SQLException;
}
