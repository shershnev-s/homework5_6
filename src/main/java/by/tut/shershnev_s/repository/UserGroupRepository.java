package by.tut.shershnev_s.repository;

import by.tut.shershnev_s.repository.model.UserGroup;

import java.sql.Connection;
import java.sql.SQLException;

public interface UserGroupRepository extends GeneralRepository<UserGroup> {
    int deleteTable(Connection connection) throws SQLException;

    int createTable(Connection connection) throws SQLException;
}
