package by.tut.shershnev_s.repository;

import by.tut.shershnev_s.repository.model.UserInformation;

import java.sql.Connection;
import java.sql.SQLException;

public interface UserInformationRepository extends GeneralRepository<UserInformation> {
    int deleteTable(Connection connection) throws SQLException;

    int createTable(Connection connection) throws SQLException;
}
